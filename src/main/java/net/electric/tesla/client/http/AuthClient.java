package net.electric.tesla.client.http;

import feign.Headers;
import feign.RequestLine;
import net.electric.tesla.client.http.request.GetAccessTokenRequest;
import net.electric.tesla.client.http.response.GetAccessTokenResponse;
import net.electric.tesla.client.http.request.RevokeAccessTokenRequest;

public interface AuthClient {

    String FEIGN_METHOD_POST = "POST ";

    @RequestLine(value = FEIGN_METHOD_POST + TeslaEndpoints.AUTH_TOKEN)
    @Headers("Content-type: application/json")
    GetAccessTokenResponse authenticate(GetAccessTokenRequest request);

    @RequestLine(value = FEIGN_METHOD_POST +  TeslaEndpoints.AUTH_REVOKE)
    @Headers("Content-type: application/json")
    void logout(RevokeAccessTokenRequest request);

}
