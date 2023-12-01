package dev.murasakin.shinpo.api.v1.controller;

import dev.murasakin.shinpo.api.v1.model.ApiResponseModel;
import dev.murasakin.shinpo.application.model.UserModel;
import dev.murasakin.shinpo.application.port.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserPort port;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/list")
    public ResponseEntity<ApiResponseModel<?>> list() {
        List<UserModel> result = port.listUsers();
        return new ResponseEntity<>(ApiResponseModel.buildSuccessResponse(result), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/create")
    public ResponseEntity<ApiResponseModel<?>> create(@RequestBody UserModel model) {
        UserModel result = port.createUser(model);
        return new ResponseEntity<>(ApiResponseModel.buildSuccessResponse(result), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/{username}")
    public ResponseEntity<ApiResponseModel<?>> getOne(@PathVariable String username) {
        UserModel result = port.getUser(username);
        return new ResponseEntity<>(ApiResponseModel.buildSuccessResponse(result), HttpStatus.OK);
    }
}
