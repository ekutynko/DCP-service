package ru.lanit.ws.pgu.requests.get;

import ru.lanit.ws.pgu.beans.application.lanit.ws.pgu.get_license.*;
import ru.lanit.ws.pgu.requests.BaseRequestHelper;
import ru.lanit.ws.pgu.requests.get.info.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user06 on 25.08.2014.
 */
public abstract class RequestHelper extends BaseRequestHelper {

    /*
    Класс, заполяющий модель для конкретного вида услуги.
     */

    private RequestBaseType request;

    public RequestHelper(RequestBaseType request) {
        this.request = request;
    }

    public RequestBaseType getRequest() {
        return request;
    }

    public String getAuthority() {
        return request.getAuthority();
    }

    public AddressInfo getAddressInfo() {
        AddressType addressType = request.getRegAddress();
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setPostIndex(getValue(addressType.getPostIndex()));
        addressInfo.setRegion(addressType.getRegion());
        addressInfo.setDistrict(getValue(addressType.getDistrict()));
        addressInfo.setCity(addressType.getCity());
        addressInfo.setStreet(getValue(addressType.getStreet()));
        addressInfo.setHouse(getValue(addressType.getHouse()));
        addressInfo.setConstruction(getValue(addressType.getConstruction()));
        addressInfo.setBuilding(getValue(addressType.getBuilding()));
        addressInfo.setFlat(getValue(addressType.getFlat()));
        addressInfo.setDescription(getValue(addressType.getDescription()));
        return addressInfo;
    }

    public List<CarInfo> getCarsInfo() {
        List<CarInfo> carsInfo = new ArrayList<CarInfo>();
        CarsInfoType carsInfoType = request.getCarsInfo();
        if (carsInfoType != null) {
            List<CarInfoType> carInfoTypes = carsInfoType.getCarInfo();
            for (CarInfoType carInfoType : carInfoTypes) {
                CarInfo carInfo = new CarInfo();
                carInfo.setBrand(toDictInfo(carInfoType.getBrand()));
                carInfo.setBrandText(getValue(carInfoType.getBrandText()));
                carInfo.setModel(toDictInfo(carInfoType.getModel()));
                carInfo.setModelText(getValue(carInfoType.getModelText()));
                carInfo.setColour(toDictInfo(carInfoType.getColour()));
                carInfo.setGosNumber(carInfoType.getGosNumber());
                carInfo.setReleaseYear(carInfoType.getReleaseYear());
                carInfo.setEcoClass(toDictInfo(getValue(carInfoType.getEcoClass())));
                carInfo.setVin(getValue(carInfoType.getVin()));
                carInfo.setCarDocInfo(getCarDocInfo(carInfoType.getCarDocInfo()));
                carsInfo.add(carInfo);
            }
        }
        return carsInfo;
    }

    private CarDocInfo getCarDocInfo(CarDocInfoType carDocInfoType) {
        CarDocInfo carDocInfo = new CarDocInfo();
        carDocInfo.setCarDocType(toDictInfo(carDocInfoType.getCarDocType()));
        carDocInfo.setCarDocSeries(getValue(carDocInfoType.getCarDocSeries()));
        carDocInfo.setCarDocNumber(carDocInfoType.getCarDocNumber());
        carDocInfo.setCarDocDate(XMLCalendarToDate(carDocInfoType.getCarDocDate()));
        carDocInfo.setCarDocDateExp(XMLCalendarToDate(getValue(carDocInfoType.getCarDocDateExp())));
        return carDocInfo;
    }

    public RegistrationInfo getRegistrationInfo() {
        RegistrationInfo registrationInfo = new RegistrationInfo();
        RegistrationInfoType regInfoType = request.getRegistrationInfo();
        registrationInfo.setOgrn(regInfoType.getRegNo());
        registrationInfo.setOgrnSeries(getValue(regInfoType.getRegNoSeries()));
        registrationInfo.setOgrnNo(getValue(regInfoType.getRegNoNumber()));
        registrationInfo.setOgrnDate(XMLCalendarToDate(regInfoType.getRegNoDate()));
        registrationInfo.setOgrnDepartAddress(getValue(regInfoType.getRegDepartAddress()));
        registrationInfo.setOgrnForm(getValue(regInfoType.getRegNoForm()));
        registrationInfo.setOgrnName(getValue(regInfoType.getRegNoName()));
        registrationInfo.setInn(regInfoType.getInn());
        registrationInfo.setInnWho(getValue(regInfoType.getInnWho()));
        registrationInfo.setInnDate(XMLCalendarToDate(getValue(regInfoType.getInnDate())));
        registrationInfo.setInnNo(getValue(regInfoType.getInnNumber()));
        registrationInfo.setInnSeries(getValue(regInfoType.getInnSeries()));
        registrationInfo.setInnForm(getValue(regInfoType.getInnForm()));
        registrationInfo.setInnName(getValue(regInfoType.getInnName()));
        registrationInfo.setFax(getValue(regInfoType.getFax()));
        registrationInfo.setEmail(getValue(regInfoType.getEmail()));
        registrationInfo.setPhone(getValue(regInfoType.getPhone()));
        registrationInfo.setRepresentativeInfo(getRepresentativeInfo(
                getValue(request.getRegistrationInfo().getRepresentativeInfo())));
        return registrationInfo;
    }

    private RepresentativeInfo getRepresentativeInfo(RepresentativeInfoType representativeInfoType) {
        if (representativeInfoType != null) {
            RepresentativeInfo representativeInfo = new RepresentativeInfo();
            representativeInfo.setLastName(representativeInfoType.getRepresentativeLastName());
            representativeInfo.setFirstName(representativeInfoType.getRepresentativeFirstName());
            representativeInfo.setSecondName(getValue(representativeInfoType.getRepresentativeSecondName()));
            return representativeInfo;
        }
        return null;
    }

}
