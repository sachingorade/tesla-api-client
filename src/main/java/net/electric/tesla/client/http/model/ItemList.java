package net.electric.tesla.client.http.model;

import java.util.List;

public class ItemList<T> {

    private List<T> response;
    private int count;

    public List<T> getResponse() {
        return response;
    }

    public void setResponse(List<T> response) {
        this.response = response;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ItemList{" +
                "response=" + response +
                ", count=" + count +
                '}';
    }
}
