package org.example;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        DisposableServer server = HttpServer.create()   // Prepares an HTTP server ready for configuration
                .host("0.0.0.0")
                .port(8080)
                .handle((request, response) -> response.sendString(Mono.just("123")))
                // an ephemeral port when binding the server
                // .route(routes ->
                //         // The server will respond only on POST requests
                //         // where the path starts with /test and then there is path parameter
                //         routes.get("/test/{param}", (request, response) ->
                //                 response.sendString(request.receive()
                //                         .asString()
                //                         .map(s -> s + ' ' + request.param("param") + '!')
                //                         .log("http-server"))))
                .bindNow();// Starts the server in a blocking fashion, and waits for it to finish its initialization


        server.onDispose().block();
    }
}
