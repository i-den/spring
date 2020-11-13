The main class
```java
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    @Embedded                   // <-- HERE
    private Address address;

    public Person() {}
    // getters and setters
}
```
```java
@Embeddable
public class Address {

    private String street;

    public Address() {}

    public Address(String street) {
        this.street = street;
    }
}
```
Can modify the table
```java
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
    })
    private Address address;
```
```java
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Person p = session.get(Person.class, 1L);

        p.setFirstName("FNnn");
        Address a = new Address("Street");
        p.setAddress(a);

        session.getTransaction().commit();
        session.close();
    }
```

Makes the Table use new columns for the Address object but does not create a new Table for it

Useful for Value Objects (Value Object vs Entity DDD)

```sql
id	        bigint
firstName	varchar(255)
street	    varchar(255)
```