package com.betfair.api.ng.json;

public class Constants {
	//TODO may want to put some of these things in a properties file of some sort.

	/** HTTP Request Headers **/
	public static final String ENCODING_UTF_8 = "UTF-8";
	public static final String HTTP_HEADER_ACCEPT = "Accept";
	public static final String HTTP_HEADER_ACCEPT_CHARSET = "Accept-Charset";
	public static final String HTTP_HEADER_X_APPLICATION = "X-Application";
	public static final String APP_KEY = "PRBQYVVI6GIgqQjv";
	public static final String HTTP_HEADER_X_AUTHENTICATION = "X-Authentication";
	public static final String HTTP_HEADER_X_ADMINISTRATOR = "X-Administrator";
	public static final String HTTP_HEADER_X_PROXY_AUTHENTICATION = "X-Proxy-Authentication";
	public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
	public static final String APPLICATION_JSON = "application/json";
	public static final int TIMEOUT = 10000;
	public static String SSO_TOKEN = null; 
	
	/** API Service Name **/
	public static final String SPORTS_APING_V1_0 = "SportsAPING/v1.0/";
	public static final String URL = "https://api.betfair.com/exchange/betting/json-rpc/v1";
	public static final String REDIRECT_URL = "https://www.betfair.com";
	public static final String LOG_IN_URL = "https://identitysso.betfair.com/view/login?product=" + APP_KEY + "&url=" + REDIRECT_URL;
	public static final String LOG_OUT_URL = "https://identitysso.betfair.com/api/logout";

}
