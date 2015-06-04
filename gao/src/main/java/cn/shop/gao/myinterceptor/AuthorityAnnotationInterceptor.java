package cn.shop.gao.myinterceptor;

import cn.shop.gao.annotation.Authority;
import cn.shop.gao.tools.AuthorityHelper;
import cn.shop.gao.tools.AuthorityType;
import cn.shop.gao.tools.ResultTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLEncoder;

public class AuthorityAnnotationInterceptor extends HandlerInterceptorAdapter {

    final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handler2 = (HandlerMethod) handler;
        Authority authority = handler2.getMethodAnnotation(Authority.class);

        if (null == authority) {
            //没有声明权限,放行
            return true;
        }

        HttpSession session = request.getSession();
        boolean aflag = false;

        for (AuthorityType authorityType : authority.authorityTypes()) {
            if (AuthorityHelper.hasAuthority(authorityType.getIndex(), session.getAttribute("right").toString()) == true) {
                aflag = true;
                break;
            }
        }

        if (false == aflag) {

            if (authority.resultType() == ResultTypeEnum.page) {
                //采用传统页面进行提示
                StringBuilder sb = new StringBuilder();
                sb.append(request.getContextPath());
                sb.append("/index?oprst=false&opmsg=").append(URLEncoder.encode("没有权限", "utf-8"));
                response.sendRedirect(sb.toString());
            } else if (authority.resultType() == ResultTypeEnum.json) {
                //采用ajax方式的进行提示
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=UTF-8");
                OutputStream out = response.getOutputStream();
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, "utf-8"));
                pw.println("{\"result\":false,\"code\":12,\"errorMessage\":\"没有权限\"}");
                pw.flush();
                pw.close();
            }

            return false;

        }

        return true;

    }

}