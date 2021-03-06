package cn.shop.gao.service;

import cn.shop.gao.domain.Cart;
import cn.shop.gao.domain.Good;
import cn.shop.gao.tools.Page;

import java.util.List;

/**
 * Created by gaojc on 2015/5/6.
 */
public interface GoodService {
    public Good getGood(Integer id);

    public List<Good> getAllGood();

    public Boolean isHaveCart(Integer id, Good good);

    public Cart getByUserAndGood(Integer id, Good good);

    public void updateCart(Cart cart);

    public Page getPagedGoods(Integer pageNo, Integer pageSize);

    public void saveCart(Cart cart);

    public List<Cart> findByUser(Integer id);
}
