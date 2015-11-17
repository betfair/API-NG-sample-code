using System;
using System.Linq;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{

    public class MarketTypeResult
    {
        [JsonProperty(PropertyName = "marketType")]
        public string marketType { get; set; }

        [JsonProperty(PropertyName = "marketCount")]
        public int marketCount { get; set; }
    }
}
