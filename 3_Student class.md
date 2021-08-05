## Let's create a student class

```
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private Integer age;

    public Student(String name, String email,
                   LocalDate dob, Integer age) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
```

Now inside main method rewrite the `hello` function:

```
@GetMapping
	public List<Student> hello(){
		return List.of(
				new Student(
						1L,
						"Mariam",
						"mariam.jamal@gmail.com",
						LocalDate.of(2000, Month.JANUARY, 5),
						21
				)
		);
	}
```

This will output JSON Array of Student Obj:

```
[
  {
    "id": 1,
    "name": "Mariam",
    "email": "mariam.jamal@gmail.com",
    "dob": "2000-01-05",
    "age": 21
  }
]
```
