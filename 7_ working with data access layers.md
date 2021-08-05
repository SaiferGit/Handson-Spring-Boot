We will use Postgres as our Real-time DB. To connect our code with DB, we need to add these codes into `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/student # url-port(5432)-dbname(student)

# for local development username and password is empty
spring.datasource.username=
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop # we have the clean state everytime we run the application
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```
