package ru.lanit.ws.pgu.requests.duplic.info;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by user06 on 18.09.2014.
 */
public class RepresentativeInfo {

    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;

    @Size(max = 50)
    private String secondName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
