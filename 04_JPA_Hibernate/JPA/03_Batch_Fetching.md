Avoiding N+1

Lazy Loading
```java
public class Guide {
    @OneToMany(
            mappedBy = "guide",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<Person> people = new HashSet<>();
}
```
```java
public class Person {
    @ManyToOne(
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "guide_id")
    private Guide guide;
}
```

Just a single query as the Guide for each Person is loaded lazily and is not requested
```java
List<Person> people = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
for (Person p : people) {
    System.out.println(p.getName());
}
// Hibernate: select person0_.id as id1_2_, person0_.guide_id as guide_id3_2_, person0_.name as name2_2_ from Person person0_
```

Requesting the Guide as well
```java
List<Person> people = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
for (Person p : people) {
    System.out.println(p.getGuide().getName());
}
// Hibernate: select person0_.id as id1_2_, person0_.guide_id as guide_id3_2_, person0_.name as name2_2_ from Person person0_
// Hibernate: select guide0_.id as id1_0_0_, guide0_.name as name2_0_0_ from Guide guide0_ where guide0_.id=?
// Guide 1
// Hibernate: select guide0_.id as id1_0_0_, guide0_.name as name2_0_0_ from Guide guide0_ where guide0_.id=?
// Guide 2
// 30 queries
```

JOIN FETCH
```java
List<Person> people = em.createQuery("SELECT p FROM Person p LEFT JOIN FETCH p.guide", Person.class).getResultList();
// Hibernate: select person0_.id as id1_2_0_, guide1_.id as id1_0_1_, person0_.guide_id as guide_id3_2_0_, person0_.name as name2_2_0_, guide1_.name as name2_0_1_ from Person person0_ left outer join Guide guide1_ on person0_.guide_id=guide1_.id
// single query, loads all in memory
```

### Batch Fetching
Fetches N amount while lazy loading and storing them in java memory
```java
import org.hibernate.annotations.BatchSize; // Hibernate Specific
@Entity
@BatchSize(size = 4) // <-- HERE
public class Guide {  }
```
```java
List<Person> people = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
for (Person p : people) {
    System.out.println(p.getGuide().getName());
}
// Hibernate: select person0_.id as id1_2_, person0_.guide_id as guide_id3_2_, person0_.name as name2_2_ from Person person0_
// Hibernate: select guide0_.id as id1_0_0_, guide0_.name as name2_0_0_ from Guide guide0_ where guide0_.id in (?, ?, ?, ?)
// Guide 1
// Guide 2
// Guide 3
// Guide 4
// Hibernate: select guide0_.id as id1_0_0_, guide0_.name as name2_0_0_ from Guide guide0_ where guide0_.id in (?, ?, ?, ?)
// Guide 5
// Guide 6
// Guide 7
// Guide 8
```



