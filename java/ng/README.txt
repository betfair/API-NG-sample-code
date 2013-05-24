
##### Description:

This is a maven project containing Java sample code to connect to the Betfair API NG application.

It requires:
  -Apache Maven 3 (3.0.3)
  -Java 7

##### To build:

You need Apache Maven in order to build the application. Execute:
   mvn clean install


##### How to invoke:

You can again use Apache Maven to run the application passing the app key, session token and the method (json-rpc or rescript:
    mvn exec:java -Dexec.mainClass="com.betfair.aping.ApiNGDemo" -Dexec.args="<YOUR APP KEY> <YOUR SESSION TOKEN> <METHOD>"

    <YOUR APP KEY>: a valid app key
    <YOUR SESSION TOKEN>: a valid Betfair session token
    <METHOD>: json-rpc or rescript

     example: mvn exec:java -Dexec.mainClass="com.betfair.aping.ApiNGDemo" -Dexec.args="myAppKey mySessionToken json-rpc"