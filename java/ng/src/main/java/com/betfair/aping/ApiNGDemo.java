package com.betfair.aping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * This is a demonstration class to show a quick demo of the new Betfair API-NG.
 * When you execute the class will: <li>find a market (next horse race in the
 * UK)</li> <li>get prices and runners on this market</li> <li>place a bet on 1
 * runner</li> <li>handle the error</li>
 *
 */
public class ApiNGDemo {

    private static Properties prop = new Properties();
    private static Boolean jsonRpcRequest;
    private static String applicationKey;
    private static String sessionToken;
    private static String jsonOrRescript;
    private static boolean debug;

    static {
        try {
            InputStream in = ApiNGDemo.class.getResourceAsStream("/apingdemo.properties");
            prop.load(in);
            in.close();

            debug = new Boolean(prop.getProperty("DEBUG"));

        } catch (IOException e) {
            System.out.println("Error loading the properties file: "+e.toString());
        }
    }

    public static void main(String[] args) {

        System.out.println("Welcome to the Betfair API NG!");

        BufferedReader inputStreamReader = null;
        //getting the AppKey and the session token

        if(args.length >= 3){
            applicationKey = args[0];
            sessionToken = args[1];
            jsonOrRescript = args[2];

            if(jsonOrRescript.equalsIgnoreCase("json-rpc")){
                jsonRpcRequest=true;
            } else if(jsonOrRescript.equalsIgnoreCase("rescript")){
                jsonRpcRequest=false;
            }

        } else {
            while(applicationKey == null || applicationKey.isEmpty()){

                System.out.println("Please insert a valid App Key: ");
                System.out.print("> ");
                inputStreamReader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    applicationKey = inputStreamReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            while(sessionToken== null || sessionToken.isEmpty()){
                System.out.println("Please insert a valid Session Token: ");
                System.out.print("> ");
                inputStreamReader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    sessionToken = inputStreamReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Ask the user what protocol want to use for the test
        while (jsonRpcRequest == null) {
            System.out.println("Please choose the protocol to run the test: ");
            System.out.println("1 json-rpc");
            System.out.println("2 Rescript");
            System.out.print("> ");

            inputStreamReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                jsonOrRescript = inputStreamReader.readLine();
                Integer input = new Integer(jsonOrRescript);

                if (input == 1)
                    jsonRpcRequest = true;
                else if (input == 2)
                    jsonRpcRequest = false;
                else
                    jsonRpcRequest = null;

            } catch (IOException e) {
                jsonRpcRequest = null;
            } catch (NumberFormatException e) {
                jsonRpcRequest = null;
            }
        }

        if(jsonRpcRequest) {
            ApiNGJsonRpcDemo jsonRpcDemo = new ApiNGJsonRpcDemo();
            jsonRpcDemo.start(applicationKey, sessionToken);
        } else {
            ApiNGJRescriptDemo rescriptDemo = new ApiNGJRescriptDemo();
            rescriptDemo.start(applicationKey, sessionToken);
        }
    }

    public static Properties getProp() {
        return prop;
    }

    public static boolean isDebug(){
        return debug;
    }
}
