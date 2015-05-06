package cn.shop.gao.dao;

import cn.shop.gao.domain.Good;
import cn.shop.gao.domain.User;

/**
 * Created by gaojc on 2015/5/6.
 */
public interface GoodDao {
    public Good findGood(Integer id);

    public Boolean checkCart(User user, Good good);


}
