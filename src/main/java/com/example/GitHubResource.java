package com.example;

import com.example.error.ErrorResponse;
import com.example.model.Repository;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Path("/github")
public class GitHubResource {

    @RestClient
    GitHubClient gitHubClient;

    @GET
    @Path("/repositories")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getRepositories(@QueryParam("username") String username) {
        return gitHubClient.getUserRepos(username)
                .onItem().transformToUni(repos -> {
                    if (repos == null || repos.isEmpty()) {
                        return Uni.createFrom().item(List.of());
                    }

                    List<Uni<Repository>> repositoryUnis = repos.stream()
                            .filter(repo -> !repo.isFork())
                            .map(repo -> gitHubClient.getBranches(username, repo.getName())
                                    .onItem().transform(branches ->
                                            new Repository(repo.getName(), username, branches)))
                            .collect(Collectors.toList());

                    return Uni.combine().all().unis(repositoryUnis)
                            .combinedWith(list -> (List<Repository>) list);
                })
                .onItem().transform(repos -> {
                    if (repos.isEmpty()) {
                        return Response.status(Response.Status.NOT_FOUND)
                                .entity(new ErrorResponse(404, "User not found"))
                                .build();
                    } else {
                        return Response.ok(repos).build();
                    }
                });
    }
}
