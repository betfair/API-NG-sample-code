using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class Event
    {
        [JsonProperty(PropertyName = "id")]
        public string Id { get; set; }

        [JsonProperty(PropertyName = "name")]
        public string Name { get; set; }

        [JsonProperty(PropertyName = "countryCode")]
        public string CountryCode { get; set; }

        [JsonProperty(PropertyName = "timezone")]
        public string Timezone { get; set; }

        [JsonProperty(PropertyName = "venue")]
        public string Venue { get; set; }

        [JsonProperty(PropertyName = "openDate")]
        public DateTime? OpenDate { get; set; }

        public override string ToString()
        {
            return new StringBuilder().AppendFormat("{0}", "Event")
                        .AppendFormat(" : Id={0}", Id)
                        .AppendFormat(" : Name={0}", Name)
                        .AppendFormat(" : CountryCode={0}", CountryCode)
                        .AppendFormat(" : Venue={0}", Venue)
                        .AppendFormat(" : Timezone={0}", Timezone)
                        .AppendFormat(" : OpenDate={0}", OpenDate)
                        .ToString();
        }
    }
}
