package ru.yandex.practicum.grpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest;
import ru.yandex.practicum.grpc.telemetry.hubRouter.HubRouterControllerGrpc;


@Service
public class HubRouterGrpcClient {
    private final HubRouterControllerGrpc.HubRouterControllerBlockingStub hubRouterClient;

    public HubRouterGrpcClient(@GrpcClient("hub-router") HubRouterControllerGrpc.HubRouterControllerBlockingStub hubRouterClient) {
        this.hubRouterClient = hubRouterClient;
    }

    public void sendData(DeviceActionRequest deviceActionRequest) {
        hubRouterClient.handleDeviceAction(deviceActionRequest);
    }
}
