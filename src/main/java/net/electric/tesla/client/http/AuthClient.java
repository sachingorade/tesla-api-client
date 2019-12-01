package net.electric.tesla.client.http;

import net.electric.tesla.client.http.model.GetAccessTokenRequest;
import net.electric.tesla.client.http.model.GetAccessTokenResponse;
import net.electric.tesla.client.http.model.RevokeAccessTokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "tesla-auth-client", url = "${tesla.api.url}")
public interface AuthClient {

    @PostMapping(value = TeslaEndpoints.AUTH_TOKEN)
    GetAccessTokenResponse authenticate(@RequestBody GetAccessTokenRequest request);

    @PostMapping(value = TeslaEndpoints.AUTH_REVOKE)
    void logout(@RequestBody RevokeAccessTokenRequest request);

}
