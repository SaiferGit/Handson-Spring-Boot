Here we will put our business logic for managing students. Our API layer will talk with service layer to get the data. Service layer will ask data access layer and hence data access layer will send the data from database to api layer via service layer.

Create `StudentService` class. And paste the `getStudents()` method inside the service class.

```
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
```

Next, delete this method from controller. We will call `getStudents()` via StudentService class object.

```
    private final StudentService studentService;

    // as we declared StudentService object as final we need to initialize it
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }
```
