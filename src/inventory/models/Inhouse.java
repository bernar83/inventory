/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.models;

/**
 *
 * @author naber
 */
public class Inhouse extends Part{
    private int machineID;
    
    public void setMachineID(int id) {
        this.machineID = id;
    }
    
    public int getMachineID() {
        return this.machineID;
    }
}
