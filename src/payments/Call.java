/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import rates.Tarrif;

/**
 *
 * @author Venkat Sai Jarugula
 */
public class Call {

    private String phoneNumber;
    private String startTime;
    private String endTime;
    private Tarrif callType;

    /**
     * Constructor with all the arguments
     *
     * @param phoneNumber PhoneNumber
     * @param startTime StartTime
     * @param endTime EndTime
     * @param callType CallType
     */
    public Call(String phoneNumber, String startTime, String endTime, Tarrif callType) {
        this.phoneNumber = phoneNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.callType = callType;
    }

    /**
     * Gets gives the phone number
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Get gives the seconds
     *
     * @return the seconds
     */
    public double getSeconds() {
        double seconds = 0.0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startdate = LocalDateTime.parse(startTime, dtf);
        LocalDateTime enddate = LocalDateTime.parse(endTime, dtf);
        seconds = Duration.between(startdate, enddate).getSeconds();
        return seconds;

    }

    /**
     * Get gives the Call type
     *
     * @return the Call type
     */
    public Tarrif getCallType() {
        return callType;
    }

    /**
     * represents the toString
     *
     * @return phone Number,callType,startTime,endTime
     */
    @Override
    public String toString() {
        return String.format("%-15s %-15s %-22s %-21s %n", phoneNumber, callType, startTime, endTime);
        // return String.format("%-16s",phoneNumber)+String.format("%-23s",callType)+String.format("%-23s",startTime)+endTime+"\n"; 

    }

}
