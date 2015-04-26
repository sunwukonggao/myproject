package cn.shop.gao.domain;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("user")
public class User implements Serializable {
    private static final long serialVersionUID = 2L;
    private Integer id;
    private String user_id;
    private String passwd;
    private String state;
    private String name;
    private String sex;
    private String idcard;
    private Integer birth;
    private Integer group_id;
    private Integer academe_id;


    private String right_content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getBirth() {
        return birth;
    }

    public void setBirth(Integer birth) {
        this.birth = birth;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getAcademe_id() {
        return academe_id;
    }

    public void setAcademe_id(Integer academe_id) {
        this.academe_id = academe_id;
    }

    public String getRight_content() {
        return right_content;
    }

    public void setRight_content(String right_content) {
        this.right_content = right_content;
    }
}
