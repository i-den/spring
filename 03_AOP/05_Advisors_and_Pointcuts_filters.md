### Pointcut interface
```java
public interface Pointcut {
	ClassFilter getClassFilter();
	MethodMatcher getMethodMatcher();
	Pointcut TRUE = TruePointcut.INSTANCE;
}
```
##### ClassFilter
```java
@FunctionalInterface
public interface ClassFilter {
	boolean matches(Class<?> clazz);
	ClassFilter TRUE = TrueClassFilter.INSTANCE;
}
```
##### MethodMatcher
Two types supported by Spring, determined by isRuntime()
 - static
 - dynamic
```java
public interface MethodMatcher {
	boolean matches(Method method, Class<?> targetClass); // called for Dynamic
	boolean isRuntime();
	boolean matches(Method method, Class<?> targetClass, Object... args); // called for Static
	MethodMatcher TRUE = TrueMethodMatcher.INSTANCE;
}
```

It's rare to create your own Pointcuts.

Spring provides abstract base classes for both static and dynamic

### Spring Pointcuts
#### Annotation
 - org.springframework.aop.support.annotation.AnnotationMatchingPointcut

#### Expression
AspectJ syntax
 - org.springframework.aop.aspectj.AspectJ.ExpressionPointcut
 
#### Composable
Composes two or more Pointcuts with ops like union() or intersection()
 - org.springframework.aop.support.ComposablePointcut

#### Control Flow
Matches all methods within the control flow of another method, that is, any method that is invoked
either directly or indirectly as the result of another method being invoked
 - org.springframework.aop.support.ControlFlowPointcut

#### Dynamic Method Matcher
Base Class for building dynamic pointcuts
 - org.springframework.aop.support.DynamicMethodMatcherPointcut
 
#### JDK Regex Method
Matches methods according to a RegEx
 - org.springframework.aop.support.JdkRegexpMethodPointcut
 
#### Name Match Method
Matches a list of defined method names
 - org.springframework.aop.support.NameMatchMethodPointcut
 
#### Static Method Matcher
Base class for building static pointcuts
 - org.springframework.aop.support.StaticMethodMatcherPointcut
 
[pictures/pointcuts.png]

