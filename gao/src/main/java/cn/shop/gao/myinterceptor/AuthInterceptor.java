package cn.shop.gao.myinterceptor;

/**
 * Created by gaojc on 2015/4/21.
 */

import cn.shop.gao.annotation.IsLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            IsLogin isLogin = ((HandlerMethod) handler).getMethodAnnotation(IsLogin.class);

            //没有声明需要权限,或者声明不验证权限
            if (isLogin == null || isLogin.validate() == false)
                return true;
            else {
                //在这里实现自己的权限验证逻辑
                if (request.getSession().getAttribute("user_id") != null)//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
                {
                    System.out.println("AuthInterceptor");
                    request.getRequestDispatcher("index").forward(request, response);
                    return false;
                }
                return true;
            }
        } else
            return true;
    }
}