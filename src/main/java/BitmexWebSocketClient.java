import jakarta.websocket.*;
import lombok.extern.slf4j.Slf4j;
import util.Signature;

import java.net.URI;

@ClientEndpoint
@Slf4j
public class BitmexWebSocketClient {


    private String apiKey;
    private String apiSecret;
    private Signature signature = new Signature();
    private static long expires = System.currentTimeMillis() / 1000 + 5;

    public BitmexWebSocketClient( String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to Bitmex Websocket");
        try {
            String signatureValue = signature.getSignature(apiSecret, "GET/realtime" + expires);
            String authMessage = "{\"op\": \"authKeyExpires\", \"args\": [\"" + apiKey + "\", " + expires + ", \"" + signatureValue + "\"]}";
            session.getBasicRemote().sendText(authMessage);
        } catch (Exception e) {
            log.error("Ошибка при отправке запроса аутентификации: " + e.getMessage());
        }
    }


    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }


    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("Connection closed: " + closeReason.getReasonPhrase());
    }



    @OnMessage
    public void onMessage(PongMessage pongMessage) {
        //    System.out.println(message);
    }


}
