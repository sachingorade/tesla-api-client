package net.electric.tesla.client.http;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import net.electric.tesla.client.TestUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class ApiClientTest {

    private ApiClient apiClient;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

    @Before
    public void setupClient() {
        apiClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(ApiClient.class, wireMockRule.baseUrl());
    }

    @Test
    public void testGetProducts() {
        stubFor(get(urlEqualTo("/api/1/products"))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody(TestUtils.loadResourceAsString("mocks/tesla/api/get-products-ok.json"))));

        apiClient.getProductList();

        verify(getRequestedFor(urlMatching("/api/1/products")));
    }

}
