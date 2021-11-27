package test.Domain.Models;

import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Domain.Models.UserGroup;
import stubs.StubsUserGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DriverUserGroup {
    public static final String ansiGreen = "\u001B[32m";
    public static final String ansiRed = "\u001B[31m";
    public static final String ansiNormal = "\u001B[0m";

    static boolean first = true;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int usage;
        Usage();
        while ((usage = reader.nextInt()) != 8) {
            switch (usage) {
                case 1:
                    executeGetId();
                    break;

                case 2:
                    executeSetId();
                    break;
                case 3:
                    executeGetUsers();
                    break;
                case 4:
                    executeSetUsers();
                    break;
                case 5:
                    executeContainsUser();
                    break;
                case 6:
                    executeAddUser();
                    break;
                case 7:
                    executeRemoveUser();
                    break;
                default:
                    System.out.println("Invalid value");
                    System.out.println();
                    Usage();
                    break;
            }
        }
    }

    public static void Usage() {
        if (first) {
            System.out.println();
            first = false;
        }
        System.out.println("You are executing User Group class driver. Choose one of the following options in order to" +
                " test this class");
        System.out.println("\t1. getID: creates an User Group and checks if the getter works properly");
        System.out.println(" \t2. setId: change the id and checks if the change has been done properly");
        System.out.println(" \t3. getUser: creates an UserGroup and an User and checks if the getter works properly");
        System.out.println(" \t4. setUser: creates an UserGroup with an User and change the user associates and checks if the change has been done properly");
        System.out.println(" \t5. containsUser: creates an UserGroup with 2 Users and checks if the function works properly");
        System.out.println(" \t6. addUser: Create an UserGroup and an User and associate the User with the UserGroup and checks if the functions works properly");
        System.out.println(" \t7. removeUser: Create an UserGroup with 2 Users and removes one and checks if it has benn deleted properly");
        System.out.println(ansiRed + "\t8. Exit" + ansiNormal);
    }

    private static void executeGetId() {
        System.out.println("Testing Get Id");
        System.out.println("We will now  create an UserGroup");
        StubsUserGroup stubsUserGroup = new StubsUserGroup();
        UserGroup userGroup = stubsUserGroup.getStubUserGroup();
        System.out.println("Expected id values after the execution: 1");
        String finalId =userGroup.getId();
        System.out.println("id after the execution: " +finalId);
        if (finalId == "1") System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);


    }
    private static void executeSetId() {
        System.out.println("Testing Set Id");
        System.out.println("We will now  create an UserGroup");
        UserGroup userGroup = new UserGroup("1");
        String id = "5" ;
        userGroup.setId(id);
        System.out.println("Expected id values after the execution:" + id);
        String finalId =userGroup.getId();
        System.out.println("id after the execution: " +finalId);
        if (finalId == id) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);

    }

    private static void executeGetUsers() {
        System.out.println("Testing Get Users");
        System.out.println("We will now  create an UserGroup");

        StubsUserGroup stubsUserGroup = new StubsUserGroup();
        UserGroup userGroup = stubsUserGroup.getStubUserGroupWithUser();

        System.out.println("Expected id values after the execution: test");

        Map<String, User> users = userGroup.getUsers();
        System.out.println("id after the execution: " + users.get("test").getId());
        if ("test" == users.get("test").getId()) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeSetUsers() {
        System.out.println("Testing Set Users");
        System.out.println("We will now  create an UserGroup");
        UserGroup userGroup = new UserGroup("1");
        StubsUserGroup stubsUserGroup = new StubsUserGroup();
        System.out.println("We will now  create two Users");
        User user = stubsUserGroup.getStubUser();
        User user1 = new User("Testing");
        Map<String, User> users = new HashMap<String, User>();
        users.put("Testing", user1);
        System.out.println("Expected id values after the execution:" + user1.getId());
        userGroup.addUser(user);
        userGroup.setUsers(users);
        Map<String, User> finalUsers = userGroup.getUsers();
        System.out.println("id after the execution: " + finalUsers.get("Testing").getId());
        if (finalUsers == users) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);


    }


    private static void executeContainsUser(){
        System.out.println("Testing Contains User");
        System.out.println("We will now  create an UserGroup");
        UserGroup userGroup = new UserGroup("1");
        StubsUserGroup stubsUserGroup = new StubsUserGroup();
        System.out.println("We will now  create two Users");
        User user = stubsUserGroup.getStubUser();
        User user1 = new User("Testing");
        Map<String, User> users = new HashMap<String, User>();
        users.put("Testing", user1);
        users.put("Test", user);
        userGroup.addUser(user);
        userGroup.setUsers(users);
        if (userGroup.containsUser("Testing")) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);

    }


    private static void executeAddUser(){
        System.out.println("Testing Add User");
        System.out.println("We will now  create an UserGroup");
        UserGroup userGroup = new UserGroup("1");
        StubsUserGroup stubsUserGroup = new StubsUserGroup();
        User user = stubsUserGroup.getStubUser();
        System.out.println("Expected id values after the execution:" + user.getId());
        userGroup.addUser(user);
        Map<String, User> users = userGroup.getUsers();
        System.out.println("id after the execution: " + users.get("test").getId());
        if (user.getId() == users.get("test").getId()) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }


    private static void executeRemoveUser(){
        System.out.println("Testing Remove User");
        System.out.println("Testing Contains User");
        System.out.println("We will now  create an UserGroup");
        UserGroup userGroup = new UserGroup("1");
        StubsUserGroup stubsUserGroup = new StubsUserGroup();
        System.out.println("We will now  create two Users");
        User user = stubsUserGroup.getStubUser();
        User user1 = new User("Testing");
        Map<String, User> users = new HashMap<String, User>();
        users.put("Testing", user1);
        users.put("Test", user);
        System.out.println("We add the users to User Group");
        userGroup.addUser(user);
        userGroup.setUsers(users);
        System.out.println("We will now  remove the user with Id Testing");
        userGroup.removeUser("Testing");
        if (!userGroup.containsUser("Testing")) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);

    }


}