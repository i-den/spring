Example using ProxyFactory. This is purely programmatic and mostly not needed

Instead using Declarative AOP config (ProxyFactoryBean, AOP Namespace, @AspectJ annotations)

```java
public class SaysHiClass {
    public void speak() {
        System.out.println("SaysHiClass: Hi");
    }
}
```
```java
public class SaysHiDecorator implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("SaysHiDecorator: Decorates Before");  // <-- HERE
        Object ret = methodInvocation.proceed();
        System.out.println("SaysHiDecorator: Decorates After");   // <-- HERE
        return ret;
    }
}
```
```java
public class BasicAOPDemo {
    public static void main(String[] args) {
        SaysHiClass hi = new SaysHiClass();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SaysHiDecorator());
        pf.setTarget(hi);

        SaysHiClass proxy = (SaysHiClass) pf.getProxy();

        System.out.println("Normal Class-------");
        hi.speak();
        System.out.println("Normal Class-------");

        System.out.println("Proxy-------");
        proxy.speak();
        System.out.println("Proxy-------");
    }
}
// Normal Class-------
// SaysHiClass: Hi
// Normal Class-------

// Proxy-------
// SaysHiDecorator: Decorates Before
// SaysHiClass: Hi
// SaysHiDecorator: Decorates After
// Proxy-------
```