using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace Api_ng_sample_code.TO
{
    public class Exception
    {
        // exception in json-rpc format
        [JsonProperty(PropertyName = "data")]
        public JObject Data { get; set; }		// actual exception details


        // exception in rescript format
        [JsonProperty(PropertyName = "detail")]
        public JObject Detail { get; set; }		// actual exception details

    }
}
