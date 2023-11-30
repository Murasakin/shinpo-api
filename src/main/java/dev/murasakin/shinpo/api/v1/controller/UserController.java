package dev.murasakin.shinpo.api.v1.controller;

import dev.murasakin.shinpo.api.v1.model.ApiResponseModel;
import dev.murasakin.shinpo.application.model.UserOutputModel;
import dev.murasakin.shinpo.application.port.UserInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserInputPort port;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/list")
    public ResponseEntity<ApiResponseModel<?>> list() {
        List<UserOutputModel> result = port.listUsers();
        return new ResponseEntity<>(ApiResponseModel.buildSuccessResponse(result), HttpStatus.OK);
    }
}
