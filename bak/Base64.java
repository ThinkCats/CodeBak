public class Base64 {

	public static void main(String[] args) {

		byte[] data1 = "zhouqian周骞".getBytes();
		String base64 = null;
		byte[] data2 = null;
		long time1, time2;

		// /
		time1 = System.currentTimeMillis();
		for (int i = 0; i < 9999999; ++i) {
			base64 = encode1(data1);
			data2 = decode(base64);
		}
		time2 = System.currentTimeMillis();
		System.out.println(time2 - time1);
		System.out.println(base64);
		System.out.println(new String(data2));

		// /
		time1 = System.currentTimeMillis();
		for (int i = 0; i < 9999999; ++i) {
			base64 = encode2(data1);
			data2 = decode(base64);
		}
		time2 = System.currentTimeMillis();
		System.out.println(time2 - time1);
		System.out.println(base64);
		System.out.println(new String(data2));

		// /
		time1 = System.currentTimeMillis();
		for (int i = 0; i < 9999999; ++i) {
			base64 = encode3(data1);
			data2 = decode(base64);
		}
		time2 = System.currentTimeMillis();
		System.out.println(time2 - time1);
		System.out.println(base64);
		System.out.println(new String(data2));

	}


	private static char BASE64_ENCODE[] = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
		'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
		'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
		'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
		'4', '5', '6', '7', '8', '9', '+', '/',
	};

	private static byte BASE64_DECODE[] = {
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1,
		-1, // 注意两个63，为兼容SMP，
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 63,
		-1,
		63, // “/”和“-”都翻译成63。
		52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, 0, -1, -1, -1, 0,
		1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
		14, // 注意两个0：
		15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1,
		-1, // “A”和“=”都翻译成0。
		-1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42,
		43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1,
	};


	private Base64() {

	}


	/**
	 * Base64编码。将字节数组中字节3个一组编码成4个可见字符。
	 * 
	 * @param b
	 *            需要被编码的字节数据。
	 * @return 编码后的Base64字符串。
	 */
	public static String encode1(byte[] b) {

		int code = 0;

		// 按实际编码后长度开辟内存，加快速度
		StringBuffer sb = new StringBuffer(((b.length - 1) / 3) << 2 + 4);

		// 进行编码
		for (int i = 0; i < b.length; i++) {
			code |= (b[i] << (16 - i % 3 * 8)) & (0xff << (16 - i % 3 * 8));
			if (i % 3 == 2 || i == b.length - 1) {
				sb.append(BASE64_ENCODE[(code & 0xfc0000) >>> 18]);
				sb.append(BASE64_ENCODE[(code & 0x3f000) >>> 12]);
				sb.append(BASE64_ENCODE[(code & 0xfc0) >>> 6]);
				sb.append(BASE64_ENCODE[code & 0x3f]);
				code = 0;
			}
		}

		// 对于长度非3的整数倍的字节数组，编码前先补0，编码后结尾处编码用=代替，
		// =的个数和短缺的长度一致，以此来标识出数据实际长度
		if (b.length % 3 > 0) {
			sb.setCharAt(sb.length() - 1, '=');
		}
		if (b.length % 3 == 1) {
			sb.setCharAt(sb.length() - 2, '=');
		}
		return sb.toString();
	}


	public static String encode2(byte[] b) {

		StringBuilder sb = new StringBuilder(((b.length - 1) / 3) << 2 + 4);
		int code = 0;
		for (int i = 0; i < b.length; i += 3) {
			try {
				code = (b[i] & 0xff) << 16;
				code |= (b[i + 1] & 0xff) << 8;
				code |= b[i + 2] & 0xff;
			} catch (ArrayIndexOutOfBoundsException e) {

			} finally {
				sb.append(BASE64_ENCODE[(code & 0x00fc0000) >>> 18]);
				sb.append(BASE64_ENCODE[(code & 0x0003f000) >>> 12]);
				sb.append(BASE64_ENCODE[(code & 0x00000fc0) >>> 6]);
				sb.append(BASE64_ENCODE[code & 0x0000003f]);
			}
		}
		if (b.length % 3 > 0) {
			sb.setCharAt(sb.length() - 1, '=');
		}
		if (b.length % 3 == 1) {
			sb.setCharAt(sb.length() - 2, '=');
		}
		return sb.toString();
	}


	public static String encode3(byte[] b) {

		char[] data = new char[((b.length - 1) / 3) << 2 + 4];
		int code = 0, j = -1;
		for (int i = 0; i < b.length; i += 3) {
			try {
				code = (b[i] & 0xff) << 16;
				code |= (b[i + 1] & 0xff) << 8;
				code |= b[i + 2] & 0xff;
			} catch (ArrayIndexOutOfBoundsException e) {

			} finally {
				data[++j] = BASE64_ENCODE[(code & 0x00fc0000) >>> 18];
				data[++j] = BASE64_ENCODE[(code & 0x0003f000) >>> 12];
				data[++j] = BASE64_ENCODE[(code & 0x00000fc0) >>> 6];
				data[++j] = BASE64_ENCODE[code & 0x0000003f];
			}
		}
		++j;
		if (b.length % 3 > 0)
			data[--j] = '=';
		if (b.length % 3 == 1)
			data[--j] = '=';
		return new String(data);
	}


	/**
	 * Base64解码。
	 * 
	 * @param code
	 *            用Base64编码的ASCII字符串
	 * @return 解码后的字节数据
	 */
	public static byte[] decode(String code) {

		// 检查参数合法性
		if (code == null) {
			return null;
		}
		int len = code.length();
		if (len % 4 != 0) {
			throw new IllegalArgumentException(
				"Base64 string length must be 4*n");
		}
		if (code.length() == 0) {
			return new byte[0];
		}

		// 统计填充的等号个数
		int pad = 0;
		if (code.charAt(len - 1) == '=') {
			pad++;
		}
		if (code.charAt(len - 2) == '=') {
			pad++;
		}

		// 根据填充等号的个数来计算实际数据长度
		int retLen = len / 4 * 3 - pad;

		// 分配字节数组空间
		byte[] ret = new byte[retLen];

		// 查表解码
		char ch1, ch2, ch3, ch4;
		int i;
		for (i = 0; i < len; i += 4) {
			int j = i / 4 * 3;
			ch1 = code.charAt(i);
			ch2 = code.charAt(i + 1);
			ch3 = code.charAt(i + 2);
			ch4 = code.charAt(i + 3);
			int tmp =
				(BASE64_DECODE[ch1] << 18) | (BASE64_DECODE[ch2] << 12) |
					(BASE64_DECODE[ch3] << 6) | (BASE64_DECODE[ch4]);
			ret[j] = (byte) ((tmp & 0xff0000) >> 16);
			if (i < len - 4) {
				ret[j + 1] = (byte) ((tmp & 0x00ff00) >> 8);
				ret[j + 2] = (byte) ((tmp & 0x0000ff));
			} else {
				if (j + 1 < retLen) {
					ret[j + 1] = (byte) ((tmp & 0x00ff00) >> 8);
				}
				if (j + 2 < retLen) {
					ret[j + 2] = (byte) ((tmp & 0x0000ff));
				}
			}
		}
		return ret;
	}
}