package cn.shop.gao.service.impl;

import cn.shop.gao.dao.CartDao;
import cn.shop.gao.dao.GoodDao;
import cn.shop.gao.domain.Cart;
import cn.shop.gao.domain.Good;
import cn.shop.gao.service.GoodService;
import cn.shop.gao.tools.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gaojc on 2015/5/6.
 */
@Service("goodServiceImpl")
public class GoodServiceImpl implements GoodService {
    private GoodDao goodDao;
    private CartDao cartDao;

    public CartDao getCartDao() {
        return cartDao;
    }

    @Resource(name = "cartDaoImpl")
    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }


    public GoodDao getGoodDao() {
        return goodDao;
    }

    @Resource(name = "goodDaoImpl")
    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    public Good get(Integer id) {
        return null;
    }

    public Good getGood(Integer id) {
        return goodDao.findGood(id);
    }

    public List<Good> getAllGood() {
        return goodDao.findAllGood();
    }

    public Boolean isHaveCart(Integer id, Good good) {
        return cartDao.checkCart(id, good);
    }

    public Cart getByUserAndGood(Integer id, Good good) {
        return cartDao.findByUserAndGood(id, good);
    }

    public void updateCart(Cart cart) {
        cartDao.updateCart(cart);

    }

    public Page getPagedGoods(Integer pageNo, Integer pageSize) {
        long totalCount = goodDao.countGood();
        if (totalCount < 1)
            return new Page();
        // 实际查询返回分页对象
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List list = goodDao.findPageGood(startIndex, pageSize);
        return new Page(startIndex, totalCount, pageSize, list);
    }

    public void saveCart(Cart cart) {
        cartDao.saveCart(cart);

    }

    public List<Cart> findByUser(Integer id) {
        return cartDao.findByUser(id);
    }
}
