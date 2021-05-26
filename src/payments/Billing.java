/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

/**
 *
 * @author Venkat Sai Jarugula
 */
public interface Billing {

    /**
     * Calculates the bill based on service
     *
     * @return the bill based on service
     */
    public double calcBill();

    /**
     * Calculates the discount of returning customer
     *
     * @return the discount of returning customer
     */
    public double discountForReturningCustomer();

}
