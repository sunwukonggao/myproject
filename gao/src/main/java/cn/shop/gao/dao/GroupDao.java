package cn.shop.gao.dao;

/**
 * Created by gaojc on 2015/4/24.
 */
public interface GroupDao {
    public String findRight(Integer id);

    public void updateRight(Integer group_id, String right_content);
}
