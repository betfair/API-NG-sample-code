using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class Order
    {
        [JsonProperty(PropertyName = "betId")]
        public string BetId { get; set; }

        [JsonProperty(PropertyName = "orderType")]
        public OrderType OrderType { get; set; }

        [JsonProperty(PropertyName = "status")]
        public OrderStatus Status { get; set; }

        [JsonProperty(PropertyName = "persistenceType")]
        public PersistenceType PersistenceType { get; set; }

        [JsonProperty(PropertyName = "side")]
        public Side Side { get; set; }

        [JsonProperty(PropertyName = "price")]
        public double Price { get; set; }

        [JsonProperty(PropertyName = "size")]
        public double Size { get; set; }

        [JsonProperty(PropertyName = "bspLiability")]
        public double? BspLiability { get; set; }

        [JsonProperty(PropertyName = "placedDate")]
        public DateTime? PlacedDate { get; set; }

        [JsonProperty(PropertyName = "avgPriceMatched")]
        public double? AvgPriceMatched { get; set; }

        [JsonProperty(PropertyName = "sizeMatched")]
        public double? SizeMatched { get; set; }

        [JsonProperty(PropertyName = "sizeRemaining")]
        public double? SizeRemaining { get; set; }

        [JsonProperty(PropertyName = "sizeLapsed")]
        public double? SizeLapsed { get; set; }

        [JsonProperty(PropertyName = "sizeCancelled")]
        public double? SizeCancelled { get; set; }

        [JsonProperty(PropertyName = "sizeVoided")]
        public double? SizeVoided { get; set; }

        public override string ToString()
        {
            return new StringBuilder().AppendFormat("BetId={0}", BetId)

                        .AppendFormat(" : OrderType={0}", OrderType)
                        .AppendFormat(" : OrderStatus={0}", Status)
                        .AppendFormat(" : PersistenceType={0}", PersistenceType)
                        .AppendFormat(" : Side={0}", Side)
                        .AppendFormat(" : Size@Price={0}@{1}", SizeRemaining, Price)	// instead of simply Size
                        .AppendFormat(" : BspLiability={0}", BspLiability)

                        .AppendFormat(" : PlacedDate={0}", PlacedDate)
                        .AppendFormat(" : AvgPriceMatched={0}", AvgPriceMatched)

                        .AppendFormat(" : SizeMatched={0}", SizeMatched)
                        .AppendFormat(" : SizeRemaining={0}", SizeRemaining)
                        .AppendFormat(" : SizeLapsed={0}", SizeLapsed)
                        .AppendFormat(" : SizeCancelled={0}", SizeCancelled)
                        .AppendFormat(" : SizeVoided={0}", SizeVoided)

                        .ToString();
        }
    }
}
