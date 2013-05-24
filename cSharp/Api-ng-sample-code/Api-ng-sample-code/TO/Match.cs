using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class Match
    {
        [JsonProperty(PropertyName = "betId")]
        public string BetId { get; set; }

        [JsonProperty(PropertyName = "side")]
        public Side Side { get; set; }

        [JsonProperty(PropertyName = "price")]
        public double Price { get; set; }

        [JsonProperty(PropertyName = "size")]
        public double Size { get; set; }

        [JsonProperty(PropertyName = "matchDate")]
        public DateTime MatchDate { get; set; }

        public override string ToString()
        {
            return new StringBuilder()
                        .AppendFormat(" : BetId={0}", BetId)
                        .AppendFormat(" : Side={0}", Side)
                        .AppendFormat(" : Size@Price={0}@{1}", Size, Price)
                        .AppendFormat(" : MatchDate={0}", MatchDate)
                        .ToString();
        }
    }
}
