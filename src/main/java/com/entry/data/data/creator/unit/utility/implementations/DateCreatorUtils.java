package com.entry.data.data.creator.unit.utility.implementations;

import com.entry.data.data.creator.unit.constants.Constants;
import com.entry.data.data.creator.unit.enums.Months;
import com.entry.data.data.creator.unit.model.FieldDetails;
import com.entry.data.data.creator.unit.utility.interfaces.IDateCreatorUtils;

public class DateCreatorUtils implements IDateCreatorUtils {

    public String getFormattedDate(String inputDate, FieldDetails fldDtls) {
        String inputDateFormat = fldDtls.getInputDateFormat().trim();
        String requiredFormat  = fldDtls.getDesiredDateFormat().trim();
        String formattedDate = null;
        switch(requiredFormat.toLowerCase()){
            case Constants.DATE_FORMAT_DATE:
                formattedDate = getDateElement(inputDate,inputDateFormat.toUpperCase());
                break;
            case Constants.DATE_FORMAT_DATETIME:
                formattedDate = getFormattedDateForDateTime(inputDate,inputDateFormat.toUpperCase());
                break;
            case Constants.DATE_FORMAT_TIMESTAMP:
                formattedDate = getFormattedDateForTimeStamp(inputDate,inputDateFormat.toUpperCase());
                break;
        }
        return formattedDate;
    }

    private String getFormattedDateForTimeStamp(String inputDate, String inputDateFormat) {
        return getDateElement(inputDate,inputDateFormat) + Constants.SPACE_CHAR_STRING + getTimeElement(inputDate,inputDateFormat);
    }

    private String getFormattedDateForDateTime(String inputDate, String inputDateFormat) {
        return getDateElement(inputDate,inputDateFormat) + Constants.SPACE_CHAR_STRING + getFullTimeElement(inputDate,inputDateFormat);
    }

    private String getDateElement(String inputDate, String inputDateFormat) {
        String month = null;
        String year = null;
        String day = null;

        int index = 0;

        if(inputDateFormat.contains(Constants.THREE_CHAR_MONTH_STRING)){
            index = inputDateFormat.indexOf(Constants.THREE_CHAR_MONTH_STRING);
            month = inputDate.substring(index, index+3);
            month = Months.valueOf(month).getValue();
        }else if (inputDateFormat.contains(Constants.TWO_CHAR_MONTH_STRING)){
            index = inputDateFormat.indexOf(Constants.TWO_CHAR_MONTH_STRING);
            month = inputDate.substring(index, index+2);
        }

        if(inputDateFormat.contains(Constants.FOUR_CHAR_YEAR_STRING)){
            index = inputDateFormat.indexOf(Constants.FOUR_CHAR_YEAR_STRING);
            year = inputDate.substring(index, index+4);
        }else if(inputDateFormat.contains(Constants.TWO_CHAR_YEAR_STRING)){
            index = inputDateFormat.indexOf(Constants.TWO_CHAR_YEAR_STRING);
            year = "20" + inputDate.substring(index, index+2);
        }

        if(inputDateFormat.contains(Constants.DAY_STRING)){
            index = inputDateFormat.indexOf(Constants.DAY_STRING);
            day = inputDate.substring(index,index+2);
        }
        return year + Constants.HYPHEN_STRING + month + Constants.HYPHEN_STRING + day;
    }

    private String getTimeElement(String inputDate, String inputDateFormat) {
        String time = null;
        int index = 0;

        if(inputDateFormat.contains(Constants.TIME_TILL_SECONDS_STRING)){
            index = inputDateFormat.indexOf(Constants.TIME_TILL_SECONDS_STRING);
            time = inputDate.substring(index,index+8);
        }
        return time;
    }

    private String getFullTimeElement(String inputDate, String inputDateFormat) {
        String time = null;
        int index = 0;

        if(inputDateFormat.contains(Constants.TIME_TILL_SECONDS_STRING)){
            index = inputDateFormat.indexOf(Constants.TIME_TILL_SECONDS_STRING);
            time = inputDate.substring(index,inputDate.length()-1);
        }
        return time;
    }

}
