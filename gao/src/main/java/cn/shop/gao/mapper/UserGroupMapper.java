package cn.shop.gao.mapper;

import java.util.List;

/**
 * Created by gaojc on 2015/4/24.
 */
public interface UserGroupMapper extends SqlMapper {
    public List<String> getGroupRight(Integer id);
}
