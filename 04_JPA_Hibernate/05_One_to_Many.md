### Guide (One)
```java
@Entity
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "guide")      // bi-directional relationship
    private Set<Person> people;

    public void addPerson(Person p) {
        p.setGuide(this);
    }
}
```

### Person (Many)
```java
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "guide_id")
    private Guide guide;

    public void setGuide(Guide guide) {
        if (this.guide != null) {
            this.guide.getPeople().remove(this);
        }
        if (guide != null) {
            guide.getPeople().add(this);
        }
        this.guide = guide;
    }
}
```

### CREATE
```java
Guide guide1 = new Guide();
guide1.setName("Guide 1");
Guide guide2 = new Guide();
guide2.setName("Guide 2");

Person p1 = new Person();
p1.setName("Person 1");
Person p2 = new Person();
p2.setName("Person 2");
Person p3 = new Person();
p3.setName("Person 3");

guide1.addPerson(p1);
guide1.addPerson(p2);
guide2.addPerson(p3);

session.persist(guide1);
session.persist(guide2);

// Hibernate: insert into Guide (name) values (?)
// Hibernate: insert into Person (guide_id, name) values (?, ?)
// Hibernate: insert into Person (guide_id, name) values (?, ?)
// Hibernate: insert into Guide (name) values (?)
// Hibernate: insert into Person (guide_id, name) values (?, ?)
```

### UPDATE
```java
Guide guide = session.createQuery("SELECT g FROM Guide g LEFT JOIN FETCH g.people WHERE g.id = 1", Guide.class)
        .getSingleResult();
Guide guide2 = session.get(Guide.class, 2L);

Set<Person> people = new HashSet<>(guide.getPeople());
people.forEach(p -> p.setGuide(guide2));

// Hibernate: select guide0_.id as id1_0_0_, people1_.id as id1_1_1_, guide0_.name as name2_0_0_, people1_.guide_id as guide_id3_1_1_, people1_.name as name2_1_1_, people1_.guide_id as guide_id3_1_0__, people1_.id as id1_1_0__ from Guide guide0_ left outer join Person people1_ on guide0_.id=people1_.guide_id where guide0_.id=1
// Hibernate: select guide0_.id as id1_0_0_, guide0_.name as name2_0_0_ from Guide guide0_ where guide0_.id=?
// Hibernate: select people0_.guide_id as guide_id3_1_0_, people0_.id as id1_1_0_, people0_.id as id1_1_1_, people0_.guide_id as guide_id3_1_1_, people0_.name as name2_1_1_ from Person people0_ where people0_.guide_id=?

// Hibernate: update Person set guide_id=?, name=? where id=?
// Hibernate: update Person set guide_id=?, name=? where id=?
```

### DELETE
```java
Guide guide = session.createQuery("SELECT g FROM Guide g LEFT JOIN FETCH g.people WHERE g.id = 1", Guide.class)
        .getSingleResult();
session.delete(guide);

// Hibernate: select guide0_.id as id1_0_0_, people1_.id as id1_1_1_, guide0_.name as name2_0_0_, people1_.guide_id as guide_id3_1_1_, people1_.name as name2_1_1_, people1_.guide_id as guide_id3_1_0__, people1_.id as id1_1_0__ from Guide guide0_ left outer join Person people1_ on guide0_.id=people1_.guide_id where guide0_.id=1

// Hibernate: delete from Person where id=?
// Hibernate: delete from Person where id=?
// Hibernate: delete from Guide where id=?
```