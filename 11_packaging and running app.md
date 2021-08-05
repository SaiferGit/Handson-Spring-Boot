### Creating .jar file from API

- Delete `target` folder from application directory. So, first stop the application.
- Do `maven clean`
- Do `maven install`
- Expand `target` folder to get the jar. To run using CLI we can run following command:

```
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

- To run on different port:

```
java -jar target/demo-0.0.1-SNAPSHOT.jar --server.port=8081
```
