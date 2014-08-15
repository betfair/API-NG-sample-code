using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class ClearedOrderSummary
    {
        [JsonProperty(PropertyName = "eventTypeId")]
        public string EventTypeId { get; set; }

        [JsonProperty(PropertyName = "eventId")]
        public string EventId { get; set; }

        [JsonProperty(PropertyName = "marketId")]
        public string MarketId { get; set; }

        [JsonProperty(PropertyName = "selectionId")]
        public long SelectionId { get; set; }

        [JsonProperty(PropertyName = "handicap")]
        public double handicap { get; set; }

        [JsonProperty(PropertyName = "betId")]
        public string BetId { get; set; }

        [JsonProperty(PropertyName = "placedDate")]
        public DateTime PlacedDate { get; set; }

        [JsonProperty(PropertyName = "persistenceType")]
        public PersistenceType PersistenceType { get; set; }
        
        [JsonProperty(PropertyName = "orderType")]
        public OrderType OrderType { get; set; }

        [JsonProperty(PropertyName = "side")]
        public Side Side { get; set; }

        [JsonProperty(PropertyName = "itemDescription")]
        public ItemDescription ItemDescription { get; set; }

        [JsonProperty(PropertyName = "priceRequested")]
        public double PriceRequested { get; set; }

        [JsonProperty(PropertyName = "settledDate")]
        public DateTime SettledDate { get; set; }

        [JsonProperty(PropertyName = "betCount")]
        public int BetCount { get; set; }

        [JsonProperty(PropertyName = "commission")]
        public double Commission { get; set; }

        [JsonProperty(PropertyName = "priceMatched")]
        public double PriceMatched { get; set; }

        [JsonProperty(PropertyName = "priceReduced")]
        public bool PriceReduced { get; set; }

        [JsonProperty(PropertyName = "sizeSettled")]
        public double SizeSettled { get; set; }

        [JsonProperty(PropertyName = "profit")]
        public double Profit { get; set; }

        [JsonProperty(PropertyName = "sizeCancelled")]
        public double SizeCancelled { get; set; }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.AppendFormat("{0}", "ClearedOrderSummary")
                        .AppendFormat(" : EventTypeId={0}", EventTypeId)
                        .AppendFormat(" : EventId={0}", EventId)
                        .AppendFormat(" : MarketId={0}", MarketId)
                        .AppendFormat(" : SelectionId={0}", SelectionId)
                        .AppendFormat(" : handicap={0}", handicap)
                        .AppendFormat(" : BetId={0}", BetId)
                        .AppendFormat(" : PlacedDate={0}", PlacedDate)
                        .AppendFormat(" : PersistenceType={0}", PersistenceType)
                        .AppendFormat(" : OrderType={0}", OrderType)
                        .AppendFormat(" : Side={0}", Side)
                        .AppendFormat(" : ItemDescription={0}", ItemDescription)
                        .AppendFormat(" : PriceRequested={0}", PriceRequested)
                        .AppendFormat(" : SettledDate={0}", SettledDate)
                        .AppendFormat(" : BetCount={0}", BetCount)
                        .AppendFormat(" : Commission={0}", Commission)
                        .AppendFormat(" : PriceMatched={0}", PriceMatched)
                        .AppendFormat(" : SizeSettled={0}", SizeSettled)
                        .AppendFormat(" : Profit={0}", Profit)
                        .AppendFormat(" : SizeCancelled={0}", SizeCancelled);

            return sb.ToString();
        }
    }
}
