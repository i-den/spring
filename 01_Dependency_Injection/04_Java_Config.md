### Java Configuration
This is useful when the bean types that
the application needs are part of third-party libraries that cannot be modified. Such a configuration class is
annotated with @Configuration and contains methods annotated with @Bean that are called directly by the
Spring IoC container to instantiate the beans

```java
@Configuration
@ComponentScan(basePackages = {"com.idenchev.javaconfig"})
public class JavaCfg {

    @Bean
    public Output output() {
        return new Output();
    }

    @Bean
    public OutputAmplifier outputAmplifier() {
        Output output = output();
        OutputAmplifier outputAmplifier = new OutputAmplifier();
        outputAmplifier.setOutput(output);
        return outputAmplifier;
    }
}
```
```java
public class JavaCfgApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaCfg.class);
        OutputAmplifier outputAmplifier = (OutputAmplifier) ctx.getBean("outputAmplifier");
        System.out.println(outputAmplifier.output());
    }
}
```

### Setter Injection
```java
@Component("output")
public class Output {}

@Component("outputAmplifier")
public class OutputAmplifier {
    // ...
    @Autowired                               // <--
    public void setOutput(Output output) {
        this.output = output;
    }
}
```

