package com.sachin.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

public class MainVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }

  @Override
  public void start() {
    HttpServer server = vertx.createHttpServer();

    server.requestHandler(request -> {
      // Check if the request path is "/hello"
      if ("/hello".equals(request.path())) {
        // Set content type to JSON
        request.response().putHeader("Content-Type", "application/json");

        // Write a JSON response
        HttpServerResponse response = request.response();
        response.end("{\"message\": \"Hello, World!\"}");
      } else {
        // Respond with 404 Not Found for other paths
        request.response().setStatusCode(404).end();
      }
    });

    server.listen(4321); // Start the server on port 8080
    System.out.println("Server started at http://localhost:8080/");
  }
}

