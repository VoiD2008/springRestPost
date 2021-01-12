package org._void.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * A simple REST client using synchronous Spring rest template
 * This sample assume the REST service returns JSON payload
 */
@Component
public class SyncRestClient {

  private static final Logger LOG = LoggerFactory.getLogger(SyncRestClient.class);

  @Value("https://testonline.atol.ru/possystem/v4/getToken")
  private String tokenUrl;
  @Value("https://testonline.atol.ru/possystem/v4/v4-online-atol-ru_4179/sell")
  //@Value("http://127.0.0.1")
  private String apiUrl;

  private final RestOperations restTemplate;

  @Autowired
  public SyncRestClient(RestOperations restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Token getJsonDataToken() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    JSONObject auth = new JSONObject();

    try {
      auth.put("login", "v4-online-atol-ru");
      auth.put("pass", "iGFFuihss");
    } catch (JSONException e) {
      LOG.error(e.getMessage());
    }

    HttpEntity<String> request =
            new HttpEntity<String>(auth.toString(), headers);

    Token authObject = restTemplate.postForObject(tokenUrl,
            request,
            Token.class);

    return authObject;
  }

  public Object getJsonData() {
    Token t = getJsonDataToken();
    Object result = null;
    SellRequest sellReq = new SellRequest();
//    sellReq.external_id = "27052917561851774";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    LinkedList<String> l = new LinkedList<String>();
    l.add(t.token);
    headers.put("Token", l);


    try {
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

      HttpEntity<SellRequest> request =
              new HttpEntity<SellRequest>(sellReq, headers);

      result = restTemplate.postForObject(apiUrl,
              request,
              Object.class);

    } catch (HttpClientErrorException e) {
      e.printStackTrace();
    }
    return result;
  }
}
