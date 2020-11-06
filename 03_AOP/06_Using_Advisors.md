Need to make an instance of the Advisor interface (PointcutAdvisor)

Advisor is the Spring version of an Aspect

Couples advices and pointcuts together and governs which methods should be advised

### Static Method Matcher
Will match method "speak" of class SaysHiClass
```java
public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().equals("speak");
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> (clazz == SaysHiClass.class);
    }
}
```
```java
public class SimpleAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("SimpleAdvice Before call");
        Object ret = invocation.proceed();
        System.out.println("SimpleAdvice After call");
        return ret;
    }
}
```

```java
public class StaticPointcutDemo {
    public static void main(String[] args) {
        SaysSomethingInterface hi = new SaysHiClass();
        SaysSomethingInterface bye = new SaysByeClass();

        Pointcut pc = new SimpleStaticPointcut();                    // <-- HERE
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);    // Spring provided

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(hi);
        SaysSomethingInterface hiProxy = (SaysSomethingInterface) pf.getProxy();

        pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(bye);
        SaysSomethingInterface byeProxy = (SaysSomethingInterface) pf.getProxy();

        System.out.println("Hi proxy calls:");
        hiProxy.speak();
        System.out.println();
        System.out.println("Bye proxy calls");
        byeProxy.speak();
    }
}
// Hi proxy calls:
// SimpleAdvice Before call
// SaysHiClass: Hi
// SimpleAdvice After call
// 
// Bye proxy calls
// Says Bye Class: Bye!
```

### Dynamic Method Matcher
```java
public class TakesIntArg {
    public void saysIsEven(int n) {
        System.out.println("Number is even: " + (n % 2 == 0));
    }
}
```
```java
public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "saysIsEven".matches(method.getName());
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        int n = (int) args[0];
        return (n != 100);
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> (clazz == TakesIntArg.class);
    }
}
```
```java
public class DynamicPointcutDemo {

    public static void main(String[] args) {
        TakesIntArg takesIntArg = new TakesIntArg();

        Advice advice = new SimpleAdvice();
        Pointcut pc = new SimpleDynamicPointcut();
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice); // <-- HERE

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(takesIntArg);
        TakesIntArg proxy = (TakesIntArg) pf.getProxy();

        proxy.saysIsEven(98); // not filtered by the pointcut
    }
}
// SimpleAdvice Before call
// Number is even: true
// SimpleAdvice After call
```