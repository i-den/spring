import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class EnvSample {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.refresh();

        ConfigurableEnvironment env = ctx.getEnvironment();
        MutablePropertySources sources = env.getPropertySources();

        Map<String, Object> map = new HashMap<>();
        map.put("user.home", "Set via a map");

        sources.addLast(new MapPropertySource("app", map)); // <-- HERE

        System.out.println("user.home " + System.getProperty("user.home"));      // System.getProperty()
        System.out.println("JAVA_HOME " + System.getenv("JAVA_HOME"));

        System.out.println("user.home: " + env.getProperty("user.home"));        // env.getProperty()
        System.out.println("JAVA_HOME " + env.getProperty("JAVA_HOME"));
    }
}
