package guide.triple.mileage.common.constant;

import java.util.Arrays;

public enum ErrorCode implements IErrorCode{
    COMMON_ERROR("common.error"),
    ERROR_REQUEST_ACTION_NOT_FOUND("error.request.action.not.found"),
    ERROR_REGISTERED_REVIEW_EXIST("error.registered.review.exist"),
    ERROR_REVIEW_NOT_EXIST("error.review.not.exist"),
    ERROR_NOT_EXIST_USER("error.not.exist.user"),

    Error_REQUEST_PARAMETER_INVALID("error.request.parameter.invalid")
    ;

    ErrorCode(String msgCode) { this.msgCode = msgCode; }

    private String msgCode;

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMsgCode() {
        return msgCode;
    }

    @Override
    public boolean isEqual(String code) {
        return this.getCode().equalsIgnoreCase(code);
    }

    @Override
    public boolean isEqual(ErrorCode errorCode) {
        return this.isEqual(errorCode.getCode());
    }

    @Override
    public ErrorCode findByCode(String code) {
        return Arrays.stream(values()).filter(value -> value.isEqual(code)).findFirst().orElse(null);
    }
}
