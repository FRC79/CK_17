/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.util;

import com.sun.squawk.util.MathUtils;

/**
 *
 * @author sebastian
 */
public class TeleopHelper {
    
    public static double mapJoystickToPowerOutput(double input)
    {
//        if(Math.abs(input) < 0.05)
//	{
//		// Stop if joystick is near zero
//		return 0.0;
//	}
//	else
//	{
//            double mapping;
//
//            if(Math.abs(input) <= 0.75)
//            {
//                    mapping = 0.95 * ((0.5 * MathUtils.pow(Math.abs(input), 2.0)) + 0.2);
//                    mapping = (input >= 0) ? mapping : -mapping; // Change to negative if the input was negative
//                    return mapping;
//            }
//            else
//            {
//                    mapping = 2.16 * Math.abs(input) - 1.16;
//                    mapping = (input >= 0) ? mapping : -mapping; // Change to negative if the input was negative
//                    return mapping;
//            }
//	}
        return input;
    }
}
