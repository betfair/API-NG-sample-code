using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class PlaceInstruction
    {
        [JsonProperty(PropertyName = "orderType")]
        public OrderType OrderType { get; set; }

        [JsonProperty(PropertyName = "selectionId")]
        public long SelectionId { get; set; }

        [JsonProperty(PropertyName = "handicap")]
        public double? Handicap { get; set; }

        [JsonProperty(PropertyName = "side")]
        public Side Side { get; set; }

        [JsonProperty(PropertyName = "limitOrder")]
        public LimitOrder LimitOrder { get; set; }

        [JsonProperty(PropertyName = "limitOnCloseOrder")]
        public LimitOnCloseOrder LimitOnCloseOrder { get; set; }

        [JsonProperty(PropertyName = "marketOnCloseOrder")]
        public MarketOnCloseOrder MarketOnCloseOrder { get; set; }

        public override string ToString()
        {
            var sb = new StringBuilder()
                .AppendFormat("OrderType={0}", OrderType)
                .AppendFormat(" : SelectionId={0}", SelectionId)
                .AppendFormat(" : Handicap={0}", Handicap)
                .AppendFormat(" : Side={0}", Side);

            switch (OrderType)
            {
                case OrderType.LIMIT:
                    sb.AppendFormat(" : LimitOrder={0}", LimitOrder);
                    break;
                case OrderType.LIMIT_ON_CLOSE:
                    sb.AppendFormat(" : LimitOnCloseOrder={0}", LimitOnCloseOrder);
                    break;
                case OrderType.MARKET_ON_CLOSE:
                    sb.AppendFormat(" : MarketOnCloseOrder={0}", MarketOnCloseOrder);
                    break;
            }

            return sb.ToString();
        }
    }
}
