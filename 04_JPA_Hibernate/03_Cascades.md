Saving one by one
```java
public static void main() {
    Many m1 = new Many();
    Many m2 = new Many();
    One o = new One();
    
    m1.setOne(o);
    m2.setOne(o);
    
    session.save(o);
    session.save(m1);
    session.save(m2);
}
```

To prevent saving every object we need to **cascade** a save method for just one entity

Cascade Types


