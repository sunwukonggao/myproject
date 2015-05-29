package cn.shop.gao.web;

import cn.shop.gao.annotation.Login;
import cn.shop.gao.tools.Page;
import cn.shop.gao.tools.ResultTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/goods/page/{page_id}", method = RequestMethod.GET)
    public ModelAndView listBoardTopics(@PathVariable("page_id") Integer page_id, Integer pageNo) {
        ModelAndView view =new ModelAndView();
        pageNo = pageNo==null?1:pageNo;
      //  Page pagedTopic = forumService.getPagedTopics(boardId, pageNo,
        //        CommonConstant.PAGE_SIZE);
        //sview.addObject("pagedTopic", pagedTopic);
        view.setViewName("/listGoods");
        return view;
    }
}
