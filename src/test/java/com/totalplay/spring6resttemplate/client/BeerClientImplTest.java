package com.totalplay.spring6resttemplate.client;

import com.totalplay.spring6resttemplate.model.BeerDTO;
import com.totalplay.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void testDeleteById() {

        Page<BeerDTO> beerDTOS = beerClient.listBeers();

        BeerDTO newDTO = beerDTOS.getContent().getFirst();

        beerClient.deleteBeerById(newDTO.getId());

        assertThrows(HttpClientErrorException.class, () -> {
            beerClient.getBeerById(newDTO.getId());
        });
    }

    @Test
    void testUpdateBeer() {

        Page<BeerDTO> beerDTOS = beerClient.listBeers();

        BeerDTO newDTO = beerDTOS.getContent().getFirst();

        final String newBeerName = "New Beer Name";
        newDTO.setBeerName(newBeerName);

        BeerDTO updatedDTO = beerClient.updateBeer(newDTO);

        assertEquals(newBeerName, updatedDTO.getBeerName());

    }

    @Test
    void testCreateBeer() {

        BeerDTO newDTO = BeerDTO.builder()
                .beerName("ShCareta")
                .beerStyle(BeerStyle.GOSE)
                .price(new BigDecimal("100.00"))
                .quantityOnHand(122)
                .upc("122345")
                .build();

        BeerDTO savedDTO = beerClient.createBeer(newDTO);

        assertNotNull(savedDTO);
    }

    @Test
    void testGetBeerById() {
        Page<BeerDTO> beerDTOS = beerClient.listBeers();

        BeerDTO beerDTO = beerDTOS.getContent().getFirst();

        BeerDTO byId = beerClient.getBeerById(beerDTO.getId());

        assertNotNull(byId);
    }

    @Test
    void testListBeers() {
        beerClient.listBeers();
    }

    @Test
    void testListBeersByName() {
        beerClient.listBeers("ALE", null, null, null, null);
    }


    @Test
    void testListBeersByNameAndBeerStyle() {
        beerClient.listBeers("ALE", BeerStyle.ALE, null, null, null);
    }


}