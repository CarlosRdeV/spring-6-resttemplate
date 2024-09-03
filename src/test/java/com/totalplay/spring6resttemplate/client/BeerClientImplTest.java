package com.totalplay.spring6resttemplate.client;

import com.totalplay.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;


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