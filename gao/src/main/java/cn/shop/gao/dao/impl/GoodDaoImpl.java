package cn.shop.gao.dao.impl;

import cn.shop.gao.dao.GoodDao;
import cn.shop.gao.domain.Good;
import cn.shop.gao.mapper.GoodMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gaojc on 2015/5/6.
 */
@Repository("goodDaoImpl")
public class GoodDaoImpl implements GoodDao {
    private GoodMapper goodMapper;

    public GoodMapper getGoodMapper() {
        return goodMapper;
    }

    @Resource(name = "goodMapper")
    public void setGoodMapper(GoodMapper goodMapper) {
        this.goodMapper = goodMapper;
    }


    public Good findGood(Integer id) {
        return goodMapper.findGood(id);
    }

    public List<Good> findPageGood(Integer pageNo, Integer pageSize) {
        return goodMapper.findPageGood(pageNo, pageSize);
    }

    public List<Good> findAllGood() {
        return goodMapper.findAllGood();
    }

    public Integer countGood() {
        return goodMapper.countGood();
    }


}
