package com.oca.Desafio_Tres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController()
public class UfController {
    private static final Logger _log = LoggerFactory.getLogger(UfController.class);
        @GetMapping("/breeds/list/all")
        public List<Breed> getAllBreeds() {
            _log.info("/breeds/list/all");
            RetrieverBreedInterface retrieverBreed = new RetrieverBreed(new ConsumerBreed(restTemplate));

            return retrieverBreed.retrieveAll();
        }
}
