package com.sterniczuk.MF;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class GetDataFromMf {

    public Map<String,String> checkNIP(String NIP) throws Exception {

        Map<String,String> dataFromMf = new HashMap<>();
        LocalDate date = LocalDate.now();
        final String URL = "https://wl-api.mf.gov.pl/api/search/nip/" + NIP + "?date=" + date;

        RestTemplate restTemplate = new RestTemplate();

        //JsonNode object  = restTemplate.getForObject(URL, JsonNode.class);
        ResponseEntity<JsonNode> request = restTemplate.getForEntity(URL, JsonNode.class);
        HttpStatus statusCode = request.getStatusCode();

        if(statusCode.toString().equals("200 OK")){
            JsonNode object = Objects.requireNonNull(request.getBody()).at("/result/subject");

            dataFromMf.put("name",object.get("name").asText());
            dataFromMf.put("NIP",object.get("nip").asText());
            dataFromMf.put("REGON",object.get("regon").asText());

            if(object.get("residenceAddress").asText().equals("null")){
                dataFromMf.put("address",object.get("workingAddress").asText());
            }else{
                dataFromMf.put("address",object.get("residenceAddress").asText());
            }
            return dataFromMf;
        }else
            throw new Exception("The given tax identification number was not found");
    }
}
