package cn.shop.gao.annotation;

import cn.shop.gao.tools.ResultTypeEnum;

import java.lang.annotation.*;

/**
 * Created by gaojc on 2015/4/22.
 * 直接访问login登陆页面处理
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
    ResultTypeEnum value() default ResultTypeEnum.page;
}