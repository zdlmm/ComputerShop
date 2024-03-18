package org.denglin.armeria;

import io.grpc.stub.StreamObserver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderService extends OrderServiceGrpc.OrderServiceImplBase {

    private final AtomicInteger idGenerator = new AtomicInteger(1);
    private final Map<Integer, ComputerOrder> computerOrders = new ConcurrentHashMap<>();

    @Override
    public void saveOrder(ComputerOrderRequest request, StreamObserver<ComputerOrder> responseObserver) {
        final int id = idGenerator.getAndIncrement();
        final ComputerOrder computerOrder = ComputerOrder.newBuilder()
                .setOrderId(id)
                .setCpuId(request.getCpuId())
                .setGpuId(request.getGpuId())
                .setMemoryId(request.getMemoryId())
                .build();
        computerOrders.put(id, computerOrder);
        final ComputerOrder stored = computerOrder;
        responseObserver.onNext(stored);
        responseObserver.onCompleted();
    }

    @Override
    public void getOrderById(GetOrderRequest request, StreamObserver<ComputerOrder> responseObserver) {
        final ComputerOrder computerOrder = computerOrders.get(request.getId());
        if (computerOrder == null)
            throw new OrderNotFoundException("Order doesn't exist with Order ID: " + request.getId());
        else {
            responseObserver.onNext(computerOrder);
            responseObserver.onCompleted();
        }
    }

}
