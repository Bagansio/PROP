package stubs;

import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Domain.Models.UserGroup;

public class StubsUserGroup {
    public UserGroup getStubUserGroup() {
        return new UserGroup("1");
    }
    public User getStubUser() {
        return new User("test");
    }
    public UserGroup getStubUserGroupWithUser() {
        UserGroup ug = new UserGroup("1");
        User user = new User("test");
        ug.addUser(user);
        return ug;
    }

}