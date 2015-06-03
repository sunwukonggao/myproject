package cn.shop.gao.dao.impl;

import cn.shop.gao.dao.CartDao;
import cn.shop.gao.domain.Cart;
import cn.shop.gao.domain.Good;
import cn.shop.gao.domain.User;
import cn.shop.gao.mapper.CartMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

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

    public Cart findByUserAndGood(Integer id, Good good) {
        return cartMapper.checkCart(id, good.getGood_id());
    }

    public void updateCart(Cart cart) {
        cartMapper.updateCart(cart);

    }

    public void saveCart(Cart cart) {
        cartMapper.saveCart(cart);

    }

    public Boolean checkCart(Integer id, Good good) {
        if (cartMapper.checkCart(id, good.getGood_id()) != null)
            return true;
        else
            return false;
    }

    public List<Cart> findByUser(Integer id)
    {
        return cartMapper.findByUser(id);
    }
}
