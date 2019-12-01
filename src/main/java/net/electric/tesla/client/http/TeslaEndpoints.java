package net.electric.tesla.client.http;

public class TeslaEndpoints {

    private static final String OAUTH = "/oauth";

    public static final String AUTH_TOKEN = OAUTH + "/token";
    public static final String AUTH_REVOKE = OAUTH + "/revoke";

    private TeslaEndpoints() {
    }

}
