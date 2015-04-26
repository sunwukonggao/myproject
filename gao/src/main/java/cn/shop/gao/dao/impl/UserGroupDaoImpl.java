package cn.shop.gao.dao.impl;


import cn.shop.gao.dao.UserGroupDao;
import cn.shop.gao.mapper.UserGroupMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gaojc on 2015/4/24.
 */
@Repository("userGroupDaoimpl")
public class UserGroupDaoImpl implements UserGroupDao {
    private UserGroupMapper userGroupMapper;

    public UserGroupMapper getUserGroupMapper() {
        return userGroupMapper;
    }

    @Resource(name = "userGroupMapper")
    public void setUserGroupMapper(UserGroupMapper userGroupMapper) {
        this.userGroupMapper = userGroupMapper;
    }

    public List<Integer> getGroup(Integer id) {
        return userGroupMapper.getGroup(id);
    }
}
