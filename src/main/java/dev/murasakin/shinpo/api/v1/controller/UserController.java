package dev.murasakin.shinpo.api.v1.controller;

import dev.murasakin.shinpo.core.application.model.user.UserUpdateModel;
import dev.murasakin.shinpo.api.v1.model.ApiResponseModel;
import dev.murasakin.shinpo.core.application.model.user.UserReadModel;
import dev.murasakin.shinpo.core.application.model.user.UserCreateModel;
import dev.murasakin.shinpo.core.application.port.UserPort;
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
        List<UserReadModel> result = port.listUsers();
        return new ResponseEntity<>(ApiResponseModel.buildSuccessResponse(result), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/create")
    public ResponseEntity<ApiResponseModel<?>> create(@RequestBody UserCreateModel model) {
        UserReadModel result = port.createUser(model);
        return new ResponseEntity<>(ApiResponseModel.buildSuccessResponse(result), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/{username}")
    public ResponseEntity<ApiResponseModel<?>> getOne(@PathVariable String username) {
        UserReadModel result = port.getUser(username);
        return new ResponseEntity<>(ApiResponseModel.buildSuccessResponse(result), HttpStatus.OK);
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/update/{username}")
    public ResponseEntity<ApiResponseModel<?>> update(
            @PathVariable String username, @RequestBody UserUpdateModel model) {
        UserReadModel result = port.updateUser(username, model);
        return new ResponseEntity<>(ApiResponseModel.buildSuccessResponse(result), HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping("/delete/{username}")
    public ResponseEntity<ApiResponseModel<?>> delete(@PathVariable String username) {
        port.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}
