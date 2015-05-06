package cn.shop.gao.service;

import cn.shop.gao.domain.Cart;
import cn.shop.gao.domain.Good;
import cn.shop.gao.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by gaojc on 2015/5/6.
 */
@Service
public interface GoodService {
    public Good getGood(Integer id);

    public Boolean isHaveCart(User user, Good good);

    public Cart getByUserAndGood(User user, Good good);

    public void updateCart(Cart cart);

    public void saveCart(Cart cart);
}
