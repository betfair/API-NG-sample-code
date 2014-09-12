using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Security.Cryptography;
using System.Security.Cryptography.X509Certificates;

using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.IO;
using System.Net.Http.Headers;
using System.Security;

using Betfair_Non_interactive_login.Models;

namespace Betfair_Non_interactive_login
{
    class Program
    {
        static string getPassword()
        {
            string pass = "";
            ConsoleKeyInfo key;

            do
            {
                key = Console.ReadKey(true);
                if (key.Key != ConsoleKey.Backspace && key.Key != ConsoleKey.Enter)
                {
                    pass += key.KeyChar;
                    Console.Write("*");
                }
                else
                {
                    if (key.Key == ConsoleKey.Backspace && pass.Length > 0)
                    {
                        pass = pass.Substring(0, (pass.Length - 1));
                        Console.Write("\b \b");
                    }
                }
            }
            while (key.Key != ConsoleKey.Enter);

            return pass;
        }

        static void Main(string[] args)
        {
            if (args.Count() != 3)
            {
                Console.WriteLine("This sample takes 3 arguments: <username> <appkey> <pathtocertfile>");
                Console.WriteLine("You will then be prompted for the password for this account.");
            }
            else
            {
                var username = args.First();
                var appKey = args.ElementAt(1);
                var certFilename = args.ElementAt(2);
                Console.WriteLine("Please enter your password:");
                var password = Program.getPassword();
                Console.WriteLine("");

                var client = new AuthClient(appKey);
                try
                {
                    var resp = client.doLogin(username, password, certFilename);
                    Console.WriteLine("Response Type: " + resp.LoginStatus);
                    if (resp.LoginStatus == "SUCCESS")
                    {
                        Console.WriteLine("Obtained the session token: " + resp.SessionToken);
                    }
                }
                catch (CryptographicException e)
                {
                    Console.WriteLine("Could not load the certificate: " + e.Message);
                }
                catch (HttpRequestException e)
                {
                    Console.WriteLine("The Betfair Login endpoint returned an HTTP Error: " + e.Message);
                }
                catch (WebException e)
                {
                    Console.WriteLine("An error occurred whilst attempting to make the request: " + e.Message);
                }
                catch (Exception e)
                {
                    Console.WriteLine("An Error Occurred: " + e.Message);
                }
            }
        }
    }
}
