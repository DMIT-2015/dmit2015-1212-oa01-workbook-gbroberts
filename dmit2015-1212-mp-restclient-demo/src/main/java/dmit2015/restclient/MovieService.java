package dmit2015.restclient;

import dmit2015.restclient.Movie;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;

@RegisterRestClient(baseUri = "https://dmit2015-1212-fbrt-demo-cb656-default-rtdb.firebaseio.com")
public interface MovieService {
    @POST
    @Path("/movies.json")
    JsonObject create(Movie newMovie);

    @GET
    @Path("/movies.json")
    Map<String, Movie> list();

    @GET
    @Path("/movies/{key}.json")
    Movie findById(@PathParam("key") String id);

    @PUT
    @Path("/movies/{key}.json")
    Movie update(@PathParam("key") String id, Movie existingMovie);

    @DELETE
    @Path("/movies/{key}.json")
    void delete(@PathParam("key") String id);

}
