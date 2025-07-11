package com.zwickit.grpc.java.course.calculator.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class CalculatorServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Starting Calculator Server...");

        Server server = ServerBuilder
                .forPort(50052)
                .addService(new CalculatorServiceImpl())
                .build();
        server.start();

        System.out.println("Calculator Server started on port 50052");

        // Add a shutdown hook to gracefully stop the server
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    System.out.println("Shutting down Calculator Server...");
                    server.shutdown();
                    System.out.println("Calculator Server shut down successfully.");
                })
        );

        // Keep the server running until it is terminated
        server.awaitTermination();
    }

}
