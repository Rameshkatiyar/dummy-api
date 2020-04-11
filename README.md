#User API
We can use it for dummy test purpose. Specially for learning API automation.

##/user
Get the user data.

##/user/{id}
Get the user data by id.
Example: /user/123

#Learning Points:
##@RestController VS @Controller
###RestController 
This returns the data in the form of JSON.
###Controller 
This returns the view in the form of HTML. We can not return a Data using @Controller.

##ResponseEntity
ResponseEntity represents the whole HTTP response: status code, headers, and body. Because of it, we can use it to fully configure the HTTP response. ResponseEntity is a generic type. As a result, we can use any type as the response body:

