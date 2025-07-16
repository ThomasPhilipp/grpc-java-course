package com.zwickit.grpc.java.course.greeting.client;

import com.zwickit.grpc.java.course.greet.*;
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
        //GreetServiceGrpc.GreetServiceFutureStub asyncClient = GreetServiceGrpc.newFutureStub(transportChannel);

        //sendUnary(syncClient);
        sendServerStreaming(syncClient);

        System.out.println("Shutting down transport channel...");
        transportChannel.shutdown();
    }

    private static void sendUnary(GreetServiceGrpc.GreetServiceBlockingStub client) {
        // Create a request & store the response
        Greeting greeting = Greeting.newBuilder()
                        .setFirstName("Max")
                        .setLastName("Mustermann")
                        .build();
        GreetRequest req = GreetRequest.newBuilder()
                        .setGreeting(greeting)
                        .build();
        GreetResponse res = client.greet(req);

        // Print the response from the server
        System.out.println("Received response: " + res.getResult());
    }

    private static void sendServerStreaming(GreetServiceGrpc.GreetServiceBlockingStub client) {
        GreetManyTimesRequest request = GreetManyTimesRequest.newBuilder()
                .setGreeting(
                        Greeting.newBuilder().setFirstName("Max").build()
                ).build();

        // Stream the responses (in a blocking manner until onComplete is sent)
        client.greetManyTimes(request).forEachRemaining(greetManyTimesResponse -> System.out.println(greetManyTimesResponse.getResult()));
    }
}
