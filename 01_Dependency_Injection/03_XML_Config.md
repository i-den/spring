### XML Configuration
```
<beans...>

    <bean id="output"
          class="com.idenchev.classes.Output"
          name="outputAlias"/>

    <bean id="outputAmplifier"
          class="com.idenchev.classes.OutputAmplifier"
          name="outputAmplifierAlias"
          p:output-ref="output"/> <!-- Will inject the param named "output" from the bean resolution of "output" -->

</beans>
```
```java
public class XMLApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        OutputAmplifier o = (OutputAmplifier) ctx.getBean("outputAmplifier");
        System.out.println(o.output());
    }
}
```

### Setter Injection XML
```
<bean id="output"
      class="com.idenchev.classes.Output"/>

<bean id="outputAmplifier"
      class="com.idenchev.classes.OutputAmplifier">
    <property name="output" ref="output"/>
</bean>
```

### Constructor Injection XML
```
<bean id="xmlStringConstructor"
      class="com.idenchev.classes.XMLStringConstructor">
    <constructor-arg value="This is the string to be injected"/>
</bean>
```
```java
public class XMLApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        StringConstructor stringConstructor = (StringConstructor) ctx.getBean("xmlStringConstructor");
        System.out.println(stringConstructor.getString());
    }
}
```

Using c
```
<context:component-scan base-package="com.idenchev"/>
<bean id="xmlStringConstructor"
      class="com.idenchev.classes.XMLStringConstructor"
      c:_0="This String is injected through XML using c:_0"/>
```
```java
public static void main(String[] args) {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
    XMLStringConstructor stringConstructor = (XMLStringConstructor) ctx.getBean("xmlStringConstructor");
    System.out.println(stringConstructor.getString());
}
```
