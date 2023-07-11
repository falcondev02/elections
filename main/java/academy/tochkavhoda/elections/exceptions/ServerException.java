package academy.tochkavhoda.elections.exceptions;

public class ServerException extends Exception{
    private final ServerErrorCode errorCode;

    public ServerErrorCode getErrorCode() {
        return errorCode;
    }

    public ServerException(ServerErrorCode errorCode) {
        super(errorCode.getErrorString());
        this.errorCode = errorCode;
    }
}
