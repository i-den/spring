Useful when Singleton depends on a Non-Singleton
 - Singleton Bean implement ApplicationContextAware
 - use ApplicationContextAware to get a new Bean each time it's requested
 - declares a LookUp method
 - receive a copy of the Singleton that implements that method by Spring (CGLIB)

NonSingleton class
```java
public class NonSingleton {
    private String lyrics = "Bury all your secrets in my skin";

    public String getLyrics() {
        return lyrics;
    }
}
```

UseNonSingletonInterface
```java
public interface UseNonSingletonInterface {
    NonSingleton getNonSingleton();
    void doSomething();
}
```

### Standard Lookup
```java
public class StandardLookup implements UseNonSingletonInterface {
    private NonSingleton nonSingleton;

    public void setNonSingleton(NonSingleton nonSingleton) {
        this.nonSingleton = nonSingleton;
    }

    public NonSingleton getNonSingleton() {
        return nonSingleton;
    }

    public void doSomething() {
        System.out.println(nonSingleton.getLyrics());
    }
}
```

### Abstract Lookup
```java
public abstract class AbstractLookup implements UseNonSingletonInterface {
    public abstract NonSingleton getNonSingleton(); // Spring will create a subtype of AbstractLookup that will return the NonSingleton
    
    public void doSomething() {
        System.out.println(getNonSingleton().getLyrics());
    }
}
```
Beans.xml
```xml
<bean id="nonSingleton"
      class="com.idenchev.classes.lookupmethod.xml.NonSingleton"
      scope="prototype"/> <!-- Will create a new instance on each request -->

<bean id="abstractLookup"
      class="com.idenchev.classes.lookupmethod.xml.AbstractLookup">
    <lookup-method name="getNonSingleton" bean="nonSingleton"/>

</bean>

<bean id="standardLookup"
      class="com.idenchev.classes.lookupmethod.xml.StandardLookup">
    <property name="nonSingleton" ref="nonSingleton"/> <!-- The lookup method in XML -->
</bean>
```

#### Using the Lookup
```java
public class XMLLookupApp {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("beans.xml");

        StandardLookup standardLookup = (StandardLookup) ctx.getBean("standardLookup");
        AbstractLookup abstractLookup = (AbstractLookup) ctx.getBean("abstractLookup");

        displayInfo("Standard Lookup", standardLookup); // will be injected only once, despite using a prototype
        displayInfo("Abstract Lookup", abstractLookup); // will look up for a new instance on each request
    }

    /**
     * Standard Lookup: same instances: true
     * 10000 instances took 0
     * Abstract Lookup: same instances: false
     * 10000 instances took 63
     */
    private static void displayInfo(String lookupBeanName, UseNonSingletonInterface lookup) {
        NonSingleton n1 = lookup.getNonSingleton();
        NonSingleton n2 = lookup.getNonSingleton();

        System.out.println(lookupBeanName + ": same instances: " + (n1 == n2));

        StopWatch sw = new StopWatch();
        sw.start();
        for (int i = 0; i < 10000; i++) {
            NonSingleton n = lookup.getNonSingleton();
            n.getLyrics();
        }
        sw.stop();
        System.out.println("10000 instances took " + sw.getTotalTimeMillis());
    }
}
```


### Annotation Config
```java
@Component
public class StandardLookup implements UseNonSingletonInterface {
    private NonSingleton nonSingleton;

    @Autowired
    public void setNonSingleton(NonSingleton nonSingleton) {
        this.nonSingleton = nonSingleton;
    }

    public NonSingleton getNonSingleton() {
        return nonSingleton;
    }

    public void doSomething() {
        System.out.println(nonSingleton.getLyrics());
    }
}
```
```java
@Component
public class AbstractLookup implements UseNonSingletonInterface {
    @Lookup
    public NonSingleton getNonSingleton() {
        return null;
    };

    public void doSomething() {
        System.out.println(getNonSingleton().getLyrics());
    }
}
```
```java
@Component
@Scope("prototype")
public class NonSingleton {
    private String lyrics = "Bury all your secrets in my skin";

    public String getLyrics() {
        return lyrics;
    }
}
```

