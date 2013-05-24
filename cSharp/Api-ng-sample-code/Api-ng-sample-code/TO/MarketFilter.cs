using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class MarketFilter
    {
        [JsonProperty(PropertyName = "textQuery")]
        public string TextQuery { get; set; }

        [JsonProperty(PropertyName = "exchangeIds")]
        public ISet<string> ExchangeIds { get; set; }

        [JsonProperty(PropertyName = "eventTypeIds")]
        public ISet<string> EventTypeIds { get; set; }

        [JsonProperty(PropertyName = "eventIds")]
        public ISet<string> EventIds { get; set; }

        [JsonProperty(PropertyName = "competitionIds")]
        public ISet<string> CompetitionIds { get; set; }

        [JsonProperty(PropertyName = "marketIds")]
        public ISet<string> MarketIds { get; set; }

        [JsonProperty(PropertyName = "venues")]
        public ISet<string> Venues { get; set; }

        [JsonProperty(PropertyName = "bspOnly")]
        public bool? BspOnly { get; set; }

        [JsonProperty(PropertyName = "turnInPlayEnabled")]
        public bool? TurnInPlayEnabled { get; set; }

        [JsonProperty(PropertyName = "inPlayOnly")]
        public bool? InPlayOnly { get; set; }

        [JsonProperty(PropertyName = "marketBettingTypes")]
        public ISet<MarketBettingType> MarketBettingTypes { get; set; }

        [JsonProperty(PropertyName = "marketCountries")]
        public ISet<string> MarketCountries { get; set; }

        [JsonProperty(PropertyName = "marketTypeCodes")]
        public ISet<string> MarketTypeCodes { get; set; }

        [JsonProperty(PropertyName = "marketStartTime")]
        public TimeRange MarketStartTime { get; set; }

        [JsonProperty(PropertyName = "withOrders")]
        public ISet<OrderStatus> WithOrders { get; set; }


         
    }
}
