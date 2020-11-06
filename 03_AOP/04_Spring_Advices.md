### Before Advice
```java
public class SimpleBeforeAdvice implements MethodBeforeAdvice {

    public static void main(String[] args) {
        SaysHiClass hi = new SaysHiClass();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SimpleBeforeAdvice());
        pf.setTarget(hi);

        SaysHiClass proxy = (SaysHiClass)pf.getProxy();
        proxy.speak();
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Executing before advice");
    }
}
// Executing before advice
// SaysHiClass: Hi
```

### After-Returning Advice
```java
public class SimpleAfterReturningAdvice implements AfterReturningAdvice {

    public static void main(String[] args) {
        SaysHiClass hi = new SaysHiClass();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SimpleAfterReturningAdvice());
        pf.setTarget(hi);

        SaysHiClass proxy = (SaysHiClass)pf.getProxy();
        proxy.speak();
    }

    @Override
    public void afterReturning(Object returnVal, Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("Executing after returning advice");
    }
}
// SaysHiClass: Hi
// Executing after returning advice
```

### Around Advice
```java
public class SimpleAroundAdvice implements MethodInterceptor {

    public static void main(String[] args) {
        SaysHiClass hi = new SaysHiClass();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(hi);
        pf.addAdvice(new SimpleAroundAdvice());

        SaysHiClass proxy = (SaysHiClass) pf.getProxy();

        proxy.speak();
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Invoking before");
        Object ret = methodInvocation.proceed();
        System.out.println("Invoking After");
        return ret;
    }
}
// Invoking before
// SaysHiClass: Hi
// Invoking After
```

### Throws Advice
```java
public class SimpleThrowsAdvice implements ThrowsAdvice {
    public static void main(String[] args) {
        SaysHiClass hi = new SaysHiClass();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SimpleThrowsAdvice());
        pf.setTarget(hi);

        SaysHiClass proxy = (SaysHiClass) pf.getProxy();
        try {
            proxy.willThrow();
        } catch (Exception e) {
            // yolo
        }

    }

    public void afterThrowing(Exception ex) throws Throwable {
        System.out.println("Generic Exception Capture");
        System.out.println("Caught: " + ex.getClass().getName());
    }
}
// Generic Exception Capture
// Caught: java.lang.RuntimeException
```

