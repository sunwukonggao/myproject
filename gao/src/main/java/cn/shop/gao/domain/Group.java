package cn.shop.gao.domain;

/**
 * Created by gaojc on 2015/4/24.
 */
public class Group {
    private  Integer id;
    private  String group_name;
    private  String right_content;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRight_content() {
        return right_content;
    }

    public void setRight_content(String right_content) {
        this.right_content = right_content;
    }
}
