package cn.shop.gao.tools;

import cn.shop.gao.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by gaojc on 2015/1/29.
 */
@Repository("loginAjax")
public class LoginAjax {
    private String islogin;
    private String toUrl;
    private User user;
    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }
    public String getIslogin() {
        return islogin;
    }

    public void setIslogin(String islogin) {
        this.islogin = islogin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
