package cn.shop.gao.web;


import cn.shop.gao.domain.User;
import cn.shop.gao.service.UserService;
import cn.shop.gao.tools.JqGridBaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

@Controller
public class Test {

    private UserService userservice;
    private List<User> userlistList;
    private HttpServletRequest request;
    private Map<InetSocketAddress, Map<String, String>> testxm;

    public UserService getUserservice() {
        return userservice;
    }

    @Resource(name = "userserviceimpl")
    public void setUserservice(UserService userservice) {
        this.userservice = userservice;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Resource
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @RequestMapping(value = "/test")
    public ModelAndView testRedis() {
        ModelMap model = new ModelMap();
        model.put("user", userservice.getAllUserredis());
        return new ModelAndView("test", model);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/jguser")
    @ResponseBody
    public JqGridBaseAction jgUser(JqGridBaseAction jqGridBaseAction) {
        userlistList = userservice.getAllUser();
        /*
        List<User> results = Collections.emptyList();
        int from = jqGridBaseAction.getRows() * (jqGridBaseAction.getPage() - 1);
        int length = jqGridBaseAction.getRows();
        String id = "";
        if (jqGridBaseAction.getSidx() == null) {
            id = "id";
        } else {
            id = jqGridBaseAction.getSidx();
        }
        jqGridBaseAction.setRecord(getResultSize());
        results = listResults(jqGridBaseAction.getSord(), id, from, length);
        jqGridBaseAction.setAllrow(results);
        jqGridBaseAction.setTotal((int) Math.ceil((double) jqGridBaseAction.getRecord() / (double) jqGridBaseAction.getRows()));
         */
        jqGridBaseAction.setAllrow(userlistList);
        return jqGridBaseAction;
    }

    public int getResultSize() {
        return userservice.getUserCount();
    }

    public List<User> listResults(String sord, String sidx, int from, int length) {
        return userservice.getUserByPage(sord, sidx, from, length);

    }
}
