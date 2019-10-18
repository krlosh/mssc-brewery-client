package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
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
    public static final String CUSTOMER_V1_PATH = "/api/v1/customer/";

    private String apihost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public BeerDto getBeerById(UUID uuid) {
        return this.restTemplate.getForObject(this.apihost + BEER_V1_PATH + uuid.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto dto) {
        return  this.restTemplate.postForLocation(this.apihost + BEER_V1_PATH, dto);
    }

    public void updateBeer(UUID uuid, BeerDto beerDto) {
        this.restTemplate.put(this.apihost + BEER_V1_PATH+uuid.toString(), beerDto);
    }

    public void deleteBeer(UUID uuid) {
        this.restTemplate.delete(this.apihost + BEER_V1_PATH + uuid.toString());
    }

    public CustomerDto getCustomerById(UUID uuid) {
        return this.restTemplate.getForObject(this.apihost + CUSTOMER_V1_PATH + uuid.toString(), CustomerDto.class );
    }

    public URI saveNewCustomer(CustomerDto dto) {
        return restTemplate.postForLocation(this.apihost+CUSTOMER_V1_PATH,dto);
    }

    public void updateCustomer(UUID uuid, CustomerDto dto ){
        restTemplate.put(this.apihost+CUSTOMER_V1_PATH+uuid.toString(), dto);
    }

    public void deleteCustomer(UUID uuid) {
        this.restTemplate.delete(this.apihost+CUSTOMER_V1_PATH+uuid.toString());
    }
}
