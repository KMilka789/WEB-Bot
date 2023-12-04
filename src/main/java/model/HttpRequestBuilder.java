package model;

import java.net.URI;
import java.net.http.HttpRequest;

public class HttpRequestBuilder {
    public static HttpRequest buildHttpRequest(String baseUrl, String endpoint, String method,
                                               String data, AuthenticationHeaders authenticationHeaders) {
        HttpRequest.BodyPublisher bodyPublishers = method.equals("GET") ?
                HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(data);

        return HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .method(method, bodyPublishers)
                .header("api-key", authenticationHeaders.getApiKey())
                .header("api-signature", authenticationHeaders.getSignature())
                .header("api-expires", String.valueOf(authenticationHeaders.getExpires()))
                .header("Content-Type", "application/json")
                .build();


    }
}
