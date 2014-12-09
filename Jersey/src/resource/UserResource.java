package resource;

import client.bean.Food;
import client.bean.Goods;
import client.bean.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanglei on 2014/10/5.
 */
@Path("/users")
public class UserResource {
    private static Map<String,User> userMap=new HashMap<String, User>();
    /*查询所有*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers(){
        List<User> users=new ArrayList<User>();
        User user1=new User("001","Tom","22");
        User user2=new User("002","Wang","20");
        User user3=new User("003","Zhang","18");

        userMap.put(user1.getUserId(),user1);
        userMap.put(user2.getUserId(),user2);
        userMap.put(user3.getUserId(),user3);
        users.addAll(userMap.values());
        return users;
    }

    @GET
    @Path("/getUserXml")
    @Produces(MediaType.APPLICATION_XML)
    public User getUser(){
        User user=new User("004","Lina","21");
        return user;
    }

    @GET
    @Path("/getUserJson")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserJson(){
        User user=new User("005","wanglei","22");
        return user;
    }

    @GET
    @Path("/getJacksonList")
    @Produces(MediaType.APPLICATION_JSON)
    public User getJackson(){
        Food food=new Food();
        food.setFoodname("orz");
        food.setColor("blue");
        Food food1=new Food();
        food1.setColor("red");
        food1.setFoodname("xxx");
        Food[] foods=new Food[]{food,food1};
        User user=new User();
        user.setFoods(foods);
        user.setUsername("wwwww");
        user.setAge("111");
        Goods goods=new Goods();
        goods.setGooddesc("one");
        goods.setGoodname("onegoods");
        Goods goods1=new Goods();
        goods1.setGoodname("two");
        goods1.setGooddesc("two");
        List<Goods> list=new ArrayList<Goods>();
        list.add(goods);
        list.add(goods1);
        user.setList(list);
        user.setA("a");
        user.setB("b");
        return user;
    }

    /*add*/
    @POST
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void createStudent(User user){
        userMap.put(user.getUserId(),user);
    }

    /*del*/
    @DELETE
    @Path("{id}")
    public void deleteUser(@PathParam("id")String id){
        userMap.remove(id);
    }

    /*update*/
    @PUT
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public  void updateUser(User user){
        userMap.put(user.getUserId(),user);
    }

    /*query by id*/
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public User getUserById(@PathParam("id")String id){
        User user=userMap.get(id);
        return user;
    }
}
