package gepro.mobile.proxy;

import java.io.InputStream;

import org.json.JSONObject;

public interface IHttpWrapper {
	
	public String sendGet(String url) throws Exception;	
	public String sendPost(String url, String data)  throws Exception;	
	public String sendPut(String url, String data)  throws Exception;	
	public String sendDelete(String url, String data)  throws Exception;
	
	public String sendJSONPost(String url, JSONObject json)  throws Exception;	
	public String sendJSONPut(String url, JSONObject json)  throws Exception;	
	public String sendJSONDelete(String url, JSONObject json)  throws Exception;
	
	public InputStream getStream(String url) throws Exception ;

}
