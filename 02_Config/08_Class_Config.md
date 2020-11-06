```java
@Configuration
@ImportResource("path to resource") // XML config
public class AppConfig {

    @Bean
    public Person person() {
        Person p = new Person();
        p.setAge(0);
        p.setName("Name");
        return p;
    }
}
```