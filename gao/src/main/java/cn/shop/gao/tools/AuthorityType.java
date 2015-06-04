package cn.shop.gao.tools;

/**
 * Created by gaojc on 2015/4/22.
 */
public enum AuthorityType {
    // 包含了枚举的中文名称, 枚举的索引值,每个枚举值中包含了权限中文名称和权限索引值(即权限位).
    SALES_ORDER_CREATE("创建订单", 0),
    SALES_ORDER_FIND("查看订单", 1),
    SALES_ORDER_MODIFY("修改订单", 2),
    SALES_ORDER_DELETE("删除订单", 3),;
    private String name;
    private int index;

    AuthorityType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
