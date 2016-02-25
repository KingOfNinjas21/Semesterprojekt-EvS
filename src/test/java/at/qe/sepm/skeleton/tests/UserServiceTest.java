package at.qe.sepm.skeleton.tests;

import at.qe.sepm.skeleton.Main;
import at.qe.sepm.skeleton.model.User;
import at.qe.sepm.skeleton.model.UserRole;
import at.qe.sepm.skeleton.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Some very basic tests for {@link UserService}.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testDatainitialization() {
        Assert.assertEquals("Insufficient amount of users initialized for test data source", 3, userService.getAllUsers().size());
        for (User user : userService.getAllUsers()) {
            if ("admin".equals(user.getUsername())) {
                Assert.assertTrue("User \"admin\" does not have role ADMIN", user.getRoles().contains(UserRole.ADMIN));
                Assert.assertNotNull("User \"admin\" does not have a createUser defined", user.getCreateUser());
                Assert.assertNotNull("User \"admin\" does not have a createDate defined", user.getCreateDate());
                Assert.assertNull("User \"admin\" has a updateUser defined", user.getUpdateUser());
                Assert.assertNull("User \"admin\" has a updateDate defined", user.getUpdateDate());
            } else if ("user1".equals(user.getUsername())) {
                Assert.assertTrue("User \"user1\" does not have role MANAGER", user.getRoles().contains(UserRole.MANAGER));
                Assert.assertNotNull("User \"user1\" does not have a createUser defined", user.getCreateUser());
                Assert.assertNotNull("User \"user1\" does not have a createDate defined", user.getCreateDate());
                Assert.assertNull("User \"user1\" has a updateUser defined", user.getUpdateUser());
                Assert.assertNull("User \"user1\" has a updateDate defined", user.getUpdateDate());
            } else if ("user2".equals(user.getUsername())) {
                Assert.assertTrue("User \"user2\" does not have role EMPLOYEE", user.getRoles().contains(UserRole.EMPLOYEE));
                Assert.assertNotNull("User \"user2\" does not have a createUser defined", user.getCreateUser());
                Assert.assertNotNull("User \"user2\" does not have a createDate defined", user.getCreateDate());
                Assert.assertNull("User \"user2\" has a updateUser defined", user.getUpdateUser());
                Assert.assertNull("User \"user2\" has a updateDate defined", user.getUpdateDate());
            } else {
                Assert.fail("Unknown user \"" + user.getUsername() + "\" loaded from test data source via UserService.getAllUsers");
            }
        }
    }

    @DirtiesContext
    @Test
    public void testDeleteUser() {
        User adminUser = userService.loadUser("admin");
        Assert.assertNotNull("Admin user could not be loaded from test data source", adminUser);
        User toBeDeletedUser = userService.loadUser("user1");
        Assert.assertNotNull("User1 could not be loaded from test data source", toBeDeletedUser);

        userService.deleteUser(toBeDeletedUser, adminUser);

        Assert.assertEquals("No user has been deleted after calling UserService.deleteUser", 2, userService.getAllUsers().size());
        User deletedUser = userService.loadUser("user1");
        Assert.assertNull("Deleted User1 could still be loaded from test data source via UserService.loadUser", deletedUser);

        for (User remainingUser : userService.getAllUsers()) {
            Assert.assertNotEquals("Deleted User1 could still be loaded from test data source via UserService.getAllUsers", toBeDeletedUser.getUsername(), remainingUser.getUsername());
        }
    }

    @DirtiesContext
    @Test
    public void testUpdateUser() {
        User adminUser = userService.loadUser("admin");
        Assert.assertNotNull("Admin user could not be loaded from test data source", adminUser);
        User toBeSavedUser = userService.loadUser("user1");
        Assert.assertNotNull("User1 could not be loaded from test data source", toBeSavedUser);

        Assert.assertNull("User \"user1\" has a updateUser defined", toBeSavedUser.getUpdateUser());
        Assert.assertNull("User \"user1\" has a updateDate defined", toBeSavedUser.getUpdateDate());

        toBeSavedUser.setEmail("changed-email@whatever.wherever");
        userService.saveUser(toBeSavedUser, adminUser);

        User freshlyLoadedUser = userService.loadUser("user1");
        Assert.assertNotNull("User1 could not be loaded from test data source after being saved", freshlyLoadedUser);
        Assert.assertNotNull("User \"user1\" does not have a updateUser defined after being saved", freshlyLoadedUser.getUpdateUser());
        Assert.assertEquals("User \"user1\" has wrong updateUser set", adminUser, freshlyLoadedUser.getUpdateUser());
        Assert.assertNotNull("User \"user1\" does not have a updateDate defined after being saved", freshlyLoadedUser.getUpdateDate());
        Assert.assertEquals("User \"user1\" does not have a the correct email attribute stored being saved", "changed-email@whatever.wherever", freshlyLoadedUser.getEmail());
    }

    @DirtiesContext
    @Test
    public void testCreateUser() {
        User adminUser = userService.loadUser("admin");
        Assert.assertNotNull("Admin user could not be loaded from test data source", adminUser);

        User toBeCreatedUser = new User();
        toBeCreatedUser.setUsername("newuser");
        toBeCreatedUser.setPassword("passwd");
        toBeCreatedUser.setEnabled(true);
        toBeCreatedUser.setFirstName("New");
        toBeCreatedUser.setLastName("User");
        toBeCreatedUser.setEmail("new-email@whatever.wherever");
        toBeCreatedUser.setPhone("+12 345 67890");
        toBeCreatedUser.setRoles(Sets.newSet(UserRole.EMPLOYEE, UserRole.MANAGER));
        userService.saveUser(toBeCreatedUser, adminUser);

        User freshlyCreatedUser = userService.loadUser("newuser");
        Assert.assertNotNull("New user could not be loaded from test data source after being saved", freshlyCreatedUser);
        Assert.assertEquals("User \"newuser\" does not have a the correct username attribute stored being saved", "newuser", freshlyCreatedUser.getUsername());
        Assert.assertEquals("User \"newuser\" does not have a the correct password attribute stored being saved", "passwd", freshlyCreatedUser.getPassword());
        Assert.assertEquals("User \"newuser\" does not have a the correct firstName attribute stored being saved", "New", freshlyCreatedUser.getFirstName());
        Assert.assertEquals("User \"newuser\" does not have a the correct lastName attribute stored being saved", "User", freshlyCreatedUser.getLastName());
        Assert.assertEquals("User \"newuser\" does not have a the correct email attribute stored being saved", "new-email@whatever.wherever", freshlyCreatedUser.getEmail());
        Assert.assertEquals("User \"newuser\" does not have a the correct phone attribute stored being saved", "+12 345 67890", freshlyCreatedUser.getPhone());
        Assert.assertTrue("User \"newuser\" does not have role MANAGER", freshlyCreatedUser.getRoles().contains(UserRole.MANAGER));
        Assert.assertTrue("User \"newuser\" does not have role EMPLOYEE", freshlyCreatedUser.getRoles().contains(UserRole.EMPLOYEE));
        Assert.assertNotNull("User \"newuser\" does not have a createUser defined after being saved", freshlyCreatedUser.getCreateUser());
        Assert.assertEquals("User \"newuser\" has wrong createUser set", adminUser, freshlyCreatedUser.getCreateUser());
        Assert.assertNotNull("User \"newuser\" does not have a createDate defined after being saved", freshlyCreatedUser.getCreateDate());
    }

    @Test(expected = org.springframework.orm.jpa.JpaSystemException.class)
    public void testExceptionForEmptyUsername() {
        User adminUser = userService.loadUser("admin");
        Assert.assertNotNull("Admin user could not be loaded from test data source", adminUser);

        User toBeCreatedUser = new User();
        userService.saveUser(toBeCreatedUser, adminUser);
    }

}
