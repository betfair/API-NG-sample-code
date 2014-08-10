using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class ItemDescription
    {
        [JsonProperty(PropertyName = "eventTypeDesc")]
        public string EventTypeDesc { get; set; }

        [JsonProperty(PropertyName = "eventDesc")]
        public string EventDesc { get; set; }

        [JsonProperty(PropertyName = "marketDesc")]
        public string MarketDesc { get; set; }

        [JsonProperty(PropertyName = "marketStartTime")]
        public DateTime MarketStartTime { get; set; }

        [JsonProperty(PropertyName = "runnerDesc")]
        public string RunnerDesc { get; set; }

        [JsonProperty(PropertyName = "numberOfWinners")]
        public int NumberOfWinners { get; set; }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.AppendFormat("{0}", "ItemDescription")
                        .AppendFormat(" : EventTypeDesc={0}", EventTypeDesc)
                        .AppendFormat(" : EventDesc={0}", EventDesc)
                        .AppendFormat(" : MarketDesc={0}", MarketDesc)
                        .AppendFormat(" : MarketStartTime={0}", MarketStartTime)
                        .AppendFormat(" : RunnerDesc={0}", RunnerDesc)
                        .AppendFormat(" : NumberOfWinners={0}", NumberOfWinners);

            return sb.ToString();
        }
    }
}
