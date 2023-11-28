package Karpova;

import Karpova.model.Order;
import Karpova.model.OrderType;
import Karpova.model.Symbol;
import Karpova.service.BitmexClientFactory;
import Karpova.service.BitmexClient;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class App {
    public static void main(String[] args) {
        String apikey = "2hUPK17AqNU8-gXZZEbYA0Cx";
        String apiSecretKey = "MHZ9UYK8tdT7ZtIHTnYXcXR0_pP6JsopSmCmSvGT21d2rjtP";
    //    List.of("7862a816-38cf-42d5-bb89-7a74a58f03f4", "5a00a858-3375-43b9-a53b-bd3c59eb6d9a");
        BitmexClient bitmexClient = BitmexClientFactory.newTestnetBitmexClient(apikey,apiSecretKey);
        Order order = Order.builder()
                .orderQty(100.)
                .orderType(OrderType.LIMIT)
                .isBuy(true)
                .symbol(Symbol.XBTUSD)
                .price(3000.0)
                .build();

        try {
            bitmexClient.cancelOrder("123");
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }


    }
}
