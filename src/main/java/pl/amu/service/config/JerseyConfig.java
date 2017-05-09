package pl.amu.service.config;

import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import pl.amu.service.rest.UsersResource;
import pl.amu.service.rest.exception.mappers.IllegalArgumentExceptionMapper;
import pl.amu.service.rest.exception.mappers.UsersAppExceptionMapper;
import pl.amu.service.rest.filters.ApiOriginFilter;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(UsersResource.class);
        register(UsersAppExceptionMapper.class);
        register(IllegalArgumentExceptionMapper.class);
        register(ApiOriginFilter.class);
        packages("io.swagger.jaxrs.listing");
        
        configureSwagger();
    }

    private void configureSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8082");
        beanConfig.setBasePath("");
        beanConfig.setResourcePackage("pl.amu");
        beanConfig.setScan(true);
    }
}
