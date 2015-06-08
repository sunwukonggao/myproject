package cn.shop.gao.web;

import cn.shop.gao.annotation.Login;
import cn.shop.gao.service.UserService;
import cn.shop.gao.tools.AuthorityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by gaojc on 2015/4/21.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userservice;

    public UserService getUserservice() {
        return userservice;
    }

    @Autowired()
    public void setUserservice(UserService userservice) {
        this.userservice = userservice;
    }

    @Login
    @RequestMapping(value = "/user")
    public ModelAndView allUser() {
        ModelMap model = new ModelMap();
        model.put("user", userservice.getAllUserredis());
        return new ModelAndView("user", model);
    }

    @Login
    @RequestMapping(value = "/addright")
    public String GroupRight() {
        return "addright";
    }

    @Login
    @RequestMapping(value = "/uright")
    public String updateGroupRight(String[] Right, HttpServletRequest request) {
        System.out.println(request.getSession().getAttribute("right").toString());
        userservice.updateGroupRight((Integer) request.getSession().getAttribute("id"), AuthorityHelper.updateAuthority(Right, request.getSession().getAttribute("right").toString()));
        return "addright";
    }
}
