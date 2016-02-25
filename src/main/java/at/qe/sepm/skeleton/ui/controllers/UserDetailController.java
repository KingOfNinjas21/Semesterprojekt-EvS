package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.common.model.User;
import at.qe.sepm.skeleton.services.UserService;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ViewScoped
public class UserDetailController {

    @Autowired
    private UserService userService;

    private User user;

    public void setUser(User user) {
        this.user = user;
        doReloadUser();
    }

    public User getUser() {
        return user;
    }

    public void doReloadUser() {
        user = userService.loadUser(user.getUsername());
    }

    public void doSaveUser() {
        user = this.userService.saveUser(user);
    }

}
