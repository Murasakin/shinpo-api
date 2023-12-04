package dev.murasakin.shinpo.presentation.api.v1.controller;

import dev.murasakin.shinpo.presentation.api.v1.model.ApiResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/hello")
public class HelloController {
    @GetMapping()
    public ResponseEntity<ApiResponseModel<?>> hello(@RequestParam(defaultValue = "World") String name) {
        ApiResponseModel<String> responseModel = ApiResponseModel.buildSuccessResponse("Hello, " + name);

        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @GetMapping("/error")
    public ResponseEntity<ApiResponseModel<?>> error() throws Exception {
        throw new Exception("This is a example of error response!");
    }
}
