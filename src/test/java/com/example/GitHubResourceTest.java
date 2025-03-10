package com.example;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class GitHubResourceTest {

    @Inject
    GitHubResource gitHubResource;

    @BeforeAll
    public static void setup() {
    }

    @AfterAll
    public static void teardown() {
    }

    @Test
    public void testGetRepositories() {
        String username = "Aleqs8188";

        given()
                .queryParam("username", username)
                .when().get("/github/repositories")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body("[0].name", is("ATM-System_Java"))
                .body("[0].ownerLogin", is("Aleqs8188"))
                .body("[0].branches[0].name", is("master"))
                .body("[0].branches[0].commitSha", is(null));
    }

    @Test
    public void testGetRepositoriesUserNotFound() {
        String username = "nonexistentuser";

        given()
                .queryParam("username", username)
                .when().get("/github/repositories")
                .then()
                .statusCode(404)
                .contentType(MediaType.APPLICATION_JSON)
                .body("status", is(404))
                .body("message", is("User not found"));
    }
}
