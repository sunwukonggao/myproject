package cn.shop.gao.domain;

import org.springframework.stereotype.Repository;

/**
 * Created by gaojc on 2015/5/6.
 */
@Repository("good")
public class Good {
    private Integer id;
    private Integer good_id;

    public Integer getGood_id() {
        return good_id;
    }

    public void setGood_id(Integer good_id) {
        this.good_id = good_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
