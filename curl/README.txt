
##### Description:

Scripts contains example of placing a bet for next available horse race via curl requests. 
Scripts will attempt to place a bet with invalid odds set, so user will get error response 
on last request. This Script will NOT place any bet!

There are two scripts, one that supports json rpc and other supports rescript protocol.


##### Pre-run installation:

Before running script make sure you have got "yajl" package installed. This package is used
to pretty formatting of json response. If you run it on Debian distros run first:

	sudo apt-get install yajl-tools

on REDHAT distro:
	sudo yum install yajl


##### How to invoke:

You can start a script with two arguments : app key and session token (example below)
	sh json_curl.bash <your app key> <your session token>
	sh json_rescript.bash <your app key> <your session token>

If you don't specify either of them you will be prompted for it once you start a script.


##### Modification: 

Scrip contains some variables regarding connection such as host and port which is set 
by default to Public API-NG.
