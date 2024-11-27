package com.handson.chatbot.controller;

import com.handson.chatbot.service.AmazonService;
import com.handson.chatbot.service.ChuckNorrisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;


@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    AmazonService amazonService;

    @Autowired
    ChuckNorrisService chuckNorrisService;

    @RequestMapping(value = "/amazon", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>(amazonService.searchProducts(keyword), HttpStatus.OK);
    }


    @RequestMapping(value = "/chuckNorris", method = RequestMethod.GET)
    public ResponseEntity<?> getJokes(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>(chuckNorrisService.searchJokes(keyword), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = { RequestMethod.POST})
    public ResponseEntity<?> getBotResponse(@RequestBody BotQuery query) throws IOException {
        HashMap<String, String> params = query.getQueryResult().getParameters();
        String res = "Not found";
        if (params.containsKey("joke")) {
            res = chuckNorrisService.searchJokes(params.get("joke"));
        } else if (params.containsKey("product")) {
            res = amazonService.searchProducts(params.get("product"));
        }
        return new ResponseEntity<>(BotResponse.of(res), HttpStatus.OK);
    }

    private static class BotQuery {
        public QueryResult getQueryResult() {
            return queryResult;
        }

        public QueryResult queryResult;
    }

    static class QueryResult {
        HashMap<String, String> parameters;

        public HashMap<String, String> getParameters() {
            return parameters;
        }
    }

    static class BotResponse {
        String fulfillmentText;
        String source = "BOT";

        public String getFulfillmentText() {
            return fulfillmentText;
        }

        public String getSource() {
            return source;
        }

        public static BotResponse of(String fulfillmentText) {
            BotResponse res = new BotResponse();
            res.fulfillmentText = fulfillmentText;
            return res;
        }
    }
}