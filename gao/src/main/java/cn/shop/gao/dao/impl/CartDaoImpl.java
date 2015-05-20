package cn.shop.gao.dao.impl;

import cn.shop.gao.dao.CartDao;
import cn.shop.gao.domain.Cart;
import cn.shop.gao.domain.Good;
import cn.shop.gao.domain.User;
import cn.shop.gao.mapper.CartMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by gaojc on 2015/5/6.
 */
@Repository("cartDaoImpl")
public class CartDaoImpl implements CartDao {
    private CartMapper cartMapper;

    public CartMapper getCartMapper() {
        return cartMapper;
    }

    @Resource(name = "cartMapper")
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    public Cart findByUserAndGood(User user, Good good) {
        return cartMapper.checkCart(user.getId(), good.getGood_id());
    }

    public void updateCart(Cart cart) {
        cartMapper.updateCart(cart);

    }

    public void saveCart(Cart cart) {
        cartMapper.saveCart(cart);

    }

    public Boolean checkCart(User user, Good good) {
        if (cartMapper.checkCart(user.getId(), good.getGood_id()) != null)
            return true;
        else
            return false;
    }

}
