package cn.shop.gao.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseDao<T> {
    @SuppressWarnings("unused")
    private Class<T> entityClass;

    /**
     * 通过反射获取子类确定的泛型类
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public BaseDao() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }
}
