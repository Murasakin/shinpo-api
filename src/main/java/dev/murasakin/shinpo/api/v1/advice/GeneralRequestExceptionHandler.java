package dev.murasakin.shinpo.api.v1.advice;

import dev.murasakin.shinpo.api.v1.model.ApiResponseModel;
import dev.murasakin.shinpo.api.v1.model.GeneralExceptionModel;
import dev.murasakin.shinpo.core.application.exception.NotFoundApplicationException;
import dev.murasakin.shinpo.core.application.exception.PersistenceException;
import dev.murasakin.shinpo.core.domain.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.rmi.AlreadyBoundException;
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

    @ExceptionHandler(NotFoundApplicationException.class)
    public final ResponseEntity<ApiResponseModel<GeneralExceptionModel>> handleNotFoundException(
            Exception ex, WebRequest request) {
        GeneralExceptionModel model = new GeneralExceptionModel(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        ApiResponseModel<GeneralExceptionModel> responseModel = ApiResponseModel.buildErrorResponse(model);

        return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyBoundException.class)
    public final ResponseEntity<ApiResponseModel<GeneralExceptionModel>> handleAlreadyExistentException(
            Exception ex, WebRequest request) {
        GeneralExceptionModel model = new GeneralExceptionModel(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        ApiResponseModel<GeneralExceptionModel> responseModel = ApiResponseModel.buildErrorResponse(model);

        return new ResponseEntity<>(responseModel, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PersistenceException.class)
    public final ResponseEntity<ApiResponseModel<GeneralExceptionModel>> handlePersistenceException(
            Exception ex, WebRequest request) {
        GeneralExceptionModel model = new GeneralExceptionModel(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        ApiResponseModel<GeneralExceptionModel> responseModel = ApiResponseModel.buildErrorResponse(model);

        return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DomainException.class)
    public final ResponseEntity<ApiResponseModel<GeneralExceptionModel>> handleDomainException(
            Exception ex, WebRequest request) {
        GeneralExceptionModel model = new GeneralExceptionModel(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        ApiResponseModel<GeneralExceptionModel> responseModel = ApiResponseModel.buildErrorResponse(model);

        return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
    }
}
