package cn.shop.gao.web;

import cn.shop.gao.domain.Cart;
import cn.shop.gao.service.GoodService;
import cn.shop.gao.tools.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gaojc on 2015/5/6.
 */
@Controller
public class CartShop {
    private GoodService goodService;
    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }


    public GoodService getGoodService() {
        return goodService;
    }

    @Resource(name = "goodServiceImpl")
    public void setGoodService(GoodService goodService) {
        this.goodService = goodService;
    }

    @RequestMapping(value = "/goods")
    public ModelAndView showAllGood() {
        ModelMap model = new ModelMap();
        model.put("goods", goodService.getAllGood());
        return new ModelAndView("good", model);
    }

    @RequestMapping(value = "/add/{good_id}")
    public String addCart(@PathVariable("good_id") Integer good_id, HttpServletResponse response) {
        Cart cart = new Cart();
        String user = (String) request.getSession().getAttribute(SessionHelper.UserHandler);
        if (null == user) {
            Cookie cookie = new Cookie("beforeLoginCookie_" + good_id, "10");
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            cart.setGood_id(good_id);
            cart.setUser_id(1);
            cart.setAmount(12);
            goodService.saveCart(cart);
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart")
    public ModelAndView Cart() {
        Cookie[] cookies = request.getCookies();
        ModelMap model=new ModelMap();
        model.put("cookies",cookies);
        return new ModelAndView("cartok", model);
    }

    @RequestMapping(value = "/removec")
    public String removeAllCookies(HttpServletResponse response) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null || cookies.length < 0) {
            System.out.println("没有cookie");
        } else {
            System.out.println("开始删除cookies..");
            for (Cookie c : cookies) {
                if (c.getName().startsWith("beforeLoginCookie")) {
                    c.setPath("/");
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
        return "redirect:/cart";
    }
}
