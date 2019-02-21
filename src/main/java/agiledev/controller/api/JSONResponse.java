package agiledev.controller.api;

public class JSONResponse {
    private boolean status;
    private String message;
    private Object content;


    public JSONResponse(boolean status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.content = object;
    }
}
