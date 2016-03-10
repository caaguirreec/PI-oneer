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
       public static String  strURL= "http://www.vegatracer.com:2000"; //Esta linea se debe habilitar para funcionamiento en oficina
   // public static String  strURL= "http://192.168.1.2:2000"; //Esta linea se debe habilitar para funcionamiento en planta de producción
   
	
	public static String vegatracertime() {
            String output = "";
            String output1 = "";
	  try {
 
		URL url = new URL(strURL+"/RESTSERVICE/GetUTCDateTime"); //
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
 
		
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
		for(int n = 0; n <9; n++) 
                { char c = output.charAt(65+n);  
                 
                        output1=output1+c;
                }
                   int thirtyyears=946681200+3600;
                   System.out.println(output);
                   int time= Integer.parseInt(output1);
                   Calendar date = Calendar.getInstance();
                   date.setTimeInMillis(time*1000L+thirtyyears*1000L);
                   System.out.println(date.getTime());
                   return output1;
               
                        
		}
 
		conn.disconnect();}
                return output;
 
	  } catch (MalformedURLException e) {
 
                return output;
 
	  } catch (IOException e) {
	  }
            return output;
        
	}
        public static String engineStatus() {
          String output = "";
            String output1 = "";
	  try {
 
		URL url = new URL(strURL+"/RESTSERVICE/GetUTCDateTime"); //
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
 
		
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
		for(int n = 0; n <9; n++) 
                { char c = output.charAt(65+n);  
                 
                        output1=output1+c;
                }
                   int thirtyyears=946681200+3600;
                   System.out.println(output);
                   int time= Integer.parseInt(output1);
                   Calendar date = Calendar.getInstance();
                   date.setTimeInMillis(time*1000L+thirtyyears*1000L);
                   System.out.println(date.getTime());
                   return output1;
               
                        
		}
 
		conn.disconnect();}
                return output;
 
	  } catch (MalformedURLException e) {
 
                return output;
 
	  } catch (IOException e) {
	  }
            return output;
            
            
        }
 
        public static String AddCaptureToSever(int id, int idGatewayDevice,String gatewayDeviceHwVersion,String gatewayDeviceFwVersion,String idWorker, int captureStartDate,int captureDurationSecs, int idCaptureEquipment, String captureEquipmentHwVersion, String captureEquipmentFwVersion, int captureEquipmentTypeId, int testTypeId, int captureSentDate, String idProduct, int resultId, String args) throws MalformedURLException {
             String finalServerResponse="";    
          //  URL url = new URL("http://192.168.1.2:2000/RESTSERVICE/AddCapture/"+id+"/"+idGatewayDevice+"/"+gatewayDeviceHwVersion+"/"+gatewayDeviceFwVersion+"/"+idWorker+"/"+captureStartDate+"/"+captureDurationSecs+"/"+idCaptureEquipment+"/"+captureEquipmentHwVersion+"/"+captureEquipmentFwVersion+"/"+captureEquipmentTypeId+"/"+testTypeId+"/"+captureSentDate+"/"+idProduct+"/"+resultId+"/"+args);
	    URL url = new URL(strURL+"/RESTSERVICE/AddCapture/"+id+"/"+idGatewayDevice+"/"+gatewayDeviceHwVersion+"/"+gatewayDeviceFwVersion+"/"+idWorker+"/"+captureStartDate+"/"+captureDurationSecs+"/"+idCaptureEquipment+"/"+captureEquipmentHwVersion+"/"+captureEquipmentFwVersion+"/"+captureEquipmentTypeId+"/"+testTypeId+"/"+captureSentDate+"/"+idProduct+"/"+resultId+"/"+args);
	
                HttpURLConnection conn;
            Logger logger = Logger.getLogger("GatewayLog"); 
            logger.info(url.toString()+"Log");  
            try {
                conn = (HttpURLConnection) url.openConnection();
           
                conn.setRequestMethod("POST");
                conn.setRequestProperty("User-Agent", USER_AGENT);
                conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		conn.setRequestProperty("Accept", "application/xml");
               
                String urlParameters = "id="+id+"&idGatewayDevice="+idGatewayDevice+"&gatewayDeviceHwVersion="+gatewayDeviceHwVersion+"&gatewayDeviceFwVersion="+gatewayDeviceFwVersion+"&idWorker="+idWorker+"&captureStartDate="+captureStartDate+"&captureDurationSecs="+captureDurationSecs+"&idCaptureEquipment="+idCaptureEquipment+"&captureEquipmentHwVersion="+captureEquipmentHwVersion+"&captureEquipmentFwVersion="+captureEquipmentFwVersion+"&captureEquipmentTypeId="+captureEquipmentTypeId+"&testTypeId="+testTypeId+"&captureSentDate="+captureSentDate+"&idProduct="+idProduct+"&resultId="+resultId+"&args="+args;
                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
                String Serveresponse=response.toString();
               
                for (int x=0;x<Serveresponse.length();x++)
                { 
                if(Serveresponse.charAt(x)=='>' && (Serveresponse.charAt(x+2)=='<'|| Serveresponse.charAt(x+3)=='<'))
                {
                finalServerResponse=Character.toString(Serveresponse.charAt(x+1));
                break;
                }
                }
		//print result
		System.out.println(finalServerResponse);
             } catch (IOException ex) {
               // Logger.getLogger(ClientWS.class.getName()).log(Level.SEVERE, null, ex);
            }
 
            
          return finalServerResponse;
        }
}
