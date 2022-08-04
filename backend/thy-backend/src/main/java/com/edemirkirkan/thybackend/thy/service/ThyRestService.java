package com.edemirkirkan.thybackend.thy.service;

import com.edemirkirkan.thybackend.thy.dto.ThyAirpotInfoRestDto;
import com.edemirkirkan.thybackend.thy.dto.ThyBoardingPassRestDto;
import com.edemirkirkan.thybackend.thy.dto.ThyCustomerRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ThyRestService {

    @Qualifier("thy_rest_template")
    private final RestTemplate template;

    @Qualifier("thy_headers")
    private final HttpHeaders headers;


    public ThyBoardingPassRestDto printBoardingPass(String pnr) {

        final String URL = String.format(
                "https://api.turkishairlines.com/test/PrintboardingCard?pnr=%s",
                pnr
        );

        ResponseEntity<ThyBoardingPassRestDto> response = null;
        ResponseEntity<String> response02 = null;

        try {
             response = template
                    .exchange(URL, HttpMethod.GET, new HttpEntity<String>(headers), ThyBoardingPassRestDto.class);

             response02 = template
                    .exchange(URL, HttpMethod.GET, new HttpEntity<String>(headers), String.class);
        }
        catch (Error e) {
            System.out.println(e);
        }

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Printing boarding pass failed !!!");
        }
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        System.out.println(response02.getBody());

        return response.getBody();
    }
    public ThyCustomerRestDto retrieveCustomerInfo() {
        return null;
    }

    public ThyAirpotInfoRestDto retrieveAirportInfo() {
        return null;
    }
}
