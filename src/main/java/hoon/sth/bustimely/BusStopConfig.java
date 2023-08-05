package hoon.sth.bustimely;

import hoon.sth.bustimely.repository.BusStopRepository;
import hoon.sth.bustimely.service.BusAPIHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class BusStopConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public BusAPIHelper busAPIHelper() { return new BusAPIHelper(); }

}
