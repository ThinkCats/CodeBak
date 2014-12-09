package client;

import client.bean.User;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by wanglei on 2014/10/6.
 */
public class UserClient {
    private static String serviceUri="http://localhost:8080/rest/users";

    public static void main(String args[]){
        addUser();
        getAllUsers();
        deleteUser();
        getAllUsers();
        updateUser();
        getAllUsers();
        getUserById();
        getAllUsers();
    }

    private static void addUser(){
        System.out.println("===== add user=====");
        User user=new User("007","Susan","21");
        Client client= ClientBuilder.newClient();
        WebTarget target=client.target(serviceUri);
        Response response=target.request().buildPost(Entity.entity(user, MediaType.APPLICATION_JSON)).invoke();
        response.close();
    }

    private static void deleteUser(){
        System.out.println("=== del user ===");
        Client client=ClientBuilder.newClient();
        WebTarget target=client.target(serviceUri+"/007");
        Response response=target.request().delete();
        response.close();
    }

    private static void updateUser(){
        System.out.println("==== update user ===");
        User user=new User("001","Miao","23");
        Client client=ClientBuilder.newClient();
        WebTarget target=client.target(serviceUri);
        Response response=target.request().buildPut(Entity.entity(user,MediaType.APPLICATION_JSON)).invoke();
        response.close();
    }

    private static void getUserById(){
        System.out.println("=== begin getuserbyid ===");
        Client client=ClientBuilder.newClient();
        WebTarget target=client.target(serviceUri+"/001");
        Response response=target.request().get();
        User user=response.readEntity(User.class);
        System.out.println(user.getUserId()+user.getUsername()+user.getAge());
        response.close();
    }

    private static void getAllUsers(){
        System.out.println("===开始查询===");
        Client client=ClientBuilder.newClient();
        WebTarget target=client.target(serviceUri);
        Response response=target.request().get();
        String value=response.readEntity(String.class);
        System.out.println(value);
        response.close();
    }
}
