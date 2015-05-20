package cn.shop.gao.web;

import cn.shop.gao.service.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by gaojc on 2015/5/6.
 */
@Controller
public class CartShop {
    private GoodService goodService;
    public GoodService getGoodService() {
        return goodService;
    }
    @Resource(name = "goodServiceImpl")
    public void setGoodService(GoodService goodService) {
        this.goodService = goodService;
    }
    @RequestMapping(value = "/allgood")
    public ModelAndView showAllGood()
    {  ModelMap model = new ModelMap();
        model.put("goods",  goodService.getAllGood());
        return new ModelAndView("good", model);
    }
}
