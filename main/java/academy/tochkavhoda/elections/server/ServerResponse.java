package academy.tochkavhoda.elections.server;

import academy.tochkavhoda.elections.dto.response.ErrorResponse;
import academy.tochkavhoda.elections.exceptions.ServerErrorCode;
import academy.tochkavhoda.elections.exceptions.ServerException;
import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ServerResponse {
    private Gson gson = new Gson();
    private int responseCode;
    private String responseData;
    private static final int ERROR_CODE = 400;
    private static final int SUCCESS_CODE = 200;

    public ServerResponse(int responseCode, String responseData) {
        this.responseCode = responseCode;
        this.responseData = responseData;
    }

    public ServerResponse(String responseData) {
        this(SUCCESS_CODE, responseData);
    }

    public ServerResponse(ServerException e) {
        ServerErrorCode error = e.getErrorCode();
        String message = error.getErrorString();
        this.responseCode = ERROR_CODE;
        ErrorResponse errorResponse = new ErrorResponse(message, error);
        this.responseData = gson.toJson(errorResponse);

    }
}
