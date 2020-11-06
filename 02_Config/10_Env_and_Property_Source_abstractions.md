```java
public class EnvSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.refresh();

        ConfigurableEnvironment env = ctx.getEnvironment();
        MutablePropertySources sources = env.getPropertySources();

        Map<String, Object> map = new HashMap<>();
        map.put("user.home", "Set via a map");

        sources.addFirst(new MapPropertySource("app", map)); // <-- HERE
        sources.addLast(new MapPropertySource("app", map));  // <-- HERE

        System.out.println("user.home " + System.getProperty("user.home"));      // System.getProperty()
        System.out.println("JAVA_HOME " + System.getenv("JAVA_HOME"));

        System.out.println("user.home: " + env.getProperty("user.home"));        // env.getProperty()
        System.out.println("JAVA_HOME " + env.getProperty("JAVA_HOME"));
    }
}
```