using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class MarketCatalogue
    {
        [JsonProperty(PropertyName = "marketId")]
        public string MarketId { get; set; }

        [JsonProperty(PropertyName = "marketName")]
        public string MarketName { get; set; }

        [JsonProperty(PropertyName = "isMarketDataDelayed")]
        public bool IsMarketDataDelayed { get; set; }

        [JsonProperty(PropertyName = "description")]
        public MarketDescription Description { get; set; }

        [JsonProperty(PropertyName = "runners")]
        public List<RunnerDescription> Runners { get; set; }

        [JsonProperty(PropertyName = "eventType")]
        public EventType EventType { get; set; }

        [JsonProperty(PropertyName = "event")]
        public Event Event { get; set; }

        [JsonProperty(PropertyName = "competition")]
        public Competition Competition { get; set; }

        public override string ToString()
        {
            // well, don't bother displaying event/event type/competition
            var sb = new StringBuilder().AppendFormat("{0}", "MarketCatalogue")
                        .AppendFormat(" : Market={0}[{1}]", MarketId, MarketName)
                        .AppendFormat(" : IsMarketDataDelayed={0}", IsMarketDataDelayed);

            if (Description != null)
            {
                sb.AppendFormat(" : {0}", Description);
            }

            if (Runners != null && Runners.Count > 0)
            {
                int idx = 0;
                foreach (var runner in Runners)
                {
                    sb.AppendFormat(" : Runner[{0}]={1}", idx++, runner);
                }
            }

            return sb.ToString();
        }
    }
}
