package com.zwickit.grpc.java.course.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Starting Greeting Server...");

        Server server = ServerBuilder
                .forPort(50051)
                // Register services
                .addService(new GreetServiceImpl())
                .build();
        server.start();

        System.out.println("Greeting Server started on port 50051");

        // Add a shutdown hook to gracefully stop the server
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down Greeting Server...");
                server.shutdown();
                System.out.println("Greeting Server shut down successfully.");
            })
        );

        // Keep the server running until it is terminated
        server.awaitTermination();
    }

}
