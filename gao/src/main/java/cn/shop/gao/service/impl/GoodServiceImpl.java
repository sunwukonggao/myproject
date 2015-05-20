package cn.shop.gao.service.impl;

import cn.shop.gao.dao.GoodDao;
import cn.shop.gao.domain.Cart;
import cn.shop.gao.domain.Good;
import cn.shop.gao.domain.User;
import cn.shop.gao.service.GoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gaojc on 2015/5/6.
 */
@Service("goodServiceImpl")
public class GoodServiceImpl implements GoodService {
    public GoodDao getGoodDao() {
        return goodDao;
    }
    @Resource(name="goodDaoImpl")
    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    private GoodDao goodDao;

    public Good get(Integer id) {
        return null;
    }

    public Good getGood(Integer id) {
        return null;
    }

    public List<Good> getAllGood() {
        return goodDao.findAllGood();
    }

    public Boolean isHaveCart(User user, Good good) {
        return null;
    }

    public Cart getByUserAndGood(User user, Good good) {
        return null;
    }

    public void updateCart(Cart cart) {

    }

    public void saveCart(Cart cart) {

    }
}
