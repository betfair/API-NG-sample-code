using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Security.Cryptography;
using System.Security.Cryptography.X509Certificates;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.IO;

using Betfair_Non_interactive_login.Models;

namespace Betfair_Non_interactive_login
{
    class AuthClient
    {

        private string appKey;

        public string AppKey
        {
            get { return appKey; }
        }

        private WebRequestHandler getWebRequestHandlerWithCert (string certFilename)
        {
            var cert = new X509Certificate2(certFilename, "");
            var clientHandler = new WebRequestHandler();
            clientHandler.ClientCertificates.Add(cert);
            return clientHandler;
        }

        private const string DEFAULT_COM_BASEURL = "https://identitysso-cert.betfair.com";

        private HttpClient initHttpClientInstance (WebRequestHandler clientHandler, string appKey, string baseUrl = DEFAULT_COM_BASEURL)
        {
            var client = new HttpClient(clientHandler);
            client.BaseAddress = new Uri(baseUrl);
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Add("X-Application", appKey);
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            return client;
        }

        private FormUrlEncodedContent getLoginBodyAsContent (string username, string password)
        {
            var postData = new List<KeyValuePair<string, string>>();
            postData.Add(new KeyValuePair<string, string>("username", username));
            postData.Add(new KeyValuePair<string, string>("password", password));
            return new FormUrlEncodedContent(postData);
        }

        public LoginResponse doLogin (string username, string password, string certFilename)
        {
            var handler = getWebRequestHandlerWithCert(certFilename);
            var client = initHttpClientInstance(handler, appKey);
            var content = getLoginBodyAsContent(username, password);
            var result = client.PostAsync("/api/certlogin", content).Result;
            result.EnsureSuccessStatusCode();
            var jsonSerialiser = new DataContractJsonSerializer(typeof(LoginResponse));
            var stream = new MemoryStream(result.Content.ReadAsByteArrayAsync().Result);
            return (LoginResponse)jsonSerialiser.ReadObject(stream);
      
        }

        public AuthClient (string appKey)
        {
            this.appKey = appKey;
        }
    }
}
