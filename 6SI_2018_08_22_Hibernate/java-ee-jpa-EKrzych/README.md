## JPA Example Project

Follow the instructions **point by point**, run the code and **check the results** in the database. Check the states of in-memory objects as well (use the debugger, logging, or `System.out`).

### Exercises

1. Create a PostgreSQL database `jpaexampleDB` and modify DB username and password in `resources/META-INF/persistence.xml`.
1. Start `JPAExample`. `Student` and `Address` are annotated with `@Entity`, so if you check the database you should see two tables created by Hibernate.
1. Use the `@Column` annotation to modify the default O-R mapping! Change the column name for attribute `zipcode` to `Zip`, limit its length to 4, and set the `email` field to `UNIQUE` and `NOT NULL`!
1. It is not needed to persist `age` of students since it is calculated from `dateOfBirth` - exclude it from the table by marking it `@Transient`.
1. Add a `List<String> phoneNumbers` to students, and add it to the constructor! You can fix the problem by `@ElementCollection`. Set the name of the auxiliary table to `Phone`.
1. Now students have an address. What happens when addresses have a student as well? Set a symmetric `@OneToOne` relation and see what happens!
1. We wouldn't want this... Fix the issue by adding a `mappedBy` attribute to the annotation! What has changed?
1. Annotate POJO `Klass` and convert it to an entity! The corresponding table should be called `Class`. Fix the issues (use `@OneToMany`)! What happened in the database?
1. Again, use `mappedBy` to make the association bidirectional! (You have to drop a table manually if it's not needed anymore.)
1. **IMPORTANT!** When using bidirectional relationships it is your responsibility to set the relations both ways to have the in-memory objects in sync with the database!
1. Create a new enumerated class called `CCLocation` with values `MISKOLC`, `BUDAPEST`, and `KRAKOW`! Add it as an attribute to `Klass` and set it in its constructor! How does it get represented in the database? Use the `@Enumerated` annotation to change this default behaviour!
