package gepro.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

public class HttpRequest implements gepro.mobile.proxy.IHttpWrapper {

	DefaultHttpClient httpClient;
	HttpContext localContext;
	private String ret;

	HttpResponse response = null;
	HttpPost httpPost = null;
	HttpGet httpGet = null;
	HttpPut httpPut = null;

	public HttpRequest() {
		this(120000);
	}

	public HttpRequest(int timeout) {
		HttpParams myParams = new BasicHttpParams();

		HttpConnectionParams.setConnectionTimeout(myParams, timeout);
		HttpConnectionParams.setSoTimeout(myParams, timeout);
		httpClient = new DefaultHttpClient(myParams);
		localContext = new BasicHttpContext();
	}

	public void clearCookies() {
		httpClient.getCookieStore().clear();
	}

	public void abort() {
		try {
			if (httpClient != null) {
				System.out.println("Abort.");
				httpPost.abort();
			}
		} catch (Exception e) {
			System.out.println("GeproMobileService" + e);
		}
	}

	public String sendPost(String url, String data) throws Exception {
		return sendPost(url, data, null);
	}

	public String sendJSONPost(String url, JSONObject data) throws Exception {
		return sendPost(url, data.toString(), "application/json");
	}

	public String sendPost(String url, String data, String contentType)
			throws Exception {

		ret = null;

		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.RFC_2109);

		httpPost = new HttpPost(url);
		response = null;

		StringEntity tmp = null;

		Log.d("GeproMobile", "Setting httpPost headers");

		httpPost.setHeader("User-Agent", "GeproMobile");
		httpPost
				.setHeader(
						"Accept",
						"text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");

		if (contentType != null) {
			httpPost.setHeader("Content-Type", contentType);
		} else {
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded");
		}

		try {
			tmp = new StringEntity(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e("GeproMobile", "HttpUtils : UnsupportedEncodingException : "
					+ e);
		}

		httpPost.setEntity(tmp);

		Log.d("GeproMobile", url + "?" + data);

		response = httpClient.execute(httpPost, localContext);

		int status = response.getStatusLine().getStatusCode();
		if (status != 200) {
			ret = EntityUtils.toString(response.getEntity());
			throw new Exception(ret);
		}

		if (response != null) {
			ret = EntityUtils.toString(response.getEntity());
		}

		Log.d("GeproMobile", "Returning value:" + ret);

		return ret;
	}

	public String sendGet(String url) throws Exception {
		httpGet = new HttpGet(url);

		try {
			response = httpClient.execute(httpGet);
		} catch (Exception e) {
			Log.e("GeproMobile", e.getMessage());
		}

		int status = response.getStatusLine().getStatusCode();
		if (status != 200) {
			ret = EntityUtils.toString(response.getEntity());
			throw new Exception(ret);
		}

		// we assume that the response body contains the error message
		try {
			ret = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			Log.e("GeproMobile", e.getMessage());
		}

		return ret;
	}

	public InputStream getStream(String urlString) throws IOException {
		InputStream in = null;
		int response = -1;

		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();

			response = httpConn.getResponseCode();

			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception e) {
			throw new IOException("Error connecting");
		} // end try-catch

		return in;
	}

	@Override
	public String sendDelete(String url, String data) throws Exception {
		return null;
	}

	@Override
	public String sendJSONDelete(String url, JSONObject json) throws Exception {
		return null;
	}

	@Override
	public String sendJSONPut(String url, JSONObject json) throws Exception {
		return sendPut(url, json.toString(), "application/json");
	}

	@Override
	public String sendPut(String url, String data) throws Exception {
		return sendPut(url, data, null);
	}
	

	public String sendPut(String url, String data, String contentType)
			throws Exception {

		ret = null;

		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.RFC_2109);

		httpPut = new HttpPut(url);
		response = null;

		StringEntity tmp = null;

		Log.d("GeproMobile", "Setting httpPost headers");

		httpPut.setHeader("User-Agent", "GeproMobile");
		httpPut
				.setHeader(
						"Accept",
						"text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");

		if (contentType != null) {
			httpPut.setHeader("Content-Type", contentType);
		} else {
			httpPut.setHeader("Content-Type",
					"application/x-www-form-urlencoded");
		}

		try {
			tmp = new StringEntity(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e("GeproMobile", "HttpUtils : UnsupportedEncodingException : "
					+ e);
		}

		httpPut.setEntity(tmp);

		Log.d("GeproMobile", url + "?" + data);

		response = httpClient.execute(httpPut, localContext);

		int status = response.getStatusLine().getStatusCode();
		if (status != 200) {
			ret = EntityUtils.toString(response.getEntity());
			throw new Exception(ret);
		}

		if (response != null) {
			ret = EntityUtils.toString(response.getEntity());
		}

		Log.d("GeproMobile", "Returning value:" + ret);

		return ret;
	}
}