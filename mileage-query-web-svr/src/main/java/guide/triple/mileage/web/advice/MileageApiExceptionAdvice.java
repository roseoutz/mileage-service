package guide.triple.mileage.web.advice;

import guide.triple.mileage.common.exception.MileageException;
import guide.triple.mileage.web.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.servlet.LocaleResolver;

@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(annotations = RestController.class)
public class MileageApiExceptionAdvice {


    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(MileageException.class)
    public ResponseEntity<ResponseDTO> MileageExceptionHandler(MileageException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .internalServerError()
                .body(ResponseDTO.builder()
                        .success(false)
                        .error(e.getErrorCode())
                        .errorMsg(e.getMessage())
                        .statusCode(e.getStatusCode())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> exceptionHandler(Exception e) {
        MileageException be = new MileageException(e);
        log.error(be.getMessage(), be);
        return ResponseEntity
                .internalServerError()
                .body(ResponseDTO.builder()
                        .success(false)
                        .error(be.getErrorCode())
                        .errorMsg(be.getMessage())
                        .statusCode(be.getStatusCode())
                        .build());
    }

}
