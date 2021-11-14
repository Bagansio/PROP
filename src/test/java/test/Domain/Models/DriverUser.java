package test.Domain.Models;

import com.recommender.recommenderapp.Domain.Models.User;

public class DriverUser {
    private static User user =new User("hola");
    public static void testGetId(){
        String id = user.getId();
    }
    public static void testSetId(){
        user.setId("hola");
        String id = user.getId();
        if (id != "hola") {

        }
    }
    public static void testGetName(){
        String name =user.getName();
    }
    public static void testSetName(){
        user.setName("hola");
        String name =user.getName();
        if (name != "hola") {

        }
    }
   /* public static void testGetPassword(){
        String password =user.getPassword();
    }
    public static void testSetPassword(){
        user.setPassword("hola");
        String password =user.getPassword();
        System.out.println("Test Set Password: ");
        if (password != "hola") {

            System.out.println("Success");

        }

    }*/
    public static void main (String [] args){
        testGetId();
        testSetId();
        testGetName();
        testSetName();
        /*testGetPassword();
        testSetPassword();*/
    }
}
