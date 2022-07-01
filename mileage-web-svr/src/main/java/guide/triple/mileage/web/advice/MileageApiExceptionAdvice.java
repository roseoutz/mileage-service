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
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(annotations = RestController.class)
public class MileageApiExceptionAdvice {

    private final MessageSource messageSource;

    private final LocaleResolver localeResolver;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(MileageException.class)
    public ResponseEntity<ResponseDTO> MileageExceptionHandler(HttpServletRequest request, MileageException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .internalServerError()
                .body(ResponseDTO.builder()
                        .success(false)
                        .error(e.getErrorCode())
                        .errorMsg(this.i18n(e, request))
                        .statusCode(e.getStatusCode())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> exceptionHandler(HttpServletRequest request, Exception e) {
        MileageException be = new MileageException(e);
        log.error(be.getMessage(), be);
        return ResponseEntity
                .internalServerError()
                .body(ResponseDTO.builder()
                        .success(false)
                        .error(be.getErrorCode())
                        .errorMsg(this.i18n(be, request))
                        .statusCode(be.getStatusCode())
                        .build());
    }

    private String i18n(MileageException e, HttpServletRequest request) {
        return i18n(e.getMessage(), null, getLocale(request));
    }

    private String i18n(String msgCode, String[] args, Locale locale) {

        if( msgCode == null ) {
            return null;
        }

        return messageSource.getMessage(msgCode, args, msgCode, locale);
    }

    private Locale getLocale(HttpServletRequest request) {
        return localeResolver.resolveLocale(request);
    }
}
