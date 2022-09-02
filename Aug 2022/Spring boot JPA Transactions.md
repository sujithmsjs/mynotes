[Spring boot Transactions](https://medium.com/chain-analytica/how-to-manage-transactions-in-spring-boot-using-annotations-cbee57fe6e73#:~:text=ACID%20stands%20for%20Atomicity%2C%20Consistency,'all%20or%20nothing'%20property.)

### Transaction

A transaction is a logical unit of processing in a DBMS that access and modify (read and update) the content of a database. This transaction can be a single command, a piece of command and these may take part in any other database operations.

For maintaining the integrity of data, the DBMS systems have to ensure ACID properties. ACID stands for Atomicity, Consistency, Isolation and Durability.


### Atomicity

Since the transaction is treated as a single unit of operation, the transaction should be executed entirely or do not execute it at all. There can not be any partial execution.


### Consistency

When a transaction is completed, the database should remain in a consistent state. This refers to the correctness of a database.


### Isolation

Transactions occur independently without interference (execute in isolation from other transactions). Incomplete transactions will not be visible to other transactions going on concurrently.


### Durability

When a transaction completes successfully, it should permanently record in the database even if the system fails or restarts.

---

### @Transactional

We need to put a transactional annotation in the service layer since it may call different Repositories to perform DB operations.

If the DAO operations are transactional and if you have more than one DAO operation calls in a single service method, then you would have multiple transactions for a single function. This is not the best way because of these things we use transactional annotation in the service layer.


















