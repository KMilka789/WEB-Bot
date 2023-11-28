package Karpova.model;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpRequest;

@RequiredArgsConstructor

public class OrderHttpRequest {
    public static final Gson gson = new Gson();
    private HttpRequest httpRequest;
    private final Order order;

    public OrderHttpRequest(Order order, String baseUrl,String endpoint, String method, AuthenticationHeaders authenticationHeaders) {
        this.order = order;
        createHttpRequest(baseUrl, order, endpoint, method, authenticationHeaders);

    }
// не должен быть тут
    private void createHttpRequest(String baseUrl, Order order, String endpoint, String method, AuthenticationHeaders authenticationHeaders) {
        String data;
        if (order == null) {
            data = "";
        } else {
            OrderRequest orderRequest = OrderRequest.toRequest(order);
            data = gson.toJson(orderRequest);


        }

        HttpRequest.BodyPublisher bodyPublishers = method.equals("GET") ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofString(data);

        httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .method(method, bodyPublishers)
                .header("api-key", authenticationHeaders.getApiKey())
                .header("api-signature", authenticationHeaders.getSignature())
                .header("api-expires", String.valueOf(authenticationHeaders.getExpires()))  // Преобразование в строку
                .header("Content-Type", "application/json")
                .build();

    }
    public HttpRequest getHttpRequest() {
        return httpRequest;
    }
}

