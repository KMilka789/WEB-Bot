package Karpova.service;

import Karpova.util.Endpoints;
//

public class BitmexClientFactory {
    public static BitmexClient newTestnetBitmexClient (String apiKey, String apiSecretKey) {
        return new BitmexClient(apiKey, apiSecretKey, Endpoints.BASE_TEST_URL);
    }
//    public BitmexClient newRealBitmexClient (String apiKey, String apiSecretKey) {
//        return new BitmexClient(apiKey, apiSecretKey, Endpoints.BASE_REAL_URL);
//    }

}
