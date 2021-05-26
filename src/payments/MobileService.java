/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import accounts.ConnectionAccount;

/**
 *
 * @author Venkat Sai Jarugula
 */
public abstract class MobileService implements Operations {

    private ConnectionAccount account;
    double balance;
    double dataAvailable;

    /**
     * Constructor with three arguments
     *
     * @param account Account
     * @param balance Balance
     * @param dataAvailable Data available
     */
    public MobileService(ConnectionAccount account, double balance, double dataAvailable) {
        this.account = account;
        this.balance = balance;
        this.dataAvailable = dataAvailable;
    }

    /**
     * Get gives the account
     *
     * @return the account
     */
    public ConnectionAccount getAccount() {
        return account;
    }

    /**
     * Get gives the balance
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Get gives the available data
     *
     * @return the available data
     */
    public double getDataAvailable() {
        return dataAvailable;
    }

    /**
     * Abstract method to check if can make a call
     *
     * @return If the can make call
     */
    @Override
    public boolean canMakeCall() {
        return true;

    }

    /**
     * Abstract method to make a call
     *
     * @param call call
     * @return make a call in Boolean
     */
    @Override
    public boolean makeCall(Call call) {
        return true;

    }

    /**
     * represents the toString
     *
     * @return Customer Name,phone Number, Connection Type
     */
    @Override
    public String toString() {
        //return "Customer Name  :"+account.getCustomerName()+"\nPhone Number  :"+account.getPhoneNumber()+"\nConnection Type  :"+account.getConnectionType();

        return "----------------------------------------------------------------"
                + "-------------\n" + String.format("%-18s", "Customer Name") + ":"
                + account.getCustomerName()
                + "\n" + String.format("%-18s", "Phone Number") + ":" + "("
                + account.getPhoneNumber().substring(0, 2)
                + ")" + account.getPhoneNumber().substring(2, 5) + "-"
                + account.getPhoneNumber().substring(5, 8) + "-"
                + account.getPhoneNumber().substring(8) + "\n"
                + String.format("%-18s", "Connection Type")
                + ":" + account.getConnectionType();

    }

    /**
     * Get gives the data usage
     *
     * @param dataUsed data used
     * @return if the data id used
     */
    public boolean useData(double dataUsed) {
        boolean res = false;
        if (dataAvailable > 0) {
            dataAvailable = dataAvailable - (dataUsed / 1024);
            res = true;
        }
        return res;
    }

}
