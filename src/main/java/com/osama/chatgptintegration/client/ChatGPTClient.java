package com.osama.chatgptintegration.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osama.chatgptintegration.domain.GPTRequest;
import com.osama.chatgptintegration.exception.GPTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ChatGPTClient {

    private static final Logger logger = LoggerFactory.getLogger(ChatGPTClient.class);
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "LOL";
    private static final String AUTH_TOKEN = "Bearer " + API_KEY;
    private static HttpHeaders headers;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<String> askGPT(String prompt) throws GPTException {
        GPTRequest request = new GPTRequest(prompt, 50, "davinci-002");
        try {
            String body = objectMapper.writeValueAsString(request);
            ResponseEntity<String> exchange = restTemplate.exchange(
                    OPENAI_API_URL,
                    HttpMethod.POST,
                    new HttpEntity<>(body, getHeaders()),
                    String.class);
            if (exchange.getStatusCode().is2xxSuccessful()) {
                return new ResponseEntity<>(exchange.getBody(), HttpStatus.OK);
            }
        } catch (JsonProcessingException jsonProcessingException) {
            logger.error("Cannot parse the prompt request object!");
        }
        throw new GPTException("Cannot complete operation due to some reason!");
    }

    private HttpHeaders getHeaders() {
        if (headers == null) {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", AUTH_TOKEN);
        }
        return headers;
    }

}
