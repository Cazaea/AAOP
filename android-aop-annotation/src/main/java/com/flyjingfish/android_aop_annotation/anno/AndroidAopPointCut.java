package com.flyjingfish.android_aop_annotation.anno;


import static java.lang.annotation.RetentionPolicy.SOURCE;


import com.flyjingfish.android_aop_annotation.BasePointCut;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(SOURCE)
public @interface AndroidAopPointCut {
    Class<? extends BasePointCut> value();
}
