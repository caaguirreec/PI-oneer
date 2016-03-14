/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pioneer;

/**
 *
 * @author Cesar
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.logging.Logger;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;

/**
 *
 * @author Cesar
 */
public class RestClientService {
       public static String  strURL= "http://192.168.3.15:3000"; //Esta linea se debe habilitar para funcionamiento en oficina
    
	
	
        public static String engineStatus() {
          String output = "";
            String output1 = "";
	  try {
 
		URL url = new URL(strURL+"/api"); //
                		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/xml");
 
		if (conn.getResponseCode() != 200) {
                    //return output;	
                    throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
                     
		}
                else
                {
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
 
		
		
		while ((output = br.readLine()) != null) {
		for(int n = 0; n <output.length(); n++) 
                { char c = output.charAt(n);  
                 if(c=='"')
                 {}else{
                        output1=output1+c;}
                }
               	}
 
		conn.disconnect();}
                System.out.println("Output from Server: "+output1);
                return output1;
 
	  } catch (MalformedURLException e) {
 
                return output;
 
	  } catch (IOException e) {
	  }
            return output;
            
            
        }
 
        
}
