package cn.shop.gao.annotation;

import cn.shop.gao.tools.ResultTypeEnum;

import java.lang.annotation.*;

/**
 * Created by gaojc on 2015/4/22.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
    ResultTypeEnum value() default ResultTypeEnum.json;
}