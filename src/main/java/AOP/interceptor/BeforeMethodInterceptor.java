package AOP.interceptor;

import java.lang.reflect.Method;
import AOP.MethodInterceptor;
import AOP.MethodInvocation;

public class BeforeMethodInterceptor implements MethodInterceptor {
    private Object aspectInstance;
    private Method aspectMethod;

    public BeforeMethodInterceptor(Object aspectInstance, Method aspectMethod) {
        this.aspectInstance = aspectInstance;
        this.aspectMethod = aspectMethod;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Exception {
        aspectMethod.setAccessible(true);
        aspectMethod.invoke(aspectInstance);
        return methodInvocation.proceed();
    }
}
