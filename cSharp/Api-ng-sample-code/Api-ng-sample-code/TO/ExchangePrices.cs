using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class ExchangePrices
    {
        [JsonProperty(PropertyName = "availableToBack")]
        public List<PriceSize> AvailableToBack { get; set; }

        [JsonProperty(PropertyName = "availableToLay")]
        public List<PriceSize> AvailableToLay { get; set; }

        [JsonProperty(PropertyName = "tradedVolume")]
        public List<PriceSize> TradedVolume { get; set; }

        public override string ToString()
        {
            var sb = new StringBuilder().AppendFormat("{0}", "ExchangePrices");

            if (AvailableToBack != null && AvailableToBack.Count > 0)
            {
                int idx = 0;
                foreach (var availableToBack in AvailableToBack)
                {
                    sb.AppendFormat(" : AvailableToBack[{0}]={1}", idx++, availableToBack);
                }
            }

            if (AvailableToLay != null && AvailableToLay.Count > 0)
            {
                int idx = 0;
                foreach (var availableToLay in AvailableToLay)
                {
                    sb.AppendFormat(" : AvailableToLay[{0}]={1}", idx++, availableToLay);
                }
            }

            if (TradedVolume != null && TradedVolume.Count > 0)
            {
                int idx = 0;
                foreach (var tradedVolume in TradedVolume)
                {
                    sb.AppendFormat(" : TradedVolume[{0}]={1}", idx++, tradedVolume);
                }
            }

            return sb.ToString();
        }
    }
}
