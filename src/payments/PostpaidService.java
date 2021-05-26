/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import accounts.ConnectionAccount;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import rates.Tarrif;

/**
 *
 * @author Venkat Sai Jarugula
 */
public class PostpaidService extends MobileService implements Operations, Billing {

    private ArrayList<Call> calls;

    /**
     * Constructor with two arguments
     *
     * @param account account
     * @param dataAvailable data available
     */
    public PostpaidService(ConnectionAccount account, double dataAvailable) {
        super(account, 0.0, dataAvailable);
        calls = new ArrayList<Call>();
    }

    /**
     * Calculates the bill
     *
     * @return bill
     */
    @Override
    public double calcBill() {
        double bill = 0.0;
        for (Call call : calls) {
            if (call.getCallType().equals(Tarrif.INTERNATIONAL)) {

                bill = bill + (Tarrif.INTERNATIONAL.getPostpaid() / 100) * (call.getSeconds() / 60);

            } else if (call.getCallType().equals(Tarrif.LOCAL)) {

                bill = bill + (Tarrif.LOCAL.getPostpaid() / 100) * (call.getSeconds() / 60);

            } else {
                bill = bill + (Tarrif.ROAMING.getPostpaid() / 100) * (call.getSeconds() / 60);
            }

        }
        if (getDataAvailable() < 0) {
            bill = bill + ((Math.abs(getDataAvailable())) * 0.05);
        }
        return bill;
    }

    /**
     * Checks if can make a call
     *
     * @return if can make a call of Boolean type
     */
    @Override
    public boolean canMakeCall() {
        return true;
    }

    /**
     * Checks to make a call
     *
     * @param call call
     * @return can make a call of Boolean type
     */
    @Override
    public boolean makeCall(Call call) {
        boolean result = false;
        if (calls.add(call)) {
            result = true;
        }
        return result;
    }

    /**
     * Checks the Data usage
     *
     * @param dataUsed data used
     * @return data used
     */
    @Override
    public boolean useData(double dataUsed) {
        boolean result = false;
        dataAvailable = getDataAvailable() - (dataUsed / 1024);
        result = true;
        return result;
    }

    /**
     * Calculates the discount for returning customer
     *
     * @return the discount
     */
    public double discountForReturningCustomer() {
        double discountpercentage = 0.0;
        String getstartdate = getAccount().getJoiningDate();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate now = LocalDate.now();
        int currentyear = now.getYear();
        LocalDate startdate = LocalDate.parse(getstartdate, dtf);
        int startyear = startdate.getYear();
        int yeardiff = currentyear - startyear;
        if (yeardiff >= 5) {
            discountpercentage = rates.PostpaidDiscounts.YEAR5.getDiscount();
        } else if (yeardiff >= 4) {
            discountpercentage = rates.PostpaidDiscounts.YEAR4.getDiscount();
        } else if (yeardiff >= 3) {
            discountpercentage = rates.PostpaidDiscounts.YEAR3.getDiscount();
        } else if (yeardiff >= 2) {
            discountpercentage = rates.PostpaidDiscounts.YEAR2.getDiscount();
        } else if (yeardiff >= 1) {
            discountpercentage = rates.PostpaidDiscounts.YEAR1.getDiscount();
        }

        return discountpercentage;

    }

    /**
     * Bill after discount
     *
     * @return bill
     */
    public double finalBillAfterDiscount() {
        double discount = (discountForReturningCustomer() / 100) * calcBill();
        return calcBill() - discount;

    }

    /**
     * toString
     *
     * @return toString
     */
    @Override
    public String toString() {
        String finaldetails = "";
        double additionaldata = 0.0;
        if (dataAvailable < 0) {
            additionaldata = Math.abs(dataAvailable);
        }
        DateTimeFormatter orgformat = DateTimeFormatter.ofPattern("M/d/yyyy");
        DateTimeFormatter newformat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        LocalDate orgdate = LocalDate.parse(getAccount().getJoiningDate(), orgformat);
        String newdate = newformat.format(orgdate);

        String data = "-----------------------------------------------------------------------------\n"
                + String.format("%-15s %-15s %-22s %-21s %n", "Phone number", "Call Type", "From", "To");

        for (Call c : calls) {
            finaldetails = finaldetails + c.toString();
        }
        return super.toString() + "			Connection Date :" + newdate + "\n"
                + data + finaldetails + "-------------------------------------"
                + "----------------------------------------\n" + "	"
                + "Additional mobile data used		:" + String.format("%.2f",
                        additionaldata) + "MB\n	Bill Amount  			"
                + "	:$" + String.format("%.2f", Math.round(calcBill() * 100.0)
                        / 100.0) + "\n	Returning Customer Discount" + "("
                + discountForReturningCustomer() + "%)	:$" + String.format("%.2f", Math.round(((discountForReturningCustomer() / 100.0) * calcBill()) * 100)
                        / 100.0) + "\n	Final Bill Amount  			:$"
                + String.format("%.2f", Math.round(finalBillAfterDiscount() * 100.0) / 100.0) + "\n---------"
                + "------------------------------------------------------------"
                + "--------";

    }

}
