```
private final StudentService studentService;

public StudentController(StudentService studentService) {
        this.studentService = studentService;
}
```

Here our motive is to insert studentService object into final studentService object. We can use simply by `this.studentService = new StudentService()`. But we need to use dependency injection as much as possible. So, to connect these two we use `@Autowired` annotation.

```
public StudentController(StudentService studentService) {
        this.studentService = studentService;
}
```

We also need instantiated our service class. We can do it as `@Component`. More specifically, `@Service`.

Now we are dividing our code into layers. Our API Layers(Controllers) are communicating with Business Layers(Services) and our service classes are giving the data back.
