package com.betfair.aping.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RescriptResponseHandler implements ResponseHandler<String> {
	private static final String ENCODING_UTF_8 = "UTF-8";
	
    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() != 200) {

            String s = entity == null ? null : EntityUtils.toString(entity, ENCODING_UTF_8);
            System.out.println("Call to api-ng failed\n");
            System.out.println(s);
            System.exit(0);

        }

        return entity == null ? null : EntityUtils.toString(entity,ENCODING_UTF_8);
    }
}
