/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.YEARS;

/**
 *
 * @author Venkat Sai Jarugula
 */
public class ConnectionAccount {

    private String customerName;
    private String phoneNumber;
    private String connectionType;
    private String joiningDate;

    /**
     * Three argument constructor initializes the instance variables.
     *
     * @param customerName Full name of the customer.
     * @param phoneNumber Phone number of the customer including country code.
     * @param connectionType An attribute that stores type of connection.
     */
    public ConnectionAccount(String customerName, String phoneNumber, String connectionType) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.connectionType = connectionType;
    }

    /**
     * Four argument constructor initializes the instance variables.
     *
     * @param customerName Full name of the customer.
     * @param phoneNumber Phone number of the customer including country code.
     * @param connectionType An attribute that stores type of connection.
     * @param joiningDate Connection opening date of the customer in the format
     * mm/dd/yyyy.
     */
    public ConnectionAccount(String customerName, String phoneNumber, String connectionType, String joiningDate) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.connectionType = connectionType;
        this.joiningDate = joiningDate;
    }

    /**
     * Get gives the Name of the customer
     *
     * @return Name of the customer
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Get gives the phone number of the customer
     *
     * @return phone number of the customer
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Get gives the connection type
     *
     * @return connection type
     */
    public String getConnectionType() {
        return connectionType;
    }

    /**
     * Get gives the date of joining
     *
     * @return the date of joining
     */
    public String getJoiningDate() {
        return joiningDate;
    }

    /**
     * Get gives the number of years
     *
     * @return the number of years
     */
    public int numberOfYears() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate startDate = LocalDate.parse(joiningDate, formatter);
        LocalDate afterDate = LocalDate.now();
        int numberOfYears = (int) YEARS.between(startDate, afterDate);
        return numberOfYears;
    }
}
