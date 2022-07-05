package guide.triple.mileage.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * packageName    : guide.triple.mileage.web.config
 * fileName       : WebConfig
 * author         : kimdonggyuuuuu
 * date           : 2022/07/01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/01        kimdonggyuuuuu       최초 생성
 */
@Configuration
public class WebConfig {

    @Value("${spring.messages.basename:messages/message-error}")
    private String messageBasename;

    @Value("${spring.messages.encoding:UTF-8}")
    private String messageEncoding;

    @Value("${spring.messages.cache-duration:10}")
    private int messageCacheDuration;


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);

        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(messageBasename);
        messageSource.setDefaultEncoding(messageEncoding);
        messageSource.setCacheSeconds(messageCacheDuration); //reload messages every 10 seconds
        return messageSource;
    }





}
