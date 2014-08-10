using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class CurrentOrderSummary
    {
        [JsonProperty(PropertyName = "betId")]
        public string BetId { get; set; }

        [JsonProperty(PropertyName = "marketId")]
        public string MarketId { get; set; }

        [JsonProperty(PropertyName = "selectionId")]
        public string SelectionId { get; set; }

        [JsonProperty(PropertyName = "handicap")]
        public string Handicap { get; set; }

        [JsonProperty(PropertyName = "priceSize")]
        public PriceSize PriceSize { get; set; }

        [JsonProperty(PropertyName = "bspLiability")]
        public double BspLiability { get; set; }

        [JsonProperty(PropertyName = "side")]
        public Side Side { get; set; }

        [JsonProperty(PropertyName = "status")]
        public OrderStatus Status { get; set; }

        [JsonProperty(PropertyName = "persistenceType")]
        public PersistenceType PersistenceType { get; set; }

        [JsonProperty(PropertyName = "orderType")]
        public OrderType OrderType { get; set; }

        [JsonProperty(PropertyName = "placedDate")]
        public DateTime PlacedDate { get; set; }

        [JsonProperty(PropertyName = "matchedDate")]
        public DateTime MatchedDate { get; set; }

        [JsonProperty(PropertyName = "averagePriceMatched")]
        public double AveragePriceMatched { get; set; }

        [JsonProperty(PropertyName = "sizeMatched")]
        public double SizeMatched { get; set; }

        [JsonProperty(PropertyName = "sizeRemaining")]
        public double SizeRemaining { get; set; }

        [JsonProperty(PropertyName = "sizeLapsed")]
        public double SizeLapsed { get; set; }

        [JsonProperty(PropertyName = "sizeCancelled")]
        public double SizeCancelled { get; set; }

        [JsonProperty(PropertyName = "sizeVoided")]
        public double SizeVoided { get; set; }

        [JsonProperty(PropertyName = "regulatorAuthCode")]
        public string RegulatorAuthCode { get; set; }

        [JsonProperty(PropertyName = "regulatorCode")]
        public string RegulatorCode { get; set; }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.AppendFormat("{0}", "CurrentOrderSummary")
                        .AppendFormat(" : BetId={0}", BetId)
                        .AppendFormat(" : MarketId={0}", MarketId)
                        .AppendFormat(" : SelectionId={0}", SelectionId)
                        .AppendFormat(" : Handicap={0}", Handicap)
                        .AppendFormat(" : PriceSize={0}", PriceSize)
                        .AppendFormat(" : BspLiability={0}", BspLiability)
                        .AppendFormat(" : Side={0}", Side)
                        .AppendFormat(" : Status={0}", Status)
                        .AppendFormat(" : PersistenceType={0}", PersistenceType)
                        .AppendFormat(" : OrderType={0}", OrderType)
                        .AppendFormat(" : PlacedDate={0}", PlacedDate)
                        .AppendFormat(" : MatchedDate={0}", MatchedDate)
                        .AppendFormat(" : AveragePriceMatched={0}", AveragePriceMatched)
                        .AppendFormat(" : SizeMatched={0}", SizeMatched)
                        .AppendFormat(" : SizeRemaining={0}", SizeRemaining)
                        .AppendFormat(" : SizeLapsed={0}", SizeLapsed)
                        .AppendFormat(" : SizeCancelled={0}", SizeCancelled)
                        .AppendFormat(" : SizeVoided={0}", SizeVoided)
                        .AppendFormat(" : RegulatorAuthCode={0}", RegulatorAuthCode)
                        .AppendFormat(" : RegulatorCode={0}", RegulatorCode);

            return sb.ToString();
        }
    }

}
