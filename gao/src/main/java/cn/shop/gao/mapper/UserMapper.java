package cn.shop.gao.mapper;

import cn.shop.gao.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends SqlMapper {

    public List<User> getUser();

    public List<User> getUserByPage(Map<String, Object> params);

    public int getUserCount();

    public User findUser(User user);
}

