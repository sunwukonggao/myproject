package cn.shop.gao.dao;

import cn.shop.gao.domain.Cart;
import cn.shop.gao.domain.Good;

import java.util.List;

/**
 * Created by gaojc on 2015/5/6.
 */
public interface CartDao {
    public Cart findByUserAndGood(Integer id, Good good);

    public void updateCart(Cart cart);

    public void saveCart(Cart cart);

    public Boolean checkCart(Integer id, Good good);

    public List<Cart> findByUser(Integer id);
}
