JPARepository is responsible for Data access.

- Make an interface, annotate it with `@Repository`
- Extend the interface with JPARepostitory, which takes 2 generics, types of obj we are working with and its primary ID type.

```
@Repository
public interface StudentRepository
    extends JpaRepository<Student, Long> {
}
```

Now in service class, we will use this powerful method.

```
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll(); // returns all the list of student
    }
}
```

We will return all the student list from our database. But first we have to insert few students using JpaRepository.

### Saving Students

- New configuration class for talking with Repository and save our data into database

```
@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mariam = new Student(
                    1L,
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, JANUARY, 5),
                    21
            );

            Student saif = new Student(
                    "Saif",
                    "munshisaif0990@gmail.com",
                    LocalDate.of(1999, JANUARY, 23),
                    22
            );

            // saving
            repository.saveAll(
                    List.of(mariam, saif)
            );
        };
    }
}

```

- If we run our application now, we will get our students list.

### Calculating age automatically

- `@Transient` annotation means we don't want that field to store as column in db.

### POST Request

- Controller class

```
@PostMapping
    public void registerNewStudent(@RequestBody Student student){
        // requestbody means we weant student input from our client
        studentService.addNewStudent(student);
}
```

- Service Class

```
public void addNewStudent(Student student) {
    System.out.println(student);
}
```

- Now we can verify this postMapping with any of our rest client. i.e. POSTMan, Intellij HTTP client. Here, we are using Intellij's default HTTP client. SO, input:

```
POST http://localhost:8080/api/v1/student
Content-Type: application/json

{
  "name": "Bilal",
  "email": "bilal.ahmed@gmail.com",
  "dob": "1995-12-17"
}
```

- Now we want to see if same email exists.

### Finding user by email

- Go to `StudentRepository` and we can do this into following ways:

- First we need to add our query inside DataAccess Layer. And next we need to validate using that query e.g. if the email is present inside db then generate exception. otherwise save that student. Inside `StudentRepository`:

```
Optional <Student> findStudentByEmail(String email);

// or another way is annotation
@Query("SELECT s FROM Student s WHERE s.email= ?1")

```

- And inside studentService:

```
Optional<Student> studentOptional =  studentRepository.findStudentByEmail(student.getEmail());

if (studentOptional.isPresent()) {
    throw new IllegalStateException("Email Taken");
}
studentRepository.save(student);
```

Now if we run our application and then do a POST request:

```
POST http://localhost:8080/api/v1/student
Content-Type: application/json

{
  "name": "Bilal",
  "email": "bilal.ahmed@gmail.com",
  "dob": "1995-12-17"
}
```

Output will be: `200-OK`

Now, we will add the following line into our `application.properties`:

```
# getting server message
server.error.include-message=always
```

Again if we run the exact same request:

```
{
  "timestamp": "2021-08-04T04:02:44.221+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "Email Taken",
  "path": "/api/v1/student"
}
```

### Delete student by ID:

- Inside student controller. By default our path is `localhost:8080/api/v1/student`. Now we mentioned if our path is `student/studentId` then we will delete that student by his/her id.

```
@DeleteMapping(path = "{studentId}")
    // collecting studentId from path variable
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
}
```

We now need to implement deleteStudent method in our service class.

```
public void deleteStudent(Long studentId) {
    boolean exists = studentRepository.existsById(studentId);
    // if id not found throw exception
    if(!exists){
        throw new IllegalStateException(
                "student with id " + studentId + "does not exists"
        );
    }
    // if id found, delete it from db
    studentRepository.deleteById(studentId);
}
```

Now run the applicaiton, and inside request give the following url:

```
###
DELETE http://localhost:8080/api/v1/student/1
```

executing this will delete mariam's data.

### Doing PUT request

- PUT request is used when we want to update our system. We want to update name and email by PUT request.
- Inside controller. Both name and email are not required.

```
@PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
}
```

- Inside service class:

```
// transactional means entity goes an managing state
@Transactional
public void updateStudent(Long studentId, String name, String email) {
    // finding students by id or throwing exceptions
    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new IllegalStateException(
                    "student with Id " + studentId + "does not exists"
            ));
    // updating name, name that is provided for updating, not 0 or null or is not equal to existing
    if(name != null && name.length()> 0 && !Objects.equals(student.getName(), name)){
        student.setName(name);
    }

    if(email != null && email.length()> 0
            && !Objects.equals(student.getEmail(), email)){
        // check if given email is not taken
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        student.setEmail(email);
    }
}
```

- Chekcking:

```
PUT http://localhost:8080/api/v1/student/1?name=Maria&email=maria@gmail.com
```

It will respond success message.
