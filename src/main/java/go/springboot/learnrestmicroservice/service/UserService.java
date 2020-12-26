package go.springboot.learnrestmicroservice.service;

import go.springboot.learnrestmicroservice.ui.model.request.UserInfoRequestModel;

public interface UserService {
    UserInfoRequestModel createUser(UserInfoRequestModel userInfoRequestModel) ;
}
