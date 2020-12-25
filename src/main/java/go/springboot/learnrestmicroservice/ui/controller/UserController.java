package go.springboot.learnrestmicroservice.ui.controller;

import go.springboot.learnrestmicroservice.ui.model.request.UserInfoRequestModel;
import go.springboot.learnrestmicroservice.ui.model.request.UserInfoUpdateRequestModel;
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

    Map<String, UserInfoRequestModel> tempUsers;

    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue="1")  int page,
                          @RequestParam(value = "limit", defaultValue="10") int limit
                          ) {
        return "get users, page="+page+" limit="+limit;
    }
    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserInfoRequestModel> getUser(@PathVariable String userId) {
        if (userId.equals("0")) // if userid is 0 then bad request
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        // find user and return user info with status OK
        UserInfoRequestModel userInfoRequestModel=null;
        if (tempUsers !=null && tempUsers.containsKey(userId)) {
            userInfoRequestModel = tempUsers.get(userId);
            return new ResponseEntity<UserInfoRequestModel>(userInfoRequestModel, HttpStatus.OK);
        }
        // no user was found
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE
            ,produces=MediaType.APPLICATION_JSON_VALUE
         )
    public ResponseEntity<UserInfoRequestModel> createUser(@Valid
                                                           @RequestBody UserInfoRequestModel userDetails) {

        UserInfoRequestModel userInfoRequestModel = new UserInfoRequestModel();
        userInfoRequestModel.setFirstName(userDetails.getFirstName());
        userInfoRequestModel.setLastName(userDetails.getLastName());
        userInfoRequestModel.setEmail(userDetails.getEmail());
        userInfoRequestModel.setUserId(userDetails.getUserId());

        // save user in temp users
        if (tempUsers == null) tempUsers = new HashMap<>();
        tempUsers.put(userDetails.getUserId(), userDetails);

        return new ResponseEntity<UserInfoRequestModel>(userInfoRequestModel, HttpStatus.OK);
    }

    @PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE
            ,produces=MediaType.APPLICATION_JSON_VALUE
            ,path = "/{userId}")
    public ResponseEntity<UserInfoRequestModel> updateUser(
            @PathVariable String userId,
            @Valid @RequestBody UserInfoUpdateRequestModel userInfoUpdateRequestModel) {
        if (tempUsers.containsKey(userId)) {
            UserInfoRequestModel userInfoRequestModel = (UserInfoRequestModel)tempUsers.get(userId);
            userInfoRequestModel.setFirstName(userInfoUpdateRequestModel.getFirstName());
            userInfoRequestModel.setLastName(userInfoUpdateRequestModel.getLastName());
            tempUsers.put(userId, userInfoRequestModel);

            return new ResponseEntity<UserInfoRequestModel>(userInfoRequestModel, HttpStatus.OK);
        }

        // no user found ... no content
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        if (tempUsers != null && tempUsers.containsKey(id)) {
            tempUsers.remove(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
