package cn.shop.gao.web;

import cn.shop.gao.annotation.Login;
import cn.shop.gao.tools.ResultTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Index {
    @Login(ResultTypeEnum.page)
    @RequestMapping(value = "")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @Login(ResultTypeEnum.page)
    @RequestMapping(value = "/index")
    public ModelAndView index1() {
        return new ModelAndView("index");
    }


}
