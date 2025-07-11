package com.zwickit.grpc.java.course.greeting.client;

import com.zwickit.grpc.java.course.greet.GreetResponse;
import com.zwickit.grpc.java.course.greet.GreetRequest;
import com.zwickit.grpc.java.course.greet.GreetServiceGrpc;
import com.zwickit.grpc.java.course.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("Starting Greeting Client...");

        // Transport channel to connect to the server
        ManagedChannel transportChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()// in development only, in production use TLS
                .build();

        // Create a synchronous blocking stub to call the service
        GreetServiceGrpc.GreetServiceBlockingStub syncClient = GreetServiceGrpc.newBlockingStub(transportChannel);

        // Create a request & store the response
        Greeting greeting = Greeting.newBuilder()
                        .setFirstName("Max")
                        .setLastName("Mustermann")
                        .build();
        GreetRequest req = GreetRequest.newBuilder()
                        .setGreeting(greeting)
                        .build();
        GreetResponse res = syncClient.greet(req);

        // Print the response from the server
        System.out.println("Received response: " + res.getResult());

        System.out.println("Shutting down transport channel...");
        transportChannel.shutdown();
    }

}
