package ru.lanit.ws.pgu.requests.get.info;

import ru.lanit.ws.pgu.requests.DictionaryInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Данные об адресе места деятельности
 *
 * @author Roman Orlov
 */
public class CarInfo {

    @NotNull
    private DictionaryInfo brand;

    @Size(max = 150)
    private String brandText;

    @NotNull
    private DictionaryInfo model;

    @Size(max = 150)
    private String modelText;

    @NotNull
    private DictionaryInfo colour;

    @NotNull
    @Size(min = 1, max = 25)
    private String gosNumber;

    @NotNull
    @Pattern(regexp = "\\d{4}")
    private String releaseYear;

    private DictionaryInfo ecoClass;

    private String vin;

    @NotNull
    private CarDocInfo carDocInfo;


    public String getGosNumber() {
        return gosNumber;
    }

    public void setGosNumber(String gosNumber) {
        this.gosNumber = gosNumber;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public CarDocInfo getCarDocInfo() {
        return carDocInfo;
    }

    public void setCarDocInfo(CarDocInfo carDocInfo) {
        this.carDocInfo = carDocInfo;
    }

    public DictionaryInfo getBrand() {
        return brand;
    }

    public void setBrand(DictionaryInfo brand) {
        this.brand = brand;
    }

    public String getBrandText() {
        return brandText;
    }

    public void setBrandText(String brandText) {
        this.brandText = brandText;
    }

    public DictionaryInfo getModel() {
        return model;
    }

    public void setModel(DictionaryInfo model) {
        this.model = model;
    }

    public String getModelText() {
        return modelText;
    }

    public void setModelText(String modelText) {
        this.modelText = modelText;
    }

    public DictionaryInfo getColour() {
        return colour;
    }

    public void setColour(DictionaryInfo colour) {
        this.colour = colour;
    }

    public DictionaryInfo getEcoClass() {
        return ecoClass;
    }

    public void setEcoClass(DictionaryInfo ecoClass) {
        this.ecoClass = ecoClass;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
