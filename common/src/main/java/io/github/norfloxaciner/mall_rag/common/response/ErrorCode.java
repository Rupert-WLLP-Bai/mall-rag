package io.github.norfloxaciner.mall_rag.common.response;

public enum ErrorCode {
    // Common system errors
    SYSTEM_ERROR(10000, "System error"),
    PARAMETER_ERROR(10001, "Invalid parameter"),
    UNAUTHORIZED(10002, "Unauthorized access"),
    FORBIDDEN(10003, "Access forbidden"),
    NOT_FOUND(10004, "Resource not found"),
    METHOD_NOT_ALLOWED(10005, "Method not allowed"),
    REQUEST_TIMEOUT(10006, "Request timeout"),
    TOO_MANY_REQUESTS(10007, "Too many requests"),

    // User related errors
    USER_NOT_FOUND(20001, "User not found"),
    USER_ALREADY_EXISTS(20002, "User already exists"),
    PASSWORD_ERROR(20003, "Password error"),
    ACCOUNT_LOCKED(20004, "Account is locked"),
    USER_NOT_LOGGED_IN(20005, "User not logged in"),

    // Product related errors
    PRODUCT_NOT_FOUND(30001, "Product not found"),
    PRODUCT_OUT_OF_STOCK(30002, "Product out of stock"),
    PRODUCT_PRICE_ERROR(30003, "Product price error"),

    // Order related errors
    ORDER_NOT_FOUND(40001, "Order not found"),
    ORDER_ALREADY_PAID(40002, "Order already paid"),
    PAYMENT_FAILED(40003, "Payment failed"),
    SHIPPING_ERROR(40004, "Shipping error");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
