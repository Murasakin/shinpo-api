package dev.murasakin.shinpo.presentation.api.v1.model;

public class ApiResponseModel <T> {
    private final boolean success;
    private final T content;
    private final String message;

    public ApiResponseModel(boolean success, T content, String message) {
        this.success = success;
        this.content = content;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getContent() {
        return content;
    }

    public String getMessage() {
        return message;
    }

    public static <S> ApiResponseModel<S> buildSuccessResponse(S content) {
        return new ApiResponseModel<>(true, content, "");
    }

    public static <S> ApiResponseModel<S> buildErrorResponse(S content) {
        return new ApiResponseModel<>(false, content, "");
    }
}
