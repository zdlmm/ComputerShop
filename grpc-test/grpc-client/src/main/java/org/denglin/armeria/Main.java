package org.denglin.armeria;

import com.linecorp.armeria.client.WebClient;
import com.linecorp.armeria.client.grpc.GrpcClients;
import com.linecorp.armeria.common.AggregatedHttpResponse;

import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) {

        WebClient webClient = WebClient.of("http://127.0.0.1:8080/");
        AggregatedHttpResponse res1 = webClient.get("/component/all").aggregate().join();
        System.out.println(res1.content(Charset.defaultCharset()));

        OrderServiceGrpc.OrderServiceBlockingStub orderClient = GrpcClients.newClient("http://127.0.0.1:8080/",
        OrderServiceGrpc.OrderServiceBlockingStub.class);
        ComputerOrderRequest orderRequest = ComputerOrderRequest.newBuilder()
                        .setCpuId("15162b96-d8aa-4227-b8ed-e08912d46ac3")
                        .setGpuId("8feea0cf-cab8-4e94-a802-cdd4a35cbc9e")
                        .setMemoryId("cb427efc-4490-4aea-9146-784363532878")
                        .build();
        ComputerOrder order = orderClient.saveOrder(orderRequest);
        System.out.println(order);
    }
}