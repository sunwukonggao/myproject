package cn.shop.gao.myinterceptor;

import cn.shop.gao.annotation.Login;
import cn.shop.gao.tools.ResultTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginAnnotationInterceptor extends HandlerInterceptorAdapter {
    final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handler2 = (HandlerMethod) handler;
        Login login = handler2.getMethodAnnotation(Login.class);

        if (null == login) {
            // 没有声明权限,放行
            return true;
        }
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user_id");

        if (null == user) {
            // 需要登录
            if (login.value() == ResultTypeEnum.page) {
                //采用传统页面进行登录提示
                if (request.getQueryString() != null) {
                    request.getSession().setAttribute("toUrl", request.getRequestURI() + "?" + request.getQueryString());
                } else {
                    request.getSession().setAttribute("toUrl", request.getRequestURI());
                }
                response.sendRedirect("login");
                // request.getRequestDispatcher("login").forward(request, response);
            } else if (login.value() == ResultTypeEnum.json) {
                //采用ajax方式的进行登录提示
                request.getRequestDispatcher("login").forward(request, response);
            }
            return false;
        }
        return true;
    }
}
