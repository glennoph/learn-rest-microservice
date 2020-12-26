package go.springboot.learnrestmicroservice.ui.controller;

import go.springboot.learnrestmicroservice.service.UserService;
import go.springboot.learnrestmicroservice.ui.model.request.UserInfoRequestModel;
import go.springboot.learnrestmicroservice.ui.model.request.UserInfoUpdateRequestModel;
import go.springboot.learnrestmicroservice.ui.model.response.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    /** get all users
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue="1")  int page,
                           @RequestParam(value = "limit", defaultValue="10") int limit
    ) {
        return "get users, page="+page+" limit="+limit;
    }

    /** get user
     *
     * @param userId
     * @return
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        if (userId.equals("0")) // if userid is 0 then bad request
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        // find user and return user info with status OK
        UserRest userRest=userService.getUser(userId);
        if (userRest !=null ) {
            return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
        }
        // no user was found
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /** create user
     *
     * @param userDetails
     * @return
     */
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE
            ,produces=MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserInfoRequestModel userDetails) {

        UserRest userRest = null;
        userRest = userService.createUser(userDetails);

        return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
    }

    /** update user
     *
     * @param userId
     * @param userInfoUpdateRequestModel
     * @return
     */
    @PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE
            ,produces=MediaType.APPLICATION_JSON_VALUE
            ,path = "/{userId}")
    public ResponseEntity<UserRest> updateUser(
            @PathVariable String userId,
            @Valid @RequestBody UserInfoUpdateRequestModel userInfoUpdateRequestModel) {
        UserRest userRest = userService.updateUser(userId, userInfoUpdateRequestModel);

        if (userRest != null) {
            return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
        }

        // no user found ... no content
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /** delete user
     *
     * @param id
     * @return
     */
    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        if (userService.deleteUser(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
