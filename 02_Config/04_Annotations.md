```java
public class Person {
    // ...
    @PostConstruct            // <-- here
    public void init() {
        System.out.println("Initializing Bean Person " + name);
        if (name == null) {
            System.out.println("Name is not set");
        }
        if (age == -1) {
            System.out.println("Age is not set");
        }
    }
}


@Configuration
public class Config {
    @Bean(initMethod="init")       // <-- here
    public Person person() {/*...*/}
}
```

### Destroy
```java
public class Person {
    // ...
    @PreDestroy
    public void destroy() {
        System.out.println("Calling from the @PreDestroy annotation for Bean with Name: " + name);
    }
}

@Configuration
public class Config {
    @Bean(destroyMethod="destroy")       // <-- HERE 
    public Person person() {/*...*/}
}
```

