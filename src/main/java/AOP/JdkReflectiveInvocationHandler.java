package AOP;

import AOP.annotation.*;
import AOP.interceptor.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JdkReflectiveInvocationHandler implements InvocationHandler {
    private Object realInstance;
    private Object aspectInstance;

    public JdkReflectiveInvocationHandler(Object realInstance, Object aspectInstance) {
        this.realInstance = realInstance;
        this.aspectInstance = aspectInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> clazz = aspectInstance.getClass();
        List<MethodInterceptor> methodInterceptorList = new ArrayList<>();
        for(Method aspectMethod : clazz.getDeclaredMethods()){
            for(Annotation annotation : aspectMethod.getDeclaredAnnotations()){
//                try{
//                    if(annotation.annotationType() == Before.class){
//                        methodInterceptorList.add(new BeforeMethodInterceptor(aspectInstance, aspectMethod));
//                    } else if(annotation.annotationType() == AfterReturn.class) {
//                        methodInterceptorList.add(new AfterReturnMethodInterceptor(aspectInstance, aspectMethod));
//                    }
//                } catch (Exception e){
//                    if(annotation.annotationType() == AfterReturn.class) {
//                        methodInterceptorList.add(new AfterReturnMethodInterceptor(aspectInstance, aspectMethod));
//                    }
//                }
//                finally {
//                    if(annotation.annotationType() == After.class){
//                        methodInterceptorList.add(new AfterMethodInterceptor(aspectInstance, aspectMethod));
//                    }
//                }
                if(annotation.annotationType() == Before.class){
                    methodInterceptorList.add(new BeforeMethodInterceptor(aspectInstance, aspectMethod));
                } else if(annotation.annotationType() == After.class){
                    methodInterceptorList.add(new AfterMethodInterceptor(aspectInstance, aspectMethod));
                } else if(annotation.annotationType() == AfterReturn.class) {
                    methodInterceptorList.add(new AfterReturnMethodInterceptor(aspectInstance, aspectMethod));
                } else if(annotation.annotationType() == AfterThrow.class){
                    methodInterceptorList.add(new AfterThrowMethodInterceptor(aspectInstance, aspectMethod));
                } else if(annotation.annotationType() == Around.class){
                    methodInterceptorList.add(new AroundMethodInterceptor(aspectInstance, aspectMethod));
                }
            }
        }

        ProxyMethodInvocation proxyMethodInvocation = new ProxyMethodInvocation(
                methodInterceptorList,
                realInstance,
                method
        );
        return proxyMethodInvocation.proceed();
    }
}
