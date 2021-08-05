Now we will create table inside our db to perform CRUD operations.

- To map Student class with our DB, use `@Entity` (for hibernate) and `@Table` (for db table) anotation before Student class.

```
@Entity
@Table
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private Integer age;

    //....
    //......
}
```

- The `@Entity` annotation specifies that the class is an entity and is mapped to a database table
- The `@Table` annotation specifies the name of the database table to be used for mapping. We can specify if entity and table name is different: `@Table(name = "cities")`
- The `@Id` annotation specifies the primary key of an entity.
- `@GeneratedValue` provides for the specification of generation strategies for the values of primary keys.
- Allocation size `means sequence1 + allocation_size = sequnce2`

We can connect our database using DBeaver or Intellij to see the database. To see all the table list use `\l` inside database CLI. To see details of a specific table use `\l <table name>`.
