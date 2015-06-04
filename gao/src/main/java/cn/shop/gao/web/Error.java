package cn.shop.gao.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by gaojc on 2015/6/4.
 */
@Controller
@RequestMapping(value = "/error")
public class Error {
    @RequestMapping(value = "/404")
    public ModelAndView index1() {
        return new ModelAndView("404");
    }
}
