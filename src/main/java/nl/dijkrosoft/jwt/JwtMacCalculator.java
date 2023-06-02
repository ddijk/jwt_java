package nl.dijkrosoft.jwt;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class JwtMacCalculator {

    //  header and payload are base64Url encoded
    public static String calculateJwtMac(String header, String payload, String secret) {

        return hmacSha256(header + "." + payload, secret);
    }

    public static void main(String[] args) {

        String encodedHeader = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImlzcyI6ImphcGllIn0";
        String encodePayload = "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ";
        String secret = "dd";
        String signature = hmacSha256(encodedHeader + "." + encodePayload, secret);
        System.out.println("signature:");
        System.out.println(signature);
        System.out.println("Expected:");
        String expectedResult = "o_XvcZEPUtn8_zuP8CFoWTe5weyccBAfSuwycdd31cU";
        System.out.println(expectedResult);
        System.out.println("Equal? " + expectedResult.equals(signature));
    }

    private static Charset getCharset() {
        return Charset.forName("UTF8");
    }

    private static String hmacSha256(String data, String secret) {
        try {

            byte[] hash = secret.getBytes(getCharset());
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
            sha256Hmac.init(secretKey);

            byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(getCharset()));

            return encode(signedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            System.err.println(ex);
            return null;
        }
    }

    private static String encode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
