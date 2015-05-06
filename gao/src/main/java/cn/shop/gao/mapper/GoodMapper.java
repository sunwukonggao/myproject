package cn.shop.gao.mapper;

import cn.shop.gao.domain.Good;
import cn.shop.gao.domain.User;

/**
 * Created by gaojc on 2015/5/6.
 */
public interface GoodMapper extends SqlMapper {
    public Good findGood(Integer id);

    public Boolean checkCart(User user, Good good);

}
