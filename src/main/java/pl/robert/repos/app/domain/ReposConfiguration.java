package pl.robert.repos.app.domain;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class ReposConfiguration {

    @Bean
    ReposFacade reposFacade(RestTemplateBuilder builder) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ReposService service = new ReposService(builder.build(), mapper);
        return new ReposFacade(service);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
