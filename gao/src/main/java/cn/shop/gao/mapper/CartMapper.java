package cn.shop.gao.mapper;

import cn.shop.gao.domain.Cart;
import cn.shop.gao.domain.Good;
import cn.shop.gao.domain.User;

/**
 * Created by gaojc on 2015/5/6.
 */
public interface CartMapper extends SqlMapper {

    public Cart findByUserAndGood(User user, Good good);

    public void updateCart(Cart cart);

    public void saveCart(Cart cart);

    public Cart checkCart(Integer user_id, Integer good_id);
}
