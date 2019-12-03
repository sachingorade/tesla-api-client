package net.electric.tesla.client.http;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import net.electric.tesla.client.http.request.GetAccessTokenRequest;
import net.electric.tesla.client.http.request.RevokeAccessTokenRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static net.electric.tesla.client.TestUtils.loadResourceAsObject;
import static net.electric.tesla.client.TestUtils.loadResourceAsString;

public class AuthClientTest {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON_VALUE = "application/json";
    private static final int STATUS_OK = 200;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

    private AuthClient authClient;

    @Before
    public void setupServer() {
        authClient = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(AuthClient.class, "http://localhost:" + wireMockRule.port());
    }

    @Test
    public void testAuthenticateGetAccessTokenRequest() {
        stubFor(post(urlEqualTo("/oauth/token"))
            .withHeader(CONTENT_TYPE, equalTo(APPLICATION_JSON_VALUE))
            .willReturn(WireMock.aResponse()
                            .withStatus(STATUS_OK)
                            .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                            .withBody(loadResourceAsString("mocks/tesla/auth/get-access-token-response-ok.json"))));

        GetAccessTokenRequest authRequest = getTestAccessTokenRequest();
        authClient.authenticate(authRequest);

        verify(postRequestedFor(urlMatching("/oauth/token"))
                .withRequestBody(equalToJson(loadResourceAsString("mocks/tesla/auth/get-access-token-valid-request.json")))
                .withHeader(CONTENT_TYPE, matching(APPLICATION_JSON_VALUE)));
    }

    private GetAccessTokenRequest getTestAccessTokenRequest() {
        return loadResourceAsObject("mocks/tesla/auth/get-access-token-valid-request.json", GetAccessTokenRequest.class);
    }

    @Test
    public void testLogoutRequest() {
        stubFor(post(urlEqualTo("/oauth/revoke"))
            .withHeader(CONTENT_TYPE, equalTo(APPLICATION_JSON_VALUE))
            .willReturn(WireMock.aResponse()
                    .withStatus(STATUS_OK)
                    .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)));

        RevokeAccessTokenRequest request = getTestRevokeAccessTokenRequest();
        authClient.logout(request);

        verify(postRequestedFor(urlMatching("/oauth/revoke"))
            .withRequestBody(equalToJson(loadResourceAsString("mocks/tesla/auth/revoke-access-token-request.json")))
            .withHeader(CONTENT_TYPE, matching(APPLICATION_JSON_VALUE)));
    }

    private RevokeAccessTokenRequest getTestRevokeAccessTokenRequest() {
        return loadResourceAsObject("mocks/tesla/auth/revoke-access-token-request.json", RevokeAccessTokenRequest.class);
    }

}