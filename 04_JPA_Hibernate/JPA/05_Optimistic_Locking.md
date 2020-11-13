User 1
```java
Guide guide = em.findGuide(1L);
em.close();

guide.setName("Updated");
```
User 2
```java
Guide guide = em.findGuide(1L);
guide.setName("Updated Before User 1");
em.persist(guide);
```
User 1
```java
em2.persist(guide); // Guide has already been updated
```

Introduce Optimistic Locking
```java
public class Guide {
    @Version
    private Integer version;
}
```

Will throw OptimisticLockException upon performing an Update on already Updated Entity
