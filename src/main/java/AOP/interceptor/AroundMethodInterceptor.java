package AOP.interceptor;

import AOP.MethodInterceptor;
import AOP.MethodInvocation;

import java.lang.reflect.Method;
public class AroundMethodInterceptor implements MethodInterceptor {
    private Object aspectInstance;
    private Method aspectMethod;

    public AroundMethodInterceptor(Object aspectInstance, Method aspectMethod) {
        this.aspectInstance = aspectInstance;
        this.aspectMethod = aspectMethod;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Exception {
        aspectMethod.setAccessible(true);
        Object retVal = methodInvocation.proceed();
        return retVal;
    }
}
