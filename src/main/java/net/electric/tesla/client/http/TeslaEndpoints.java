package net.electric.tesla.client.http;

public class TeslaEndpoints {

    public static final String BASE_URL = "https://owner-api.teslamotors.com";
    private static final String OAUTH = "/oauth";
    private static final String API = "/api";
    private static final String VERSION_1 = "/1";

    public static final String AUTH_TOKEN = OAUTH + "/token";
    public static final String AUTH_REVOKE = OAUTH + "/revoke";
    public static final String PRODUCTS = API + VERSION_1 + "/products";

    private TeslaEndpoints() {
    }

}
