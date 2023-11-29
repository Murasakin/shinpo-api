package dev.murasakin.shinpo.api.v1.advice;

import dev.murasakin.shinpo.api.v1.model.ApiResponseModel;
import dev.murasakin.shinpo.api.v1.model.GeneralExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class GeneralRequestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResponseModel<GeneralExceptionModel>> handleGeneralException(
            Exception ex, WebRequest request) {
        GeneralExceptionModel model = new GeneralExceptionModel(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        ApiResponseModel<GeneralExceptionModel> responseModel = ApiResponseModel.buildErrorResponse(model);

        return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
