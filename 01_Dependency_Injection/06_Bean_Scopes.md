### Scopes

#### Singleton
 - Shared object with no state: You have an object that maintains no state and has many
   dependent objects. Because you do not need synchronization if there is no state, you
   do not need to create a new instance of the bean each time a dependent object needs
   to use it for some processing.
   
 - Shared object with read-only state: This is similar to the previous point, but you have
   some read-only state. In this case, you still do not need synchronization, so creating
   an instance to satisfy each request for the bean is just adding overhead
 
 -Shared object with shared state: If you have a bean that has state that must be shared,
  singleton is the ideal choice. In this case, ensure that your synchronization for state
  writes is as granular as possible.
  
 -High-throughput objects with writable state: If you have a bean that is used a great
  deal in your application, you may find that keeping a singleton and synchronizing
  all write access to the bean state allows for better performance than constantly
  creating hundreds of instances of the bean. When using this approach, try to
  keep the synchronization as granular as possible without sacrificing consistency.
  You will find that this approach is particularly useful when your application creates
  a large number of instances over a long period of time, when your shared object
  has only a small amount of writable state, or when the instantiation of a new instance
  is expensive

##### Non-singletons 
 - Objects with writable state: If you have a bean that has a lot of writable state, you
   may find that the cost of synchronization is greater than the cost of creating a new
   instance to handle each request from a dependent object
 
 - Objects with private state: Some dependent objects need a bean that has private
   state so that they can conduct their processing separately from other objects that
   depend on that bean. In this case, singleton is clearly not suitable, and you should
   use nonsingleton
 
 
##### Scope Types
 - Singleton
 - Prototype
 - Request
   - For web application use. When using Spring MVC for web applications,
     beans with request scope will be instantiated for every HTTP request and then
     destroyed when the request is completed
 - Session
   - For web application use. When using Spring MVC for web applications,
     beans with session scope will be instantiated for every HTTP session and then
     destroyed when the session is over
 - Global Session
   - For portlet-based web applications. The global session scope beans can
     be shared among all portlets within the same Spring MVC–powered portal application
 - Thread
   - A new bean instance will be created by Spring when requested by a new
     thread, while for the same thread, the same bean instance will be returned. Note that
     this scope is not registered by default
  - Custom (org.springframework.beans.factory.config.Scope)
 
```
<bean id="nonSingleton" class="com.apress.prospring5.ch3.annotated.Singer"
    scope="prototype" c:_0="John Mayer"/>
</beans>

@Component("nonSingleton")
@Scope("prototype")
public class Singer {
    private String name = "unknown";

    public Singer(@Value("John Mayer") String name) {
        this.name = name;
    }
}
```