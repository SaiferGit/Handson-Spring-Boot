Create a new class name StudentController and do the followings:

```
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    @GetMapping
    public List<Student> getStudents(){
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
}
```

- The purpose of `@RequestMapping` is don't changing mapping of this api class. From now, if we hit `localhost:8080` we will see nothing. If we hit `localhost:8080/api/v1/student` then we will see desired output
- Also changed method name from `hello()` to `getStudents()` for giving a related name.
