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
public interface Operations {

    /**
     * Method to check if can make a call
     *
     * @return if can make a call of Boolean type
     */
    public boolean canMakeCall();

    /**
     * Method to make a call
     *
     * @param call call
     * @return call
     */
    public boolean makeCall(Call call);

    /**
     * Method usage of data
     *
     * @param dataUsed data used
     * @return data used
     */
    public boolean useData(double dataUsed);

}
