java.util.EventObject -> ApplicationEvent -> Your Custom Class

Any bean can listen for Events by implementing the ApplicationListener\<T>

Events are published by using ApplicationEventPublisher.publishEvent()

```java
public class MessageEvent extends ApplicationEvent {
    private String msg;

    public MessageEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
```
```java
public class MessageEventListener implements ApplicationListener<MessageEvent> {
    public void onApplicationEvent(MessageEvent event) {
        System.out.println("Received " + event.getMsg());
    }
}
```
```
<bean id="publisher"
      class="com.idenchev.classes.events.MessageEventPublisher"/>

<bean id="messageEventListener"
      class="com.idenchev.classes.events.MessageEventListener"/>
```
```java
public class MessageEventPublisher implements ApplicationContextAware {
    private ApplicationContext ctx;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public void publish(String msg) {
        ctx.publishEvent(new MessageEvent(this, msg));
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("event_beans.xml");

        MessageEventPublisher publisher = (MessageEventPublisher) ctx.getBean("publisher");
        publisher.publish("The first published message"); // <-- HERE
    }
}
```


