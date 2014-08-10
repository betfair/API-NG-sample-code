using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class ClearedOrderSummaryReport
    {
        [JsonProperty(PropertyName = "clearedOrders")]
        public List<ClearedOrderSummary> ClearedOrders { get; set; }

        [JsonProperty(PropertyName = "moreAvailable")]
        public bool MoreAvailable { get; set; }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.AppendFormat("{0}", "ClearedOrderSummaryReport");

            if (ClearedOrders != null && ClearedOrders.Count > 0)
            {
                int idx = 0;
                foreach (var clearedOrder in ClearedOrders)
                {
                    sb.AppendFormat(" : ClearedOrder[{0}]={1}", idx++, clearedOrder);
                }
            }

            sb.AppendFormat(" : MoreAvailable={0}", MoreAvailable);

            return sb.ToString();
        }
    }
}
