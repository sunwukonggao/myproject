package cn.shop.gao.dao.impl;



import cn.shop.gao.dao.GroupDao;
import cn.shop.gao.mapper.GroupMapper;

import javax.annotation.Resource;

/**
 * Created by gaojc on 2015/4/24.
 */
public class GroupDaoImpl implements GroupDao {
    private GroupMapper groupMapper;

    public GroupMapper getGroupMapper() {
        return groupMapper;
    }
    @Resource(name = "groupMapper")
    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    public String findRight(Integer id) {
        return groupMapper.getRight(id);
    }
}
