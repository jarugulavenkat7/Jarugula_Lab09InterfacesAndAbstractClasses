/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import accounts.ConnectionAccount;
import rates.Tarrif;

/**
 *
 * @author Venkat Sai Jarugula
 */
public class PrepaidService extends MobileService implements Billing, Operations {

    private Call call;

    /**
     * Constructor with three arguments
     *
     * @param account account
     * @param balance balance
     * @param data data
     */
    public PrepaidService(ConnectionAccount account, double balance, double data) {
        super(account, balance, data);
    }

    /**
     * Calculates the bill
     *
     * @return bill
     */
    @Override
    public double calcBill() {
        double bill = 0.0;
        if (call.getCallType().equals(Tarrif.INTERNATIONAL)) {
            bill = (Tarrif.INTERNATIONAL.getPrepaid() / 100) * (call.getSeconds() / 60);
        } else if (call.getCallType().equals(Tarrif.LOCAL)) {
            bill = (Tarrif.LOCAL.getPrepaid() / 100) * (call.getSeconds() / 60);
        } else {
            bill = (Tarrif.ROAMING.getPrepaid() / 100) * (call.getSeconds() / 60);
        }
        return bill;
    }

    /**
     * Checks if can make call
     *
     * @return if can make call of Boolean type
     */
    @Override
    public boolean canMakeCall() {
        boolean result = false;
        if (getBalance() > 0) {
            result = true;
        }
        return result;
    }

    /**
     * Checks to make a call
     *
     * @param call call
     * @return if can make a call of boolean type
     */
    @Override
    public boolean makeCall(Call call) {
        boolean makecallresult = false;
        if (canMakeCall()) {
            this.call = call;
            balance = getBalance() - calcBill();
            makecallresult = true;
        }
        return makecallresult;

    }

    /**
     * Checks the discount for returning customer
     *
     * @return discount
     */
    @Override
    public double discountForReturningCustomer() {
        return 0.0;
    }

    /**
     * toString
     *
     * @return toString
     */
    @Override
    public String toString() {
        return super.toString() + "\n--------------------------------------------"
                + "---------------------------------" + "\nLast Call Details:\n"
                + String.format("%-15s %-15s %-22s %-21s %n", "Phone number", "Call Type", "From", "To")
                + call.toString() + "-----------------------------------------------"
                + "------------------------------\n	"
                + "Available mobile data 	:"
                + Math.round(Math.abs(getDataAvailable()) * 100) / 100.0 + "MB\n	"
                + "Balance			:$"
                + Math.round(getBalance() * 100) / 100.0 + "\n	Last Call Amount  	"
                + ":$" + String.format("%.2f", Math.round(calcBill() * 100.0) / 100.0)
                + "\n-----------------------------------------------------------------------------";

    }

}
