package cn.shop.gao.dao;

import java.util.List;

/**
 * Created by gaojc on 2015/4/24.
 */
public interface UserGroupDao {
    public List<String> getGroupRight(Integer id);
}
