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

    /**
     * @return the content
     */
    public Object getContent() {
        return content;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }
}
