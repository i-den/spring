EntityManager has a persistence context

A persistence context is a cache of all objects managed by it
```java
Object obj = new Object();     // transient state, not managed
em.persist(obj);               // persistent state

em.getTransaction.commit();
em.close();                    // the ctx is removed, the obj is detached

obj.mutate(val);               // changed in java memory

em2.merge(obj);                // merged and managed by em2
em2.getTransaction.commit();   // will update
```

Cache is a copy of data, copied from a Database
```java
Guide guide = em.find(Guide.class, 1L);
Guide guide2 = em.find(Guide.class, 1L);
Guide guide3 = em.find(Guide.class, 1L);

// only 1 select
// Hibernate: select guide0_.id as id1_0_0_, guide0_.name as name2_0_0_ from Guide guide0_ where guide0_.id=?
```

#### Cache is ID based
```java
Guide g1 = em.find(Guide.class, 1L); // Cached
Guide g1 = em.find(Guide.class, 1L); // No additional SELECT
Guide g3 = em.createQuery("SELECT g FROM Guide g WHERE g.id = :id") // Will Query as there's no way to kn the ID
                .setParameter("id", 1L).getSingleResult();
```