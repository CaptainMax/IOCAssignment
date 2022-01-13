package AOP;

import AOP.annotation.*;

import java.lang.reflect.Proxy;

public class ProxyMainFunction {
    public static void main(String[] args) {
        Calculator c1 = new calculatorImple1();
        Calculator proxy = (Calculator) Proxy.newProxyInstance(
                c1.getClass().getClassLoader(),
                new Class[]{Calculator.class},
                new JdkReflectiveInvocationHandler(c1, new CalculatorAspect())
        );

        proxy.run();
    }
}

interface Calculator{
    void run();
}

class calculatorImple1 implements Calculator{
    @Override
    public void run() {
        System.out.println("This is main run");
    }
}

class CalculatorAspect{
    @After
    public void afterLogic() {
        System.out.println("This is after logic 1");
    }

    @After
    public void afterLogic2() {
        System.out.println("This is after logic 2");
    }

    @Before
    public void beforeLogic(){
        System.out.println("This is before logic 1");
    }

    @Before
    public void beforeLogic2(){
        System.out.println("This is before logic 2");
    }

    @AfterReturn
    public void afterReturn(){
        System.out.println("This is after return logic 1");
    }


    @AfterThrow
    public void afterThrowLogic(){
        System.out.println("This is after throw logic 1");
    }

//    @Around
//    public void AroundLogic(){
//        System.out.println("This is around logic 1");
//    }

}