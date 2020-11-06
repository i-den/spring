## [pictures/bean_lifecycles.png]
 1. @PostConstruct
 2. implements InitializingBean // call afterPropertiesSet()
 3. init method in config
 
Types 
 - interface
 - method
 - annotation
 
Post Init
 - after setting all props

Pre Destruct
 - before Spring destroys the bean
 - not called by Spring for @Scope("prototype")
 
 #### Interface
 ```java
public class Person implements InitializingBean {
    public void afterPropertiesSet() throws Exception {}
}
```
```java
public class Person implements DisposableBean {
    public void destroy() {}
}
```

### Order of Resolution
 1. Constructor creates the Bean
 2. Dependencies are injected (setters are called)
 3. BeanPostProcessor Spring specific beans are called to see if they "want" to call anything on the new Bean
    - @PostConstruct is registered by CommonAnnotationBeanPostProcessor
 4. InitializingBean interface's afterPropertiesSet()
    - called by a BeanFactory after all Bean properties are set
    - and has satisfied BeanFactoryAware and ApplicationContextAware
 5. The init method is called 
 

### Destroying a Singleton Bean
The context needs to call .close

Since there might be more than a single exit point in a Web App you can do
```java
public class XMLApp {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("beans.xml");
        ctx.registerShutdownHook(); // <-- HERE
    }
}
```


FactoryBean



