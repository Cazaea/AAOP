package com.flyjingfish.android_aop_annotation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public final class ProceedJoinPoint {
    @Nullable
    public Object[] args;
    @Nullable
    private Object[] originalArgs;
    @Nullable
    public Object target;
    @NotNull
    public Class<?> targetClass;
    private Method targetMethod;
    private Method originalMethod;
    private AopMethod targetAopMethod;
    private OnInvokeListener onInvokeListener;
    private boolean hasNext;
    private final int argCount;

    public ProceedJoinPoint(@NotNull Class<?> targetClass,Object[] args) {
        this.targetClass = targetClass;
        this.args = args;
        if (args != null){
            this.originalArgs = args.clone();
        }
        this.argCount = args != null ? args.length : 0;
    }

    /**
     * 调用切点方法内代码
     * @return 返回切点方法返回值
     */
    @Nullable
    public Object proceed(){
        return proceed(args);
    }

    /**
     * 调用切点方法内代码
     * @param args 切点方法参数数组
     * @return 返回切点方法返回值
     */
    @Nullable
    public Object proceed(Object... args){
        this.args = args;
        if (argCount > 0){
            if (args == null || args.length != argCount){
                throw new IllegalArgumentException("proceed 所参数个数不对");
            }
        }
        try {
            Object returnValue = null;
            if (!hasNext){
                returnValue = targetMethod.invoke(target,args);
            }
            if (onInvokeListener != null){
                onInvokeListener.onInvoke(returnValue);
            }
            return returnValue;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getTargetException());
        }
    }

    /**
     *
     * @return 切点方法相关信息
     */
    @NotNull
    public AopMethod getTargetMethod() {
        return targetAopMethod;
    }

    void setTargetMethod(Method targetMethod) {
        this.targetMethod = targetMethod;
    }

    void setOriginalMethod(Method originalMethod) {
        this.originalMethod = originalMethod;
        targetAopMethod = new AopMethod(originalMethod);
    }

    /**
     *
     * @return 切点方法所在对象，如果方法为静态的，此值为null
     */
    @Nullable
    public Object getTarget() {
        return target;
    }

    /**
     *
     * @return 切点方法所在类 Class
     */
    @NotNull
    public Class<?> getTargetClass() {
        return targetClass;
    }

    void setTargetClass(@NotNull Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    interface OnInvokeListener{
        void onInvoke(Object returnValue);
    }

    void setOnInvokeListener(OnInvokeListener onInvokeListener) {
        this.onInvokeListener = onInvokeListener;
    }

    void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    /**
     * 和 {@link ProceedJoinPoint#args} 相比，返回的引用地址不同，但数组里边的对象一致
     * @return 最开始进入方法时的参数
     */
    @Nullable
    public Object[] getOriginalArgs() {
        return originalArgs;
    }
}
