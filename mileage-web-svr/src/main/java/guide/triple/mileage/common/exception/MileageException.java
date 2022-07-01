package guide.triple.mileage.common.exception;

import guide.triple.mileage.common.constant.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * packageName    : guide.triple.mileage.common
 * fileName       : MileageException
 * author         : kimdonggyuuuuu
 * date           : 2022/07/01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/01        kimdonggyuuuuu       최초 생성
 */
public class MileageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected HttpStatus httpStatus;

    protected ErrorCode errorCode;

    protected String errorDetail = null;

    protected transient Map<String, Object> dataMap = null;

    public MileageException(Throwable throwable) {
        super(throwable);

        if (throwable instanceof MileageException) {
            MileageException e = (MileageException) throwable;
            this.httpStatus = e.httpStatus;
            this.errorCode = e.errorCode;
            this.dataMap = e.dataMap;
            this.errorDetail = e.errorDetail;
        } else {
            this.errorCode = ErrorCode.COMMON_ERROR;
            this.errorDetail = throwable.getMessage();
        }
    }

    public MileageException(HttpStatus httpStatus, Throwable throwable) {
        this(throwable);
        this.httpStatus = httpStatus;
    }

    public MileageException(HttpStatus httpStatus, ErrorCode errorCode) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public MileageException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, Object> getDataMap() {
        return this.dataMap;
    }

    public String getMessage() {
        return errorCode.getMsgCode();
    }

    public String getDetail() {
        return this.errorDetail;
    }

    public String getErrorCode() { return errorCode.getCode(); }

    public int getStatusCode() {
        return this.httpStatus == null ? 500 : this.httpStatus.value();
    }


}
