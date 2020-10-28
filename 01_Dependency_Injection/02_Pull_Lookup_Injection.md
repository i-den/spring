### Pull
```java
public class DependencyPull {
    public static void main(String... args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/app-context.xml");
        MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class); // <-- Pull
        mr.render();
    }
}
```
### Lookup
```java
public interface ManagedComponent {
    void performLookup(Container container);
}

public interface Container {
    Object getDependency(String key);
}

public class ContextualizedDependencyLookup implements ManagedComponent {
    private Dependency dependency;

    @Override
    public void performLookup(Container container) {                                // <-- Lookup Method
        this.dependency = (Dependency) container.getDependency("myDependency");     // <-- Lookup
    }
}
```
### Injection
 - Constructor
```java
public class ConstructorInjection {
    private Dependency dependency;

    public ConstructorInjection(Dependency dependency) {
        this.dependency = dependency;
    }
}
```
 - Setter
```java
public class SetterInjection {
    private Dependency dependency;

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }
}
```