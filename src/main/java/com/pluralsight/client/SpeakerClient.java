package com.pluralsight.client;

import com.pluralsight.model.Speaker;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class SpeakerClient {

    private Client client;
    private final String SPEAKER_URI = "http://localhost:8080/speaker";

    public SpeakerClient() {
        client = ClientBuilder.newClient();
    }

    public Speaker get (Long l) {
        return client
                .target(SPEAKER_URI)
                .path(String.valueOf(l))
                .request(MediaType.APPLICATION_JSON)
                .get(Speaker.class);
    }

    public List<Speaker> get () {
        Response response = client
                .target(SPEAKER_URI)
                .request(MediaType.APPLICATION_JSON)
                .get();

        List<Speaker> speakers = response.readEntity(new GenericType<List <Speaker>>() {});

        return speakers;
    }

    public Speaker post (Speaker speaker) {
        Response response = client
                .target(SPEAKER_URI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(speaker, MediaType.APPLICATION_JSON));
        Speaker returnSpeaker = response.readEntity(Speaker.class);
        return returnSpeaker;
    }

    public Speaker put (Speaker speaker) {
        Response response = client
                .target(SPEAKER_URI)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(speaker, MediaType.APPLICATION_JSON));
        Speaker returnSpeaker = response.readEntity(Speaker.class);
        return returnSpeaker;
    }

    public static void main (String args []) {
        SpeakerClient client = new SpeakerClient();
        Speaker speaker = client.get(1L);

        System.out.println(speaker.getName());

        List<Speaker> speakers = client.get();
        System.out.println(speakers.size());

        speaker = new Speaker();
        speaker.setName("Alex");
        speaker.setCompany("School");
        speaker = client.post(speaker);

        System.out.println(speaker.getId());

        speaker.setCompany("Wayne Enterprises");

        speaker = client.put(speaker);

        System.out.println(speaker.getCompany());
    }

}
