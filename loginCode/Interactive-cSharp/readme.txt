How to run the sample code
===========================
Open the solution in Visual Studio
Run the application.Enter your Application Key in the text box at the bottom of the screen.Click the 'Show Logon Page' button.
A login screen will appear, enter your user ID and password and hit Login.
Upon successful login the SSOID is retrived from the cookie and displayed.
After 15 minutes (and every subsequent 15 mins) a keep alive message is sent to maintain the session.
Upon hitting logout a logout request is sent to the server.
