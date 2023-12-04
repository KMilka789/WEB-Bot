import util.Signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

//
//public class BitmexRequestBuilder {
//    private static String apiSecretKey = "MHZ9UYK8tdT7ZtIHTnYXcXR0_pP6JsopSmCmSvGT21d2rjtP";
//    private static String expires = System.currentTimeMillis() / 1000 + 5;
//    private static Signature signature = new Signature().getSignature(apiSecretKey,"GET/realtime" + expires );
//  private String apikey = "2hUPK17AqNU8-gXZZEbYA0Cx";
//
//
//
////    public static String authenticate(String apiKey, String apiSecret, long expires) {
////        expires = System.currentTimeMillis() / 1000 + 5;
////        return "{\"op\": \"authKeyExpires\", \"args\": [\"" + apiKey + "\", " + expires + ", \"" + signature + "\"]}";
////    }
//public static String authenticate(String apiKey, String apiSecret, long expires) {
//    try {
//        Signature signature = new Signature();
//        String message = "GET/realtime" + expires; // Формируем сообщение для подписи
//        String signatureValue = signature.getSignature(apiSecret, message);
//
//        return "{\"op\": \"authKeyExpires\", \"args\": [\"" + apiKey + "\", " + expires + ", \"" + signatureValue + "\"]}";
//    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
//        e.printStackTrace();
//        return null;
//    }
//}
//    public static String checkBalance(String apiKey, String apiSecret, long expires ) {
//        return "{\"op\": \"subscribe\", \"args\": [\"margin\"]}";
//    }
//    public static String orderBook () { //ваши ордера в режиме реального времени
//        return "{\"op\": \"subscribe\", \"args\": [\"order\"]}";
//
//    }
//    public static String position () { // обновление иформации о ваших позициях
//        return "{\"op\": \"subscribe\", \"args\": [\"position\"]}";
//    }
//
//    public static String transact () { // обновление информации о пополнении/снятии средств
//        return "{\"op\": \"subscribe\", \"args\": [\"transact\"]}";
//    }
//
//
//}
public class BitmexRequestBuilder {

    public static String authenticate(String apiKey, String apiSecret, long expires) {
        try {
            String message = "GET/realtime" + expires; // Формируем сообщение для подписи
            Signature signature = new Signature();
            String signatureValue = signature.getSignature(apiSecret, message);

            return String.format("{\"args\": [\"%s\", %d, \"%s\"], \"op\": \"authKeyExpires\"}", apiKey, expires, signatureValue);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }
    // String apiKey = "DI-FVmnjsNvWGcJLEyVxqncH";
    //long expires = 1701428369;
    //String signatureValue = "DI-FVmnjsNvWGcJLEyVxqncH:1701428369:GEbbyxmv0V7GWuDDIsvsBtonH3awhmwUbsl+Yun5EO0=";
    //
    //String firstMessage = "{\"op\": \"authKeyExpires\", \"args\": [\"" + apiKey + "\", " + expires + ", \"" + signatureValue + "\"]}";
    // {"args":["DI-FVmnjsNvWGcJLEyVxqncH",1701428369,"DI-FVmnjsNvWGcJLEyVxqncH:1701428369:GEbbyxmv0V7GWuDDIsvsBtonH3awhmwUbsl+Yun5EO0="],"op":"authKeyExpires"}

//    public static String checkBalance(String apiKey, String apiSecret, long expires) {
//        return "{\"op\": \"subscribe\", \"args\": [\"margin\"]}";
//    }

    public static String orderBook() {
        return "{\"op\": \"subscribe\", \"args\": [\"order\"]}";
    }

    public static String position() {
        return "{\"op\": \"subscribe\", \"args\": [\"position\"]}";
    }

    public static String transact() {
        return "{\"op\": \"subscribe\", \"args\": [\"transact\"]}";
    }
}
