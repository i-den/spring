### BeanNameAware interface
Useful for logging
```java
public class Person implements BeanNameAware {
    public void setBeanName(String name) {}
}
```

### ApplicationContextAware interface
```java
public class Person implements ApplicationContextAware {
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(applicationContext instanceof GenericApplicationContext) {
            ((GenericApplicationContext) applicationContext).registerShutdownHook();
        }
    }
}
```