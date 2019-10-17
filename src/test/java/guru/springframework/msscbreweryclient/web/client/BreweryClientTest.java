package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    private BreweryClient breweryClient;

    @Test
    void testGetBeerById() {
        BeerDto dto = breweryClient.getBeerById(UUID.randomUUID());

        assertNotNull(dto);
    }

    @Test
    void testSaveNewBeer() {
        BeerDto dto = BeerDto.builder().beerName("New beer").build();
        URI uri = this.breweryClient.saveNewBeer(dto);
        assertNotNull(uri);
        System.out.println(uri.toString());
    }

    @Test
    void testUpdateBeer() {
        BeerDto dto = BeerDto.builder().beerName("New beer").build();
        this.breweryClient.updateBeer(UUID.randomUUID(), dto);
    }

    @Test
    void testDelete() {
        this.breweryClient.deleteBeer(UUID.randomUUID());
    }
}