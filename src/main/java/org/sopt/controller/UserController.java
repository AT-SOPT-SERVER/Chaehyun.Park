package org.sopt.controller;

import org.sopt.dto.UserRequest;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.response.enums.SuccessCode;
import org.sopt.repository.UserRepository;
import org.sopt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users/signup")
    public ResponseEntity<ApiResponse<Void>> createUser(
            @RequestBody UserRequest request
    ){
        userService.createUser(request.name());
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK));
    }

}
