package net.electric.tesla.client.http;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import net.electric.tesla.client.http.model.GetAccessTokenRequest;
import net.electric.tesla.client.http.model.RevokeAccessTokenRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static net.electric.tesla.client.TestUtils.loadResourceAsObject;
import static net.electric.tesla.client.TestUtils.loadResourceAsString;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AuthClientTest {

    private WireMockServer wireMockServer = new WireMockServer(45678);

    @Autowired
    private AuthClient authClient;

    @BeforeEach
    void setupServer() {
        wireMockServer.start();
        WireMock.configureFor(wireMockServer.port());
    }

    @AfterEach
    void stopServer() {
        wireMockServer.stop();
    }

    @Test
    void testAuthenticateGetAccessTokenRequest() {
        stubFor(post("/oauth/token")
            .withHeader(HttpHeaders.CONTENT_TYPE, equalTo(MediaType.APPLICATION_JSON_VALUE))
            .willReturn(WireMock.aResponse()
                            .withStatus(HttpStatus.OK.value())
                            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .withBody(loadResourceAsString("mocks/tesla/auth/get-access-token-response-ok.json"))));

        GetAccessTokenRequest authRequest = getTestAccessTokenRequest();
        authClient.authenticate(authRequest);

        verify(postRequestedFor(urlMatching("/oauth/token"))
                .withRequestBody(equalToJson(loadResourceAsString("mocks/tesla/auth/get-access-token-valid-request.json")))
                .withHeader(HttpHeaders.CONTENT_TYPE, matching(MediaType.APPLICATION_JSON_VALUE)));
    }

    private GetAccessTokenRequest getTestAccessTokenRequest() {
        return loadResourceAsObject("mocks/tesla/auth/get-access-token-valid-request.json", GetAccessTokenRequest.class);
    }

    @Test
    void testLogoutRequest() {
        stubFor(post("/oauth/revoke")
            .withHeader(HttpHeaders.CONTENT_TYPE, equalTo(MediaType.APPLICATION_JSON_VALUE))
            .willReturn(WireMock.aResponse()
                    .withStatus(HttpStatus.OK.value())
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        RevokeAccessTokenRequest request = getTestRevokeAccessTokenRequest();
        authClient.logout(request);

        verify(postRequestedFor(urlMatching("/oauth/revoke"))
            .withRequestBody(equalToJson(loadResourceAsString("mocks/tesla/auth/revoke-access-token-request.json")))
            .withHeader(HttpHeaders.CONTENT_TYPE, matching(MediaType.APPLICATION_JSON_VALUE)));
    }

    private RevokeAccessTokenRequest getTestRevokeAccessTokenRequest() {
        return loadResourceAsObject("mocks/tesla/auth/revoke-access-token-request.json", RevokeAccessTokenRequest.class);
    }

}