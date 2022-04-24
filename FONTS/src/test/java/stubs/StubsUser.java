package stubs;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Domain.Utils.ItemTypes;


public class StubsUser {
    public User getStubUser() {
        return new User("test");
    }
    public String getStubUserName() {
        String name = "test";
        return name;
    }
    public User getStubUserWithName() {
        User user = new User("test");
        user.setName(getStubUserName());
        return user;

    }
    public Item getStubItem() {
        return new Item("TestItem", "TestItem", ItemTypes.movie);
    }
    public User getStubUserWithItem() {
        User user = getStubUser();

        Item item = getStubItem();
        user.addItem(item);
        return user;
    }
    public Double getStubRating(){
        return 2.0;

    }


}
