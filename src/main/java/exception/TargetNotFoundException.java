package exception;

public class TargetNotFoundException extends Exception{
    private String key;

    public TargetNotFoundException(String key) {
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "victim " + this.key + " not found";
    }
}
