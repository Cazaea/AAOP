package com.flyjingfish.test_lib.mycut;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flyjingfish.android_aop_annotation.ProceedJoinPoint;
import com.flyjingfish.android_aop_annotation.anno.AndroidAopMatchClassMethod;
import com.flyjingfish.android_aop_annotation.base.MatchClassMethod;

@AndroidAopMatchClassMethod(
        targetClassName = "com.flyjingfish.test_lib.BaseActivity",
        methodName = {"onResume"}
)
public class MatchActivityMethod2 implements MatchClassMethod {
    @Nullable
    @Override
    public Object invoke(@NonNull ProceedJoinPoint joinPoint, @NonNull String methodName) {
        return null;
    }
}
