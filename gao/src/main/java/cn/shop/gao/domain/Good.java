package cn.shop.gao.domain;

import org.springframework.stereotype.Repository;

/**
 * Created by gaojc on 2015/5/6.
 */
@Repository("good")
public class Good {
    private Integer id;
    private Integer good_id;
    private String good_name;

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

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
