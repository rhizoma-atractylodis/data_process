package exception;

public class DataTypeException extends Exception {
    @Override
    public String getMessage() {
        return "data type error";
    }
}
