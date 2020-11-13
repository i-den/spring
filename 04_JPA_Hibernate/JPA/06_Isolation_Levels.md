1. User 1 SELECTS
2. User 2 INSERTS
3. User 1 SELECTS again


### Isolation Levels
 1. Serializable
 2. REPEATABLE_READ
 3. READ_COMMITTED
 4. READ_UNCOMMITTED
 

#### Serializable
 - Complete isolation
 - Executed serially
 
#### Repeatable Read
 - Gets the same read
 - Phantom Reads are possible (when another entry is inserted in between)
 
#### Read Committed
 - Will read only committed other transactions
 
#### Read Uncommitted
 - Even if the other transaction is uncommitted it will be read
 - There's a possibility for a rollback
