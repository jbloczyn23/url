package com.cloudflare.url.controller;

import com.cloudflare.url.database.data.UrlData;
import com.cloudflare.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UrlController {

    @Autowired
    UrlService urlService;

    @Description(value = "Endpoint to be used for creating the short url. " +
            "The client sends in a long url and the response includes the " +
            "inputted long url as well as the short url to be used for redirect")
    @PostMapping("/url/create")
    public ResponseEntity<UrlData> createShortUrl(@RequestBody UrlData input) {
        UrlData response = urlService.saveUrl(input);
        String fullUrl = "http://localhost:8080/url/" + response.getShorturl();
        response.setShorturl(fullUrl);

        return ResponseEntity.ok(response);
    }

    @Description(value = "Endpoint to be used for the actual redirect.  The client" +
            " will request a url that includes the short url and this endpoint will" +
            " return a redirect to the proper full length url")
    @GetMapping("/url/{shorturl}")
    public void redirectToLongUrl(@PathVariable("shorturl") String shortUrl,
                                  HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.sendRedirect(urlService.getFullUrl(shortUrl));
    }

    @Description(value = "Endpoint to be used for seeing how often a given short url has been" +
            " requested during a given time period.  The valid inputs for timePeriod are: " +
            "day (last 24 hours), week (last 7 days) or all")
    @GetMapping("/url/{shorturl}/accessed/{timeperiod}")
    public ResponseEntity getAccessMetrics(@PathVariable("shorturl") String shortUrl,
                                           @PathVariable("timeperiod") String timePeriod) {
        if(!timePeriod.equalsIgnoreCase("day")
            && !timePeriod.equalsIgnoreCase("week")
            && !timePeriod.equalsIgnoreCase("all")) {
            String error400 =  "Invalid time period.  Please use one of the following: day, week or all";
            return new ResponseEntity<>(error400, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        AccessModel accessModel = urlService.getAccess(shortUrl, timePeriod);

        return ResponseEntity.ok(accessModel);

    }
}
