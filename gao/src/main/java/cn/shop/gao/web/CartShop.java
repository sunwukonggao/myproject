package cn.shop.gao.web;

import cn.shop.gao.domain.Cart;
import cn.shop.gao.domain.Good;
import cn.shop.gao.service.GoodService;
import cn.shop.gao.tools.Page;
import cn.shop.gao.tools.ResultTypeEnum;
import cn.shop.gao.tools.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

   /* @RequestMapping(value = "/goods")
    public ModelAndView showAllGood() {
        ModelMap model = new ModelMap();
        model.put("goods", goodService.getAllGood());
        return new ModelAndView("good", model);
    }*/

    @RequestMapping(value = "/add/{good_id}")
    public String addCart(@PathVariable("good_id") Integer good_id, HttpServletResponse response) {
        Cart cart = new Cart();
        String user = (String) request.getSession().getAttribute(SessionHelper.UserHandler);
        if (null == user) {
            Cookie cookie = new Cookie("beforeLoginCookie_" + good_id, "10");
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            Good goods = goodService.getGood(good_id);
            Integer id = (Integer)request.getSession().getAttribute("id");
            if (null != goods) {
                if (goodService.isHaveCart(id, goods)) {
                    Cart oldShoppingCart = goodService.getByUserAndGood(id, goods);
                    oldShoppingCart.setAmount(12);
                    goodService.updateCart(oldShoppingCart);
                } else {
                    Cart shoppingCartTemp = new Cart();
                    shoppingCartTemp.setAmount(10);
                    shoppingCartTemp.setUser_id(id);
                    shoppingCartTemp.setGood_id(goods.getGood_id());
                    goodService.saveCart(shoppingCartTemp);
                }
            }
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart")
    public ModelAndView Cart() {
        Cookie[] cookies = request.getCookies();
        ModelMap model = new ModelMap();
        model.put("cookies", cookies);
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

    @RequestMapping(value = "/goods/{pageNo}", method = RequestMethod.GET)
    public ModelAndView listBoardTopics(@PathVariable("pageNo") Integer pageNo) {
        ModelAndView view = new ModelAndView();
        pageNo = pageNo == null ? 1 : pageNo;
        Page pagedGoods = goodService.getPagedGoods(pageNo, 3);
        view.addObject("pagedGoods", pagedGoods);
        view.setViewName("/listGoods");
        return view;
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public String listBoardTopicsNoPage() {
        return "redirect:/goods/1";
    }

    @cn.shop.gao.annotation.Login(ResultTypeEnum.page)
    @RequestMapping(value = "/pay")
    public ModelAndView Pay() {
        ModelMap model = new ModelMap();
        model.put("carts", goodService.findByUser((Integer)request.getSession().getAttribute("id")));
        return new ModelAndView("oder", model);
    }
}
