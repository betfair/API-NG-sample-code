using System;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;

namespace Betfair_Non_interactive_login.Models
{
    [DataContract]
    public class LoginResponse
    {
        [DataMember(Name = "sessionToken")]
        public string SessionToken { get; set; }
        [DataMember(Name = "loginStatus")]
        public string LoginStatus { get; set; }
    }
}
