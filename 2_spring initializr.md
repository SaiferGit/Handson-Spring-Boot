## Create a new spring boot project

- Go to [Spring Initializr](https://start.spring.io/)
- Select your project type: *Maven* or *Gradle*.
- Packaging: *Jar*
- Java version: *11*
- Dependencies: Spring web, Spring Data JPA, Postgres/H2/MongoDB as per requirement.

- testing contains all the test code for our project
- pom.xml contains all the dependencies that we have selected
- src/java/DemoAplication.java is our main class for running this project.
- src/resources/application.properties to set all the properties of our application here.
- src/resources/static contains all the html css js files that are using for web development.


Now if we try to run our project it will give error as no db has been connected yet. So, comment our Spring data jpa dependency from `pom.xml` and reload maven to confirm it. 

Now our project is running at `localhost:8080`. If we go to that page we will receive `server not found, white level error`.

Let's create a method inside main class:
```
public String hello(){
	return "Hello World";
}
```
Map this method as `@GetMapping`. And add `@RestController` annotation after `@SpringBootApplication`. So this `@RestController` annotation will serve this class as rest endpoints and we have only one endpoint which is `hello`.

Another changing:
```
public List<String> hello(){
		return List.of("Hello", "World");
	}
```
Returns JSON array.
