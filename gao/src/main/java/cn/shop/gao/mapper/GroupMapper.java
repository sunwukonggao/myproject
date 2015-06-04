package cn.shop.gao.mapper;

/**
 * Created by gaojc on 2015/4/24.
 */
public interface GroupMapper extends SqlMapper {
    public String getRight(Integer id);

    public void updateRight(Integer group_id, String right_content);
}
