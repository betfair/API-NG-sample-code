using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class ReplaceInstruction
    {
        [JsonProperty(PropertyName = "betId")]
        public string BetId { get; set; }

        [JsonProperty(PropertyName = "newPrice")]
        public double NewPrice { get; set;}

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.AppendFormat("{0}", "ReplaceInstruction")
                        .AppendFormat(" : BetId={0}", BetId)
                        .AppendFormat(" : NewPrice={0}", NewPrice);

            return sb.ToString();
        }
    }
}
