import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.Number;

import javax.ws.rs.core.MediaType;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

public class RESTClient {
	public static void main(String[] args) throws ParseException {
		
		        try{
		        	
		        	File f = new File("C:\\Users\\dsr\\Pictures\\remo.jpg");
		            
		            HttpClient client = new HttpClient();

		            PostMethod postMethod = new PostMethod("https://sag-b60stc2.eur.ad.sag:8284/networking/rest/record/Mother/");
		            
		            //Set the session for this request.
		            postMethod.setRequestHeader("Cookie", "JSESSIONID=C12575156719EF38492C7AE64D55FAA8");

		            //The first attribute of the FilePart constructor refers to the image field name 
		            //ie.image_field
		            FilePart filePart = new FilePart("photo", f);
		            
		            //Set the content type of the filePart
		            filePart.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		            //Create the xml part of the request
		            // __xml_data__ is required for the name attribute 
		            // If the content type is json, use __json_data__
		            StringPart sp = new StringPart("__xml_data__",
		              "<platform>"
		              + "<record>"
		              + "<momname>this is remo</momname>"
		              + "<photo>remo.jpg</photo>"
		            + "</record>"
		            + "</platform>");
		            
		            //Set the Content-type of the xml part
		            sp.setContentType(MediaType.APPLICATION_XML);

		            final Part[] parts = { sp, filePart };

		            postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
		            //Set the API media type in http accept header
		              
		            //Send the request; It will immediately return the response in HttpResponse object
		             
		            int executeMethod = client.executeMethod(postMethod);
		            // Read the response body.
		            byte[] responseBody = postMethod.getResponseBody();

		            // Deal with the response.
		            // Use caution: ensure correct character encoding and is not binary data
		            System.out.println(new String(responseBody));

		            System.out.println(executeMethod);
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
		
	}

}
