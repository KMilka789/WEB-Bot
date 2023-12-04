package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationHeaders {
    private long expires;
    private String apiKey;
    private String signature;
}
