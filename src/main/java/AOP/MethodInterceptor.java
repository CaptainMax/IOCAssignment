package AOP;

public interface MethodInterceptor {
    Object invoke(MethodInvocation methodInvocation) throws Exception;
}
