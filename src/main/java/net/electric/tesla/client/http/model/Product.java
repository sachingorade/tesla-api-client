package net.electric.tesla.client.http.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Product {

    private Long id;

    @JsonProperty("vehicle_id")
    private Long vehicleId;
    private String vin;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("option_codes")
    private String optionCodes;
    private String color;
    private List<String> tokens;
    private String state;

    @JsonProperty("in_service")
    private String inService;

    @JsonProperty("id_s")
    private String ids;

    @JsonProperty("calendar_enabled")
    private boolean calendarEnabled;

    @JsonProperty("backseat_token")
    private String backseatToken;

    @JsonProperty("backseat_token_updated_at")
    private String backseatTokenUpdatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getOptionCodes() {
        return optionCodes;
    }

    public void setOptionCodes(String optionCodes) {
        this.optionCodes = optionCodes;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInService() {
        return inService;
    }

    public void setInService(String inService) {
        this.inService = inService;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public boolean isCalendarEnabled() {
        return calendarEnabled;
    }

    public void setCalendarEnabled(boolean calendarEnabled) {
        this.calendarEnabled = calendarEnabled;
    }

    public String getBackseatToken() {
        return backseatToken;
    }

    public void setBackseatToken(String backseatToken) {
        this.backseatToken = backseatToken;
    }

    public String getBackseatTokenUpdatedAt() {
        return backseatTokenUpdatedAt;
    }

    public void setBackseatTokenUpdatedAt(String backseatTokenUpdatedAt) {
        this.backseatTokenUpdatedAt = backseatTokenUpdatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", vin='" + vin + '\'' +
                ", displayName='" + displayName + '\'' +
                ", optionCodes='" + optionCodes + '\'' +
                ", color='" + color + '\'' +
                ", tokens=" + tokens +
                ", state='" + state + '\'' +
                ", inService='" + inService + '\'' +
                ", ids='" + ids + '\'' +
                ", calendarEnabled=" + calendarEnabled +
                ", backseatToken='" + backseatToken + '\'' +
                ", backseatTokenUpdatedAt='" + backseatTokenUpdatedAt + '\'' +
                '}';
    }
}
