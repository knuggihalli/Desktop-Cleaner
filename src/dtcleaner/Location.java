/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtcleaner;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author I849002
 */
public class Location extends TimerTask{

    @Override
    public void run() {
        Properties prop = new Properties();
	InputStream input = null;

	try {

		input = new FileInputStream("src/dtcleaner/loc.properties");

		// load a properties file
		prop.load(input);
                
            

		// get the property value and print it out
		Enumeration<?> e = prop.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = prop.getProperty(key);
			JButton i = new JButton(key);
		}

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    }
    

}
