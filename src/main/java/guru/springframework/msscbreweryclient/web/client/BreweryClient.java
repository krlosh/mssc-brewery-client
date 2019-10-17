package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    public static final String BEER_V1_PATH = "/api/v1/beer/";

    private String apihost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid) {
        return this.restTemplate.getForObject(this.apihost + BEER_V1_PATH + uuid.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto dto) {
        return  this.restTemplate.postForLocation(this.apihost + BEER_V1_PATH, dto);
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
