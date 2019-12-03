package net.electric.tesla.client.http;

import feign.RequestLine;
import net.electric.tesla.client.http.model.ItemList;
import net.electric.tesla.client.http.model.Product;

public interface ApiClient {

    @RequestLine("GET " + TeslaEndpoints.PRODUCTS)
    ItemList<Product> getProductList();

}
