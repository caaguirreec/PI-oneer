
package pioneer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author César Aguirre and Santiago Molina
 */
public class RestClientService 
{
    public static String  strURL= "http://192.168.3.11:3000"; 
       
    public String serviceAction = ""; 
    public int serviceParam1 = -1;   
	
    /**
    *
    * Simple RESTFUL client java implementation in order to listen the commands from the webpage in the net. 
    */
    
    public RestClientService()
    {
        
    }
    public void checkServiceStatus() 
    {
        String output;
        try 
        {
            URL url = new URL(strURL+"/api"); //
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");
            
            if (conn.getResponseCode() != 200) 
            {
                //return output;	
                throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
            }
            else
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));   
                
                //Parse actions
                while ((output = br.readLine()) != null) 
                {   
                    output = output.replace("\"","");
                    
                    //Parse actions with parameters
                    if(output.contains("_"))
                    {
                        String[] parts = output.split("_");
                        serviceAction = parts[0];
                        if(parts[1] != null)
                        {
                           serviceParam1 = Integer.parseInt(parts[1]);
                        }
                        else
                        {
                            serviceParam1 = 0;
                        }
                    }
                    else
                    {
                        serviceAction = output;
                    }
                }
                conn.disconnect();
            }
            System.out.println("Output from Server: "+serviceAction);
        } 
        catch (MalformedURLException e) 
        {
        } 
        catch (IOException e) 
        {
        }
    }       
}

