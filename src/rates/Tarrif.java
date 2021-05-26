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
public enum Tarrif {
    /**
     * enum constant with name LOCAL
     */
    LOCAL(19, 20),
    /**
     * enum constant with name ROAMING
     */
    ROAMING(29, 30),
    /**
     * enum constant with name INTERNATIONAL
     */
    INTERNATIONAL(69, 70);
    private double postpaid;
    private double prepaid;

    /**
     * Constructor with two arguments
     *
     * @param postpaid postpaid
     * @param prepaid prepaid
     */
    private Tarrif(double postpaid, double prepaid) {
        this.postpaid = postpaid;
        this.prepaid = prepaid;
    }

    /**
     * Get gives postpaid
     *
     * @return postpaid
     */
    public double getPostpaid() {
        return postpaid;
    }

    /**
     * Get gives prepaid
     *
     * @return prepaid
     */
    public double getPrepaid() {
        return prepaid;
    }

}
