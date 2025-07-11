package com.zwickit.grpc.java.course.calculator.server;

import com.zwickit.grpc.java.course.calculator.CalculatorServiceGrpc;
import com.zwickit.grpc.java.course.calculator.SumRequest;
import com.zwickit.grpc.java.course.calculator.SumResponse;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {
        SumResponse response = SumResponse.newBuilder()
                .setSumResult(request.getNumber1() + request.getNumber2())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
