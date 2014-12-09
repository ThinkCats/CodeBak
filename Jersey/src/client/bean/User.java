package client.bean;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by wanglei on 2014/10/5.
 */
@XmlRootElement
public class User extends Base {
    private String userId;
    private String username;
    private String age;
    private Food[] foods;
    private List<Goods> list;

    public User() {
    }

    public User(String userId, String username, String age) {
        this.userId = userId;
        this.username = username;
        this.age = age;
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }

    public Food[] getFoods() {
        return foods;
    }

    public void setFoods(Food[] foods) {
        this.foods = foods;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
