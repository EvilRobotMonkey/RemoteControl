package com.orbyun.net.netstate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2019-10-1016:54
 * desc   :
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NetWork {
    NetType netType() default NetType.AUTO;
}
