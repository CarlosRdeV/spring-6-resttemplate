package com.totalplay.spring6resttemplate.client;

import com.totalplay.spring6resttemplate.model.BeerDTO;
import com.totalplay.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void getBeerById() {
        Page<BeerDTO> beerDTOS = beerClient.listBeers();

        BeerDTO beerDTO = beerDTOS.getContent().getFirst();

        BeerDTO byId = beerClient.getBeerById(beerDTO.getId());

        assertNotNull(byId);
    }

    @Test
    void listBeers() {
        beerClient.listBeers();
    }

    @Test
    void listBeersByName() {
        beerClient.listBeers("ALE", null, null,null,null);
    }


    @Test
    void listBeersByNameAndBeerStyle() {
        beerClient.listBeers("ALE", BeerStyle.ALE, null,null,null);
    }


}