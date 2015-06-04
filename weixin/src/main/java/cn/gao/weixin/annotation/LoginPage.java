package cn.gao.weixin.annotation;

/**
 * Created by gaojc on 2015/4/21.
 */

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginPage {
    boolean validate() default true;
}