package test.Domain.Models;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Domain.Utils.ItemTypes;

import org.junit.*;

import java.util.Map;

public class DriverUserUnitary {
    @Test
    public void testSetId(){
        User user = new User("test");
        user.setId("testing");
        String id = user.getId();
        Assert.assertEquals("SetId Failed", "testing", id);

    }
    @Test
    public void testGetId(){
        User user = new User("test");
        String id = user.getId();
        Assert.assertEquals("GetId Failed", "test", id);
    }
    @Test
    public void testSetName(){
        User user = new User("test");
        user.setName("test");
        String name = user.getName();
        Assert.assertEquals("GetName Failed", "test", name);
    }
    @Test
    public void testGetName(){
        User user = new User("test");
        user.setName("test");
        String name = user.getName();
        Assert.assertEquals("GetName Failed", "test", name);
    }
    @Test
    public void testAddItem() {
        User user = new User("test");
        Item item = new Item("TestItem", "TestItem", ItemTypes.movie);
        user.addItem(item);
        Item obtainedItem = user.getItem("TestItem");
        Assert.assertEquals("GetItem Failed", obtainedItem, item);
    }

    @Test
    public void testQuitItem() {
        User user = new User("test");
        Item item = new Item("TestItem", "TestItem", ItemTypes.movie);
        user.addItem(item);
        user.quitItem("TestItem");
        Assert.assertTrue("QuitItem Failed", user.getItem("TestItem") == null);

    }

    @Test
    public void testGetItem() {
        User user = new User("test");
        Item item = new Item("TestItem", "TestItem", ItemTypes.movie);
        user.addItem(item);
        Item obtainedItem = user.getItem("TestItem");
        Assert.assertEquals("GetItem Failed", obtainedItem, item);
    }

    @Test
    public void testRateItem() {
        User user = new User("test");
        Item item = new Item("TestItem", "TestItem", ItemTypes.movie);
        user.addItem(item);
        user.rateItem("TestItem", 2.0);
        Map<String, Double> ratings = user.getRatings();
        Assert.assertEquals("RateItem Failed", ratings.get("TestItem"), 2.0, 0);

    }
    @Test
    public void testSetRate() {
        User user = new User("test");
        Item item = new Item("TestItem", "TestItem", ItemTypes.movie);
        user.addItem(item);
        user.rateItem("TestItem", 2.0);
        user.setRate("TestItem", 10.0);
        Map<String, Double> ratings = user.getRatings();
        Assert.assertEquals("SetRate Failed", ratings.get("TestItem"), 10.0, 0);
    }
    @Test
    public void testGetItems() {
        User user = new User("test");
        Item item = new Item("TestItem", "TestItem", ItemTypes.movie);
        user.addItem(item);
        Item item1 = new Item("TestItem1", "TestItem1", ItemTypes.movie);
        user.addItem(item1);
        Map<String, Item> items = user.getItems();

        Assert.assertEquals("GetItem Failed Item 1", items.get("TestItem"), item);
        Assert.assertEquals("GetItem Failed Item 2", items.get("TestItem1"), item1);


    }
    @Test
    public void testGetRatings() {
        User user = new User("test");
        Item item = new Item("TestItem", "TestItem", ItemTypes.movie);
        user.addItem(item);
        Item item1 = new Item("TestItem1", "TestItem1", ItemTypes.movie);
        user.addItem(item1);
        user.rateItem("TestItem", 3.0);
        user.rateItem("TestItem1", 4.0);
        Map<String, Double> ratings = user.getRatings();
        Assert.assertEquals("GetRating Failed Item1", ratings.get("TestItem"), 3.0, 0);
        Assert.assertEquals("GetRating Failed Item2", ratings.get("TestItem1"), 4.0, 0);
    }

    /*
    public void testGetPassword(){
      //  String password =user.getPassword();
    }
    public void testSetPassword(){
      /*  user.setPassword("hola");
        String password =user.getPassword();
        if (password != "hola") {

        }

    }*/
    /*public static void main (String [] args){
        testGetId();
        testSetId();
        testGetName();
        testSetName();
        testGetPassword();
        testSetPassword();
    }*/
}
