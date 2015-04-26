package cn.shop.gao.dao.impl;

import cn.shop.gao.dao.UserDao;
import cn.shop.gao.domain.User;
import cn.shop.gao.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userdaoimpl")
public class UserDaoImpl implements UserDao {
    private UserMapper usermapper;

    public UserMapper getUsermapper() {
        return usermapper;
    }

    @Resource(name = "userMapper")
    public void setUsermapper(UserMapper usermapper) {
        this.usermapper = usermapper;
    }

    public List<User> findALLUser() {
        return usermapper.getUser();

    }

    public List<User> findALLUser(String sord, String sidx, int from, int length) {
        Map<String, Object> params = new HashMap<String, Object>(4);
        params.put("sord", sord);
        params.put("sidx", sidx);
        params.put("from", from);
        params.put("length", length);
        return usermapper.getUserByPage(params);

    }

    public int findUserCount() {
        return usermapper.getUserCount();
    }

    public User findUser(User user) {
        return usermapper.findUser(user);
    }
}
