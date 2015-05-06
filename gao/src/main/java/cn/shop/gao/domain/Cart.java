package cn.shop.gao.domain;

import org.springframework.stereotype.Repository;

/**
 * Created by gaojc on 2015/5/6.
 */
@Repository("cart")
public class Cart {
    private Integer id;
    private Integer user_id;
    private Integer good_id;
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
