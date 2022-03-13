package com.pluralsight.client;

import com.pluralsight.model.Speaker;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.util.Arrays;
import java.util.List;

public class SpeakerSearchClient {

    private Client client;
    private final String SPEAKER_SEARCH_URI = "http://localhost:8080/search/speaker";

    public  SpeakerSearchClient () {
        client = ClientBuilder.newClient();
    }

    public List<Speaker> search(String param, List<String> searchValues,
                                String ageFrom, int ageFromVal,
                                String ageTo, int ageToVal) {
        return client
                .target(SPEAKER_SEARCH_URI)
                .queryParam(param, searchValues)
                .queryParam(ageFrom, ageFromVal)
                .queryParam(ageTo, ageToVal)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Speaker>>(){});
    }

    public static void main (String args []) {
        SpeakerSearchClient client = new SpeakerSearchClient();
        List<Speaker> results = client.search("company", Arrays.asList("pluralsight", "school"),
                "ageFrom", 20, "ageTo", 80);

        System.out.println(results.size());
    }

}
