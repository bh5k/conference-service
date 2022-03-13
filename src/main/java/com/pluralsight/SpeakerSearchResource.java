package com.pluralsight;

import com.pluralsight.model.Speaker;
import com.pluralsight.repository.SpeakerRepository;
import com.pluralsight.repository.SpeakerRepositoryStub;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("search/speaker")
public class SpeakerSearchResource {

    private SpeakerRepository speakerRepository = new SpeakerRepositoryStub();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchForSpeakers(@QueryParam(value="company")List<String> companies,
                                      @QueryParam(value = "ageFrom") int ageFromVal,
                                      @QueryParam(value = "ageTo") int ageToVal) {

        List<Speaker> speakers = speakerRepository.findByCompany(companies, ageFromVal, ageToVal);

        if((speakers == null) || speakers.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Speaker>>(speakers){}).build();
    }
}
