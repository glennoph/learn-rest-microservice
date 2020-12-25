# learn rest microservice - spring boot 
## init - start.spring.io
* Maven Java/jar
* Spring 2.4.1
* Dependency: Web

## io.controller.UserController
* RestController 
* RequestMapping: users
### Methods
* get - get
* post - create
* put - update
* delete - delete
### postman 
* requests: get, post, put, delete
* url: http://localhost:8080/users
  
#### get return multiple users
  `get http://localhost:8080/users`
#### get specified user, where 111 is the id
  `get http://localhost:8080/users/111`
  #### post to create a new user
  `post http://localhost:8080/users`  
body:
  ```json
{
"firstName": "go",
"lastName": "ha",
"email": "go@org",
"userId": "go"
}
```