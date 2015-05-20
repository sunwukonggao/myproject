package cn.shop.gao.dao;

import cn.shop.gao.domain.Good;

import java.util.List;

/**
 * Created by gaojc on 2015/5/6.
 */
public interface GoodDao {
    public Good findGood(Integer id);

    public List<Good> findAllGood();


}
