package com.example;

import com.example.model.Branch;
import com.example.model.Repository;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "https://api.github.com")
@ClientHeaderParam(name = "Accept", value = "application/json")
public interface GitHubClient {

    @GET
    @Path("/users/{user}/repos")
    Uni<List<Repository>> getUserRepos(@PathParam("user") String username);

    @GET
    @Path("/repos/{owner}/{repo}/branches")
    Uni<List<Branch>> getBranches(@PathParam("owner") String owner, @PathParam("repo") String repo);
}