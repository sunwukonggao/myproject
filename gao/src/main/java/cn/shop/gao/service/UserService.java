package cn.shop.gao.service;


import cn.shop.gao.domain.User;
import net.rubyeye.xmemcached.exception.MemcachedException;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public interface UserService {
    public List<User> getAllUser();

    public User getUser();

    public User getUser(User user);

    public List<User> getUserByPage(String sord, String sidx, int from, int length);

    public int getUserCount();

    public String  getUserRight(Integer user_id);

    public List<Serializable> getAllUserredis();

    public Map<InetSocketAddress, Map<String, String>> testxm() throws InterruptedException, MemcachedException, TimeoutException;

}
