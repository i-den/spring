@PostConstruct
```java
public class Person {
    private String name;
    private Integer age = -1;
    // ...
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
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd"
       default-lazy-init="true"> <!-- Lazy Init -->

    <context:component-scan base-package="com.idenchev"/>

    <bean id="p1"
          class="com.idenchev.classes.Person"
          name="person1"
          init-method="init"
          p:name="First Person"
          p:age="1"/>

    <bean id="p2"
          class="com.idenchev.classes.Person"
          name="person2"
          init-method="init"
          p:name="Second Person"
          p:age="2"/>

    <bean id="p3"
          class="com.idenchev.classes.Person"
          name="person3"
          init-method="init"
          p:name="Third Person"
          p:age="3"/>
</beans>
```
Can also use
```xml
<beans
        default-lazy-init="true" default-init-method="init">
</beans>
```
```java
public class XMLApp {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("beans.xml");

        printBean("p1", ctx);
        printBean("p2", ctx);
        printBean("p3", ctx);
    }

    private static void printBean(String beanName, ApplicationContext ctx) {
        Person p = (Person) ctx.getBean(beanName);
        System.out.println(p);
    }
}
// Initializing Bean Person First Person
// Person {name='First Person', age=1}
// Initializing Bean Person Second Person
// Person {name='Second Person', age=2}
// Initializing Bean Person Third Person
// Person {name='Third Person', age=3}
```

### Destroy
```java
public class Person {
    // ...
    public void destroy() {
        System.out.println("Destroying Person Bean with Name: " + name);
    }
}
```
```
    <bean id="p1"
          class="com.idenchev.classes.Person"
          name="person1"
          init-method="init"
          destroy-method="destroy"     <-- HERE -->
          p:name="First Person"
          p:age="1"/>
```
```java
public class XMLApp {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("beans.xml");

        printBean("p1", ctx);
        printBean("p2", ctx);
        printBean("p3", ctx);

        ctx.close(); // <-- here
    }
}
// Initializing Bean Person First Person      // init
// Person {name='First Person', age=1}        // init
// Initializing Bean Person Second Person
// Person {name='Second Person', age=2}
// Destroying Person Bean with Name: First Person // destroy
```

