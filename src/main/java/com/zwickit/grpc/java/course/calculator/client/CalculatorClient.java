package com.zwickit.grpc.java.course.calculator.client;

import com.zwickit.grpc.java.course.calculator.CalculatorServiceGrpc;
import com.zwickit.grpc.java.course.calculator.SumRequest;
import com.zwickit.grpc.java.course.calculator.SumResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {

    public static void main(String[] args) {
        System.out.println("Starting Calculator Client...");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        System.out.println("Calculator Client is ready to make requests.");

        // Create a stub to connect to the Calculator Server
        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

        // Create a request & store the response
        SumRequest req = SumRequest.newBuilder()
                .setNumber1(10)
                .setNumber2(25)
                .build();
        SumResponse res = stub.sum(req);

        // Print the response from the server
        System.out.println(req.getNumber1() + " + " + req.getNumber2() + " = " + + res.getSumResult());

        System.out.println("Shutting down channel...");
        channel.shutdown();
    }

}
