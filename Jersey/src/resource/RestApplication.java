package resource;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by wanglei on 2014/10/5.
 */
public class RestApplication extends ResourceConfig {
    public RestApplication(){
        packages("hello.resource");
        register(JacksonJsonProvider.class);
    }
}
