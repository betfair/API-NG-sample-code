using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class MarketDescription
    {
        [JsonProperty(PropertyName = "persistenceEnabled")]
        public bool IsPersistenceEnabled { get; set; }

        [JsonProperty(PropertyName = "bspMarket")]
        public bool IsBspMarket { get; set; }

        [JsonProperty(PropertyName = "marketTime")]
        public DateTime MarketTime { get; set; }

        [JsonProperty(PropertyName = "suspendTime")]
        public DateTime? SuspendTime { get; set; }

        [JsonProperty(PropertyName = "settleTime")]
        public DateTime? SettleTime { get; set; }

        [JsonProperty(PropertyName = "bettingType")]
        public MarketBettingType BettingType { get; set; }

        [JsonProperty(PropertyName = "turnInPlayEnabled")]
        public bool IsTurnInPlayEnabled { get; set; }

        [JsonProperty(PropertyName = "marketType")]
        public string MarketType { get; set; }

        [JsonProperty(PropertyName = "regulator")]
        public string Regulator { get; set; }

        [JsonProperty(PropertyName = "marketBaseRate")]
        public double MarketBaseRate { get; set; }

        [JsonProperty(PropertyName = "discountAllowed")]
        public bool IsDiscountAllowed { get; set; }

        [JsonProperty(PropertyName = "wallet")]
        public string Wallet { get; set; }

        [JsonProperty(PropertyName = "rules")]
        public string Rules { get; set; }

        [JsonProperty(PropertyName = "rulesHasDate")]
        public bool RulesHasDate { get; set; }

        [JsonProperty(PropertyName = "clarifications")]
        public string Clarifications { get; set; }

        public override string ToString()
        {
            return new StringBuilder().AppendFormat("{0}", "MarketDescription")
                        .AppendFormat(" : BspMarket={0}", IsBspMarket)
                        .AppendFormat(" : BettingType={0}", BettingType)
                        .AppendFormat(" : MarketType={0}", MarketType)

                        .AppendFormat(" : MarketTime={0}", MarketTime)
                        .AppendFormat(" : SuspendTime={0}", SuspendTime)
                        .AppendFormat(" : SettleTime={0}", SettleTime)
                        .AppendFormat(" : MarketBaseRate={0}", MarketBaseRate)

                        .AppendFormat(" : IsPersistenceEnabled={0}", IsPersistenceEnabled)
                        .AppendFormat(" : IsTurnInPlayEnabled={0}", IsTurnInPlayEnabled)
                        .AppendFormat(" : IsDiscountAllowed={0}", IsDiscountAllowed)

                        .AppendFormat(" : Regulator={0}", Regulator)
                        .AppendFormat(" : Rules={0}", Rules)
                        .AppendFormat(" : RulesHasDate={0}", RulesHasDate)
                        .AppendFormat(" : Clarifications={0}", Clarifications)

                        .AppendFormat(" : Wallet={0}", Wallet)
                        .ToString();
        }
    }
}
