package com.betfair.api.ng.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;
import android.util.Log;

import com.betfair.api.ng.json.Constants;
import com.betfair.api.ng.json.JsonResponseHandler;
import com.betfair.api.ng.json.JsonrpcRequest;
import com.betfair.api.ng.objects.Container;
import com.google.gson.JsonSyntaxException;

import static com.betfair.api.ng.json.Constants.*;

public class Requester implements Parameters {

		// request handler for Api-ng calls
	public Container makeRequest(String operation, Map<String, Object> params) {

		JsonrpcRequest request = new JsonrpcRequest();
		request.setId("1");
		request.setMethod(Constants.SPORTS_APING_V1_0 + operation);
		request.setParams(params);

		String requestString = gson.toJson(request);
		Log.i("Json request", requestString);
		String response = null;
		try {
			response = new Call().execute(requestString).get();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
		Container container = null;
		try {
			Log.i("Json response", response);
			container = gson.fromJson(response, Container.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}

		if (container.getError() != null) {
			Log.e("Got Error in response: ", container.getError().getData()
					.getAPINGException().toString());
			// TODO do API error handling here
			return null;
		}

		return container;
	}
	
	private class Call extends AsyncTask<String, String, String> {
		
		@Override
		protected String doInBackground(String... params) {
			String jsonRequest = params[0];
			HttpPost post = new HttpPost(URL);
			String responseString = null;
			try {
				post.setHeader(HTTP_HEADER_CONTENT_TYPE, APPLICATION_JSON);
				post.setHeader(HTTP_HEADER_ACCEPT, APPLICATION_JSON);
				post.setHeader(HTTP_HEADER_ACCEPT_CHARSET, ENCODING_UTF_8);
				post.setHeader(HTTP_HEADER_X_APPLICATION, APP_KEY);
				post.setHeader(HTTP_HEADER_X_AUTHENTICATION, SSO_TOKEN);

				post.setEntity(new StringEntity(jsonRequest, ENCODING_UTF_8));

				HttpClient httpClient = new DefaultHttpClient();
				ResponseHandler<String> handler = new JsonResponseHandler();

				HttpParams httpParams = httpClient.getParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT);
				HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT);

				responseString = httpClient.execute(post, handler);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return responseString;
		}
		
	}


	

}
