package agiledev.controller.api;

public class JSONResponse {
    private boolean success;
    private String message;
    private Object content;


    public JSONResponse(boolean success, String message, Object object) {
        this.success = success;
        this.message = message;
        this.content = object;
    }
}
