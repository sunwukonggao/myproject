package cn.shop.gao.dao;


import cn.shop.gao.domain.User;

import java.util.List;

public interface UserDao {

    public List<User> findALLUser();

    public List<User> findALLUser(String sord, String sidx, int from, int length);

    public int findUserCount();

    public User findUser(User user);
}
