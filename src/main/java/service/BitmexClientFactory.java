package service;

import model.Order;
import util.Endpoints;
import util.Signature;

import java.net.http.HttpClient;
//     public BitmexClient newRealBitmexClient (String apiKey, String apiSecretKey) {
//    return new BitmexClient(apiKey, apiSecretKey, Endpoints.BASE_REAL_URL);

public class BitmexClientFactory {

    public static BitmexClient newTestnetBitmexClient(String apiKey, String apiSecretKey) {

        return new BitmexClient(apiKey, apiSecretKey, Endpoints.BASE_TEST_URL);
    }


}
