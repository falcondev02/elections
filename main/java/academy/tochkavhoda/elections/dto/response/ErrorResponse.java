package academy.tochkavhoda.elections.dto.response;

import academy.tochkavhoda.elections.exceptions.ServerErrorCode;

public class ErrorResponse {
    private final String message;
    private ServerErrorCode errorCode;
    private int errorCodeInt;

    public ErrorResponse(String message, ServerErrorCode errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public ErrorResponse(String errorString, int errorCodeInt) {
        this.message = errorString;
        this.errorCodeInt = errorCodeInt;
    }
}
