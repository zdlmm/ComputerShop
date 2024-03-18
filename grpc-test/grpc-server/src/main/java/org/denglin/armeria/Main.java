package org.denglin.armeria;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.grpc.GrpcService;

public class Main {
    public static void main(String[] args) throws Exception {
        final Server server = newServer(8080);

        server.start().join();
    }

    static Server newServer(int port) throws Exception {
        final GrpcService grpcService =
                GrpcService.builder()
                        .addService(new OrderService())
                        .enableUnframedRequests(true)
                        .exceptionHandler(new GrpcExceptionHandler())
                        .build();

        return Server.builder()
                .http(port)
                .service(grpcService)
                .annotatedService("/component", new ComponentService())
                .build();
    }
}