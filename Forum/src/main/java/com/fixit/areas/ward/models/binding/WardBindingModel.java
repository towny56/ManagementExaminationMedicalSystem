package com.fixit.areas.ward.models.binding;

import com.fixit.constants.Constants;

import javax.validation.constraints.Size;

public class WardBindingModel {

    @Size(min = 1, max = 50, message = Constants.WARD_NAME_LENGTH)
    private String wardName;

    @Size(min = 1, max = 30, message = Constants.ROOM_NUMBER_LENGTH)
    private String roomNumber;

    public WardBindingModel() {
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
