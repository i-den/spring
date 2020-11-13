```java
Guide guide = em.find("SELECT g from Guide g WHERE g.id = 1", Guide.class).getSingleResult();
Person person = guide.getPeople().get(0);
em.getTransaction().commit();
em.close() // Closes 1st EM

// Update Detached Entities
guide.setName("Changed Guide");
person.setName("Changed Person");

em2.getTransaction().begin();

Guide mergedGuide = em2.merge(guide); // start tracking Guide again

em2.getTransaction().commit(); // Only Guide will be updated, Person will not
em2.close();
```

Should change the cascade to include MERGE
```java
public class Guide {
    @OneToMany(
            mappedBy = "guide",
            cascade = CascadeType.MERGE, // <-- HERE
            fetch = FetchType.LAZY
    )
    private Set<Person> people = new HashSet<>();
}
```