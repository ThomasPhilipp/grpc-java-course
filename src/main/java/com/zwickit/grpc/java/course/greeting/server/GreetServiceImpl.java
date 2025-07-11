package com.zwickit.grpc.java.course.greeting.server;

import com.zwickit.grpc.java.course.greet.GreetRequest;
import com.zwickit.grpc.java.course.greet.GreetResponse;
import com.zwickit.grpc.java.course.greet.GreetServiceGrpc;
import com.zwickit.grpc.java.course.greet.Greeting;
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

}
