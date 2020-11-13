#### Concepts
Persistence Context
 - set of managed Entities
Persistence Unit
 - persistence.xml

Transaction
 - groups a series of operations
```
persist
remove
update
```
 - ensures data is kept in a consistent state
 - either all changes succeed or all fail atomically
 
Entity
```
@Entity
@Id
no-arg constructor
concrete or abstract class
cannot be Enum or Interface
@Enumerated(EnumType. ORDINAL | STRING)
```
 
Cascade
 - events cascade onto related entities
```
persist
remove
merge
all
```

Inheritance
 - Single Table
   - everything in one table
   - DTYPE indicates the specific class
   - ```
        @Inheritance
        @DiscriminatorColumn // DTYPE
     ``` 
  - JoinedSubclass
    - preferred
    - many joins
    
  - Table per concrete class
    - no normalization
    
@MappedSuperclass
 - not an Entity but shares information with subclasses
 
Queries
 - Named
   - string, static
 - Dynamic
   - JPQL
   - em.createQuery()
   
LifeCycles
 - events on Entity
```
persisting
updating
removing
loading
```
 - states
 ```
managed
detached
```

```
@Pre...
@Post...
```