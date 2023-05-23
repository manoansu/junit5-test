package pt.amane.junit5test.blog.exception;

public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException() {
    }

    public RegraNegocioException(String message) {
        super(message);
    }
}
