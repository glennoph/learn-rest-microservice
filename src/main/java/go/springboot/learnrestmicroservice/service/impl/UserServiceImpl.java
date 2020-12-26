package go.springboot.learnrestmicroservice.service.impl;

import go.springboot.learnrestmicroservice.service.UserService;
import go.springboot.learnrestmicroservice.ui.model.request.UserInfoRequestModel;
import go.springboot.learnrestmicroservice.ui.model.request.UserInfoUpdateRequestModel;
import go.springboot.learnrestmicroservice.ui.model.response.UserRest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {

    Map<String, UserRest> tempUsers;

    public UserServiceImpl() {
        this.tempUsers = new HashMap<>();
    }

    @Override
    public UserRest createUser(UserInfoRequestModel userDetails) {

        UserRest userRest = new UserRest();
        userRest.setFirstName(userDetails.getFirstName());
        userRest.setLastName(userDetails.getLastName());
        userRest.setEmail(userDetails.getEmail());
        userRest.setUserId(userDetails.getUserId());

        // save user in temp users
        if (tempUsers == null) tempUsers = new HashMap<>();
        tempUsers.put(userDetails.getUserId(), userRest);

        return userRest;
    }

    @Override
    public UserRest getUser(String userId) {
        if (tempUsers.containsKey(userId))
            return tempUsers.get(userId);
        return null;
    }

    @Override
    public UserRest updateUser(String userId,
                               UserInfoUpdateRequestModel userInfoUpdateRequestModel) {
        UserRest userRest = null;
        if (tempUsers.containsKey(userId)) { // update user rest
            userRest = tempUsers.get(userId);
            userRest.setFirstName(userInfoUpdateRequestModel.getFirstName());
            userRest.setLastName(userInfoUpdateRequestModel.getLastName());
            tempUsers.put(userId, userRest);
        }
        return userRest;
    }

    @Override
    public boolean deleteUser(String id) {
        if (tempUsers.containsKey(id)) {
            tempUsers.remove(id);
            return true;
        }
        return false;
    }
}
