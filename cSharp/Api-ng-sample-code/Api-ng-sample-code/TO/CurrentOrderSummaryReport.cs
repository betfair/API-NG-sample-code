using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class CurrentOrderSummaryReport
    {
        [JsonProperty(PropertyName = "currentOrders")]
        public List<CurrentOrderSummary> CurrentOrders { get; set; }

        [JsonProperty(PropertyName = "moreAvailable")]
        public bool MoreAvailable { get; set; }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.AppendFormat("{0}", "CurrentOrderSummaryReport");

            if (CurrentOrders != null && CurrentOrders.Count > 0)
            {
                int idx = 0;
                foreach (var currentOrder in CurrentOrders)
                {
                    sb.AppendFormat(" : CurrentOrder[{0}]={1}", idx++, currentOrder);
                }
            }

            sb.AppendFormat(" : MoreAvailable={0}", MoreAvailable);

            return sb.ToString();
        }		
    }
}
