package Karpova.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


//  у нас есть хекс функция которая берется по хэш мапу
//
public class Signature {
    public String getSignature(String secretKey, String message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256Hmac.init(secretKeySpec);

        byte[] hmacBytes = sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8)); // вычисление хэша (кода подписи) для заданного сообщения
        // doFinal принимает массив байтов в качестве входных данных и вовзращает результат хеширования
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte b : hmacBytes) {
            hexStringBuilder.append(String.format("%02x", b));

        }
        return hexStringBuilder.toString();
    }

}
