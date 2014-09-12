# Betfair API-NG C# Non-interactive login example
This project provides example usage of requests to Betfair's [Non-interactive Login](https://api.developer.betfair.com/services/webapps/docs/display/1smk3cen4v3lu3yomq5qye0ni/Non-Interactive+%28bot%29+login). You should read the guide and create yourself a key prior to using this sample.

## Running instructions
You need to convert your key to a format that the .NET Platform understands, a [PKCS12 File](http://en.wikipedia.org/wiki/PKCS_12).
```shell
openssl pkcs12 -export -in certificate.crt -inkey privateKey.key -out certificate.pfx
```
When asked to provide a password, leave the passphrase blank or you will need to update the sample file where the X509Certificate2 is instantiated in order to provide the passphrase.

Once you have made sure that your key is uploaded to the Betfair My Account -> My Security page following the instructions in the introduction above, you can invoke the sample as follows. 

* Open up Visual Studio or Visual Studio Express and build the app once by pressing F7.
* Open up the windows command line (cmd.exe) in the right place by holding Shift and Right clicking on the Debug directory within the "bin" folder in this project and select "Open Command Window here".
* Run the tool by executing :
``shell 
Betfair-Non-Interactive-login.exe myUsername myAppKey "C:\Path to my\certificate.p12"
```
* You will be asked to enter your password.
* You will then be provided a session token.

This sample takes 3 arguments: Betfair-Non-Interactive-login.exe {username} {appkey} {pathtocertfile}