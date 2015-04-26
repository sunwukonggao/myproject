package cn.shop.gao.annotation;

/**
 * Created by gaojc on 2015/4/22.
 */


import cn.shop.gao.tools.AuthorityType;
import cn.shop.gao.tools.ResultTypeEnum;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authority {
    AuthorityType[] authorityTypes();

    ResultTypeEnum resultType() default ResultTypeEnum.page;
}
