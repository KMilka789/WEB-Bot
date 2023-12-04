package model;

import lombok.RequiredArgsConstructor;
import util.OrderRequestBuilder;

import java.net.http.HttpRequest;


@RequiredArgsConstructor
public class OrderHttpRequest {
    private HttpRequest httpRequest;
    private final Order order;

    public OrderHttpRequest(Order order, String baseUrl, String endpoint, String method, AuthenticationHeaders authenticationHeaders) {
        this.order = order;
        createHttpRequest(baseUrl, order, endpoint, method, authenticationHeaders);

    }

    private void createHttpRequest(String baseUrl, Order order, String endpoint, String method, AuthenticationHeaders authenticationHeaders) {
        String data = OrderRequestBuilder.buildOrderRequest(order);
        httpRequest = HttpRequestBuilder.buildHttpRequest(baseUrl, endpoint, method, data, authenticationHeaders);

    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }
}