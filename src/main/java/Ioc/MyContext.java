package Ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyContext {
    private static final Map<String, Object> objMap = new HashMap<>();

    //scan file
    private static List<Class<?>> scan () throws Exception{
        return Arrays.asList(EmployeeService1.class, EmployeeService2.class, TmpService.class);
    }

    public static void init() throws Exception {
       List<Class<?>> classes = scan();
       for(Class<?> clazz : classes){
           String name = clazz.getSimpleName();
           Object instance = clazz.getDeclaredConstructor().newInstance();
           objMap.put(name, instance);
       }

       for(Object instance : objMap.values()){
           Class<?> clazz = instance.getClass();
           for(Constructor constructor : clazz.getDeclaredConstructors()){
               for(Annotation annotation: constructor.getDeclaredAnnotations()){
                   if(annotation.annotationType() == autowired.class){
                       String name = constructor.getName();
                       constructor.setAccessible(true);

                   }
               }
           }
       }
    }


    public static void main(String[] args) throws Exception{
        init();
        System.out.println(objMap);
    }
}


class TmpService {

    private EmployeeService1 ss1;
    private EmployeeService2 ss2;

    @autowired
    public TmpService() {
    }

    @Override
    public String toString(){
        return "TmpService{ ss1=" + ss1  + ", ss2= "+ ss2  + '}';
    }
}

class EmployeeService1{

}

class EmployeeService2{

}