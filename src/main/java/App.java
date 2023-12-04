import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.WebSocketContainer;
import model.Order;
import model.OrderRequest;
import model.OrderType;
import model.Symbol;
import service.BitmexClientFactory;
import service.BitmexClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class App {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        //     String apikey = "2hUPK17AqNU8-gXZZEbYA0Cx";
        //    String apiSecretKey = "MHZ9UYK8tdT7ZtIHTnYXcXR0_pP6JsopSmCmSvGT21d2rjtP";

        //   List.of("7862a816-38cf-42d5-bb89-7a74a58f03f4", "5a00a858-3375-43b9-a53b-bd3c59eb6d9a");
//        BitmexClient bitmexClient = BitmexClientFactory.newTestnetBitmexClient(apikey, apiSecretKey);
//        Order order = Order.builder()
//                .orderQty(100.)
//                .orderType(OrderType.LIMIT)
//                .isBuy(true)
//                .symbol(Symbol.XBTUSD)
//                .price(3000.0)
//                .build();
//
//        //    try {
//        bitmexClient.sendOrder(order);
//        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
//            throw new RuntimeException(e);
//        }
        //        long expires = 1;
//        String signature= "ьл5даь";
//        System.out.println("{\"op\": \"authKeyExpires\", \"args\": [\"" + apikey + "\", " + expires + ", \"" + signature + "\"]}\n");
        String bitmexWebSocketEndpoint = "wss://testnet.bitmex.com/realtime";
        String apiKey = "2hUPK17AqNU8-gXZZEbYA0Cx";
        String apiSecret = "MHZ9UYK8tdT7ZtIHTnYXcXR0_pP6JsopSmCmSvGT21d2rjtP";

        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            BitmexWebSocketClient bitmexWebSocketClient = new BitmexWebSocketClient(apiKey, apiSecret);
            URI uri = new URI(bitmexWebSocketEndpoint);
            try {
                container.connectToServer(bitmexWebSocketClient, uri);
            } catch (DeploymentException | IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
