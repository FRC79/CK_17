/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.util;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author sebastian
 */
public class CANJagQuadEncoder implements PIDSource, LiveWindowSendable{
    
    public static class ControlMode{
        public final int value;
        static final int kSpeed_val = 0;
        static final int kPosition_val = 1;
        public static final ControlMode kSpeed = new ControlMode(kSpeed_val);
        public static final ControlMode kPosition = new ControlMode(kPosition_val);
        
        private ControlMode(int value){
            this.value = value;
        }
    }
    
    private CANJaguar jaguar;
    private ControlMode controlMode;
    private int ticsPerRev;
    
    public CANJagQuadEncoder(CANJaguar referenceJag, 
            ControlMode controlType, int ticsPerRev){
        jaguar = referenceJag;
        controlMode = controlType;
        this.ticsPerRev = ticsPerRev;
        
        initEncoder();
    }
    
    private void initEncoder(){
        try {
            jaguar.configEncoderCodesPerRev(ticsPerRev); // Config encoder tics per rev
            
            // Set speed or position reference
            switch(controlMode.value){
                case ControlMode.kPosition_val:
                    jaguar.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
                    break;
                case ControlMode.kSpeed_val:
                    jaguar.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
                    break;
                default:
                    break;
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    private double getSpeed(){
        try {
            return jaguar.getSpeed();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }
    
    private double getPosition(){
        try {
            return jaguar.getPosition();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }
    
    public double pidGet() {
        switch(controlMode.value){
            case ControlMode.kPosition_val: 
                return getPosition();
            case ControlMode.kSpeed_val: 
                return getSpeed();
            default:
                return 0.0;
        }
    }

    public String getSmartDashboardType() {
        return "CANJagQuadEncoder";
    }
    
    private ITable table;
    
    public void initTable(ITable subtable) {
        table = subtable;
        
        // Output what control mode is being used
        if(table != null){
            switch(controlMode.value){
                case ControlMode.kPosition_val:
                    table.putString("Control", "Position");
                    break;
                case ControlMode.kSpeed_val:
                    table.putString("Control", "Speed");
                    break;
                default:
                    table.putString("Control", "None");
                    break;
            }
        }
        
        updateTable();
    }
    
    public void updateTable() {
        if(table != null){
            switch(controlMode.value){
                case ControlMode.kPosition_val:
                    table.putString("Control", "Position");
                    table.putNumber("Value", getPosition());
                    break;
                case ControlMode.kSpeed_val:
                    table.putString("Control", "Speed");
                    table.putNumber("Value", getSpeed());
                    break;
                default:
                    table.putNumber("Value", 0.0);
                    break;
            }
        }
    }
    
    public ITable getTable() {
        return table;
    }

    public void startLiveWindowMode() {
    }

    public void stopLiveWindowMode() {
    }
}
