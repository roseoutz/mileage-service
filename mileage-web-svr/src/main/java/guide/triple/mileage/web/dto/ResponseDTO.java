package guide.triple.mileage.web.dto;

import guide.triple.mileage.common.constant.ErrorCode;
import lombok.Builder;

import java.io.Serializable;

/**
 * packageName    : guide.triple.mileage.dto
 * fileName       : ResponseDTO
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */

@Builder
public class ResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;
    private int statusCode;
    private String errorCode;
    private String errorMsg;
    private Object result;
}
