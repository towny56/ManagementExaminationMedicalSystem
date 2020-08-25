package com.fixit.areas.ward.models.view;

public class WardNamesViewModel {

    private String wardName;

    public WardNamesViewModel() {
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    @Override
    public String toString() {
        return this.wardName;
    }
}
