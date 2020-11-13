```java
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    PaymentType paymentType;
}
```
```java
public enum PaymentType {
    CASH("CASH"),
    CC("CC");

    private String type;

    PaymentType(String type) {
        this.type = type;
    }
}
```
```java
Payment payment = new Payment();
payment.setPaymentType(PaymentType.CASH);
session.save(payment);

// Hibernate: insert into Payment (paymentType) values (?)
```