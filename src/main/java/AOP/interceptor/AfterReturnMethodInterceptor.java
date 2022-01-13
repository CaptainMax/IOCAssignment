package AOP.interceptor;

import AOP.MethodInterceptor;
import AOP.MethodInvocation;

import java.lang.reflect.Method;
public class AfterReturnMethodInterceptor implements MethodInterceptor {
    private Object aspectInstance;
    private Method aspectMethod;

    public AfterReturnMethodInterceptor(Object aspectInstance, Method aspectMethod) {
        this.aspectInstance = aspectInstance;
        this.aspectMethod = aspectMethod;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Exception {

        try {
            aspectMethod.setAccessible(true);
            Object res = methodInvocation.proceed();
            aspectMethod.invoke(aspectInstance);
            return res;
        } catch(Exception e){

        }
        return null;




    }
}
