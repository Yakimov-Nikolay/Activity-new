package activity_new.activity_new.config;

import activity_new.activity_new.web.Interceptors.StatisticsInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final StatisticsInterceptors interceptors;
    private final LocaleChangeInterceptor localeChangeInterceptor;

    public WebConfiguration(StatisticsInterceptors interceptors, LocaleChangeInterceptor localeChangeInterceptor) {
        this.interceptors = interceptors;
        this.localeChangeInterceptor = localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptors);
        registry.addInterceptor(localeChangeInterceptor);
    }

}
