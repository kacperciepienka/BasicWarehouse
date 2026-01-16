package pl.kacper;

public class ProductSoldOutException extends RuntimeException {
    public ProductSoldOutException(String message) {
        super(message);
    }
}
