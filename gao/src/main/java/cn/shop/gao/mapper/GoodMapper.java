package cn.shop.gao.mapper;

import cn.shop.gao.domain.Good;

import java.util.List;

/**
 * Created by gaojc on 2015/5/6.
 */
public interface GoodMapper extends SqlMapper {
    public Good findGood(Integer id);

    public Integer countGood();

    public List<Good> findAllGood();

    public List<Good> findPageGood(Integer startIndex, Integer pageSize);

}
