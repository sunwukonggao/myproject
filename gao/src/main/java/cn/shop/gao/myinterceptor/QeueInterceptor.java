package cn.shop.gao.myinterceptor;

/**
 * Created by gaojc on 2015/4/21.
 */

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QeueInterceptor extends HandlerInterceptorAdapter {
    private int i = 0;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (i < 2) {
            i++;
            System.out.println("preHandle  " + i);
            return true;
        } else {
            request.getRequestDispatcher("login").forward(request, response);
            return false;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        i--;
        System.out.println("postHandle  " + i);
    }
}