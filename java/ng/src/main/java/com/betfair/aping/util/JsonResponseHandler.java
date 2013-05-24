package com.betfair.aping.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class JsonResponseHandler implements ResponseHandler<String> {
	private static final String ENCODING_UTF_8 = "UTF-8";
	
    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() >= 300) {
            throw new HttpResponseException(statusLine.getStatusCode(),
                    statusLine.getReasonPhrase());
        }

        HttpEntity entity = response.getEntity();
        return entity == null ? null : EntityUtils.toString(entity,ENCODING_UTF_8);

    }
}
