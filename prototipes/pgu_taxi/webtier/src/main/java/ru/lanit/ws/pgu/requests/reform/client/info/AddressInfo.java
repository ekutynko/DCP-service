package ru.lanit.ws.pgu.requests.reform.client.info;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Данные об адресе объекта
 *
 * @author Roman Orlov
 */
public class AddressInfo {

    /**
     * Почтовый индекс
     */
    @Pattern(regexp = "^$|\\d{6}|null")
    private String postIndex;

    /**
     * Субъект РФ
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String region;

    /**
     * Район
     */
    @Size(max = 100)
    private String district;

    /**
     * Населенный пункт
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String city;

    /**
     * Улица
     */
    @Size(max = 50)
    private String street;

    /**
     * Дом
     */
    @Size(max = 20)
    private String house;

    /**
     * Корпус
     */
    @Size(max = 20)
    private String construction;

    /**
     * Строение
     */
    @Size(max = 20)
    private String building;

    /**
     * Квартира/офис/помещение/комната
     */
    @Size(max = 20)
    private String flat;

    /**
     * Дополнительное описание
     */
    private String description;


    public String getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(String postIndex) {
        this.postIndex = postIndex;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
