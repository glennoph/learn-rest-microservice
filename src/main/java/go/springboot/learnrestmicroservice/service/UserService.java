package go.springboot.learnrestmicroservice.service;

import go.springboot.learnrestmicroservice.ui.model.request.UserInfoRequestModel;
import go.springboot.learnrestmicroservice.ui.model.request.UserInfoUpdateRequestModel;
import go.springboot.learnrestmicroservice.ui.model.response.UserRest;

public interface UserService {
    public UserRest createUser(UserInfoRequestModel userInfoRequestModel);

    public UserRest getUser(String userId);

    public UserRest updateUser(String userId,
                               UserInfoUpdateRequestModel userInfoUpdateRequestModel);

    public boolean deleteUser(String userId);

}
