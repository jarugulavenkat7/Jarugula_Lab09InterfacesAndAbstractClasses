/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rates;

/**
 *
 * @author Venkat Sai Jarugula
 */
public enum PostpaidDiscounts {
    /**
     * enum constant with name YEAR1
     */
    YEAR1(4),
    /**
     * enum constant with name YEAR2
     */
    YEAR2(8),
    /**
     * enum constant with name YEAR3
     */
    YEAR3(10),
    /**
     * enum constant with name YEAR4
     */
    YEAR4(15),
    /**
     * enum constant with name YEAR5
     */
    YEAR5(20);
    private double discount;

    /**
     * Constructor with one argument
     *
     * @param discount discount
     */
    private PostpaidDiscounts(double discount) {
        this.discount = discount;
    }

    /**
     * Get gives the discount
     *
     * @return discount
     */
    public double getDiscount() {
        return discount;
    }

}
