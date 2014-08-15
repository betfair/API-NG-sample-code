using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class RunnerId
    {
        [JsonProperty(PropertyName = "marketId")]
        public string MarketId { get; set; }

        [JsonProperty(PropertyName = "selectionId")]
        public long SelectionId { get; set; }

        [JsonProperty(PropertyName = "handicap")]
        public double Handicap { get; set; }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.AppendFormat("{0}", "RunnerId")
                        .AppendFormat(" : MarketId={0}", MarketId)
                        .AppendFormat(" : SelectionId={0}", SelectionId)
                        .AppendFormat(" : Handicap={0}", Handicap);

            return sb.ToString();
        }
    }
}
