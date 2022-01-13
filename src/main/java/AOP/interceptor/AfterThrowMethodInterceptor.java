package AOP.interceptor;

import AOP.MethodInterceptor;
import AOP.MethodInvocation;

import java.lang.reflect.Method;
public class AfterThrowMethodInterceptor implements MethodInterceptor {
    private Object aspectInstance;
    private Method aspectMethod;

    public AfterThrowMethodInterceptor(Object aspectInstance, Method aspectMethod) {
        this.aspectInstance = aspectInstance;
        this.aspectMethod = aspectMethod;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Exception {

        try {
        } catch (Exception e) {
            aspectMethod.setAccessible(true);
            Object res = methodInvocation.proceed();
            aspectMethod.invoke(aspectInstance);
            return res;
        }
        return null;
    }

//        aspectMethod.invoke(aspectInstance);
//        return methodInvocation.proceed();

}
