package guide.triple.mileage.common.constant;

import java.io.Serializable;

/**
 * packageName    : guide.triple.mileage.common.constant
 * fileName       : IErrorCode
 * author         : kimdonggyuuuuu
 * date           : 2022/07/01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/01        kimdonggyuuuuu       최초 생성
 */
public interface IErrorCode extends Serializable {

    ErrorCode findByCode(String code);

    String getCode();

    String getMsgCode();

    boolean isEqual(String code);

    boolean isEqual(ErrorCode errorCode);
}
