package Karpova.service;

import com.google.gson.Gson;
import Karpova.model.AuthenticationHeaders;
import Karpova.model.Order;
import Karpova.model.OrderHttpRequest;
import Karpova.model.OrderRequest;
import Karpova.util.Endpoints;
import Karpova.util.Signature;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

// клиент для взаимодействия с АПи биржи
// запустить первый реквест
// отправлять ордера и кэнселить
// написание алгоритма


public class BitmexClient {
    private final HttpClient httpClient = HttpClient.newBuilder().build();
    private static final Gson gson = new Gson();
    private final Signature signature = new Signature();
    private final String apiKey;
    private final String apiSecretKey;
    private final String baseUrl;
    //  private final boolean isReal;
    private final int EXPIRES_DELAY = 5;

    public BitmexClient(String apiKey, String apiSecretKey, String baseUrl) {
        this.apiKey = apiKey;
        this.apiSecretKey = apiSecretKey;
        this.baseUrl = baseUrl;
        // this.isReal = isReal;
    }


    public void sendOrder(Order order) throws NoSuchAlgorithmException, InvalidKeyException {
        String httpMethod = "POST";
        String data = "";
        if (order == null) {
            data = "";
        } else {
            OrderRequest orderRequest = OrderRequest.toRequest(order);
            data = gson.toJson(orderRequest);
        }
        System.out.println(data);
        String base = "/api/v1";

        OrderHttpRequest orderHttpRequest = new OrderHttpRequest(order, baseUrl, Endpoints.ORDER_ENDPOINT, httpMethod, getAuthenticationHeaders(httpMethod, data, base + Endpoints.ORDER_ENDPOINT));
        try {
            HttpResponse<String> response = httpClient.send(orderHttpRequest.getHttpRequest(), HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Body: " + response.body());
            System.out.println(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getOrders() throws NoSuchAlgorithmException, InvalidKeyException {
        String httpMethod = "GET";
        String data = "";

        String base = "/api/v1";

        OrderHttpRequest orderHttpRequest = new OrderHttpRequest(null, baseUrl, Endpoints.ORDER_ENDPOINT, httpMethod, getAuthenticationHeaders(httpMethod, data, base + Endpoints.ORDER_ENDPOINT));
        try {
            HttpResponse<String> response = httpClient.send(orderHttpRequest.getHttpRequest(), HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Body: " + response.body());
            System.out.println(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void cancelOrder(String orderId) throws NoSuchAlgorithmException, InvalidKeyException {
        String httpMethod = "DELETE";
        String data = "{\"orderID\": [\"7862a816-38cf-42d5-bb89-7a74a58f03f4\", \"5a00a858-3375-43b9-a53b-bd3c59eb6d9a\"]}";


        System.out.println(data);
        String base = "/api/v1";

        //  OrderHttpRequest orderHttpRequest = new OrderHttpRequest(, baseUrl, Endpoints.ORDER_ENDPOINT, httpMethod, getAuthenticationHeaders(httpMethod, data, base+Endpoints.ORDER_ENDPOINT));
        AuthenticationHeaders authenticationHeaders = getAuthenticationHeaders(httpMethod, data, base + Endpoints.ORDER_ENDPOINT);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + Endpoints.ORDER_ENDPOINT))
                .method(httpMethod, HttpRequest.BodyPublishers.ofString(data))
                .header("api-key", authenticationHeaders.getApiKey())
                .header("api-signature", authenticationHeaders.getSignature())
                .header("api-expires", String.valueOf(authenticationHeaders.getExpires()))  // Преобразование в строку
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Body: " + response.body());
            System.out.println(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private AuthenticationHeaders getAuthenticationHeaders(String httpMethod, String data, String path) throws NoSuchAlgorithmException, InvalidKeyException {
        long expire = System.currentTimeMillis() / 1000 + EXPIRES_DELAY; // определяет время действия подписи
        System.out.println("Debug: path for authentication: " + path);
        String signatureStr = signature.getSignature(apiSecretKey, httpMethod + path + expire + data);// строка создает подпись запроса

        return AuthenticationHeaders.builder()
                .apiKey(apiKey)
                .signature(signatureStr)
                .expires(expire)
                .build();
    }
}
