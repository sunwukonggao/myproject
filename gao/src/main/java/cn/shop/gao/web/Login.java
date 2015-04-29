package cn.shop.gao.web;

import cn.shop.gao.annotation.IsLogin;
import cn.shop.gao.domain.User;
import cn.shop.gao.service.UserService;
import cn.shop.gao.tools.LoginAjax;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gaojc on 2015/1/29.
 */
@Controller
public class Login {
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(Login.class);
    private LoginAjax loginAjax;
    private UserService userService;
    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public LoginAjax getLoginAjax() {
        return loginAjax;
    }

    @Autowired
    public void setLoginAjax(LoginAjax loginAjax) {
        this.loginAjax = loginAjax;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @IsLogin
    @RequestMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @IsLogin
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/checklogin", method = RequestMethod.POST)
    @ResponseBody
    public LoginAjax loginCheck(User user) {
        try {
            if (!checkName(user.getUser_id()) || user.getUser_id() == "") {
                loginAjax.setIslogin("false");
                return loginAjax;
            } else {
                User userResult = userService.getUser(user);
                if (userResult != null) {
                    request.getSession().setAttribute("right",userService.getUserRight(userResult.getId()));
                    request.getSession().setAttribute("user_id", userResult.getUser_id());
                    request.getSession().setAttribute("name", userResult.getName());
                    request.getSession().setAttribute("user_group", userResult.getGroup_id());
                    loginAjax.setIslogin("true");
                    loginAjax.setUser(userResult);
                    logger.info("用户" + userResult.getUser_id()
                            + "登陆登陆成功，来自"
                            + request.getRemoteAddr());
                } else {
                    loginAjax.setIslogin("false");
                    logger.error("用户" + user.getUser_id()
                            + "尝试登陆失败，来自"
                            + request.getRemoteAddr());
                }

                return loginAjax;
            }
        } catch (Exception e) {
            logger.error("用户" + user.getUser_id() + "尝试登陆失败，来自"
                    + request.getRemoteAddr());
            loginAjax.setIslogin("false");
            return loginAjax;
        }
    }

    public Boolean checkName(String name) {
        String check = "^[a-z0-9A-Z]+$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(name);
        boolean isMatched = matcher.matches();
        return isMatched;
    }

}
