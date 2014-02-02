/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package krunch17.util;

import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.microedition.io.FileConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import javax.microedition.io.Connector;

/**
 *
 * @author User
 */
public class CSVReader {
    
    Hashtable config;
    
    public CSVReader(String file){
        config = new Hashtable();
        
        if(!file.equals("")){
            readCSV("file:///" + file);
        } else {
            readCSV("file:///config.csv");
        }
    }
    
    private void readCSV(String file) {
        String line = "";
        char parse = ',';
        
        try {
            FileConnection c = (FileConnection) Connector.open(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(c.openInputStream()));
            
            while ((line = br.readLine()) != null) {
                String[] content = split(line, parse);   
                config.put(content[0], content[1]);
		}
            c.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getValue(String input){
        int value = 0;
        String read = (String) config.get(input);
        
        if (read != null) {
            value = Integer.parseInt(read);   
        }
        return value;
    }
    
    private String[] split(String input, char parse){
        int loc = input.indexOf(parse);
        String[] output = new String[2];
        
        output[0] = input.substring(0, loc);
        output[1] = input.substring(loc + 1);

        return output;
    }
}
