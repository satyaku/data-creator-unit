package com.entry.data.data.creator.unit.enums;

import lombok.Getter;

@Getter
public enum Months {

    JAN("01"),FEB("02"),MAR("03"),APR("04"),MAY("05"),JUN("06"),
    JUL("07"),AUG("08"),SEP("09"),OCT("10"),NOV("11"),DEC("12");

    public String value;
    Months(String value) {
        this.value = value;
    }
}
