package com.zwickit.grpc.java.course.greeting.server;

import com.zwickit.grpc.java.course.greet.*;
import io.grpc.stub.StreamObserver;

/**
 * Implementation of the GreetService defined in the gRPC service definition.
 * This class will handle the incoming gRPC requests for greeting operations.
 */
public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        // Extract the greeting information from the request
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();
        String lastName = greeting.getLastName();

        // Create a personalized greeting message as response
        String result = "Hello, " + firstName + " " + lastName + "! Welcome to gRPC Java Course!";
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();

        // Send the response back to the client
        responseObserver.onNext(response);

        // Complete the response stream
        responseObserver.onCompleted();
    }

    @Override
    public void greetManyTimes(GreetManyTimesRequest request, StreamObserver<GreetManyTimesResponse> responseObserver) {
        String firstName = request.getGreeting().getFirstName();

        try {
            for (int i=0; i<10; i++) {
                String result = "Hello, " + firstName + ", response number: " + i + "!";
                GreetManyTimesResponse response = GreetManyTimesResponse.newBuilder()
                        .setResult(result)
                        .build();

                responseObserver.onNext(response);

                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Complete the response stream
            responseObserver.onCompleted();
        }

    }
}
