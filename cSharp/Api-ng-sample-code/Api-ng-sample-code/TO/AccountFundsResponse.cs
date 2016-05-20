using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class AccountFundsResponse
    {
        [JsonProperty(PropertyName = "availableToBetBalance")]
        public double AvailableToBetBalance { get; set; }

        [JsonProperty(PropertyName = "exposure")]
        public double Exposure { get; set; }

        [JsonProperty(PropertyName = "retainedCommission")]
        public double RetainedCommission { get; set; }

        [JsonProperty(PropertyName = "exposureLimit")]
        public double ExposureLimit { get; set; }

        [JsonProperty(PropertyName = "discountRate")]
        public double DiscountRate { get; set; }
        
        [JsonProperty(PropertyName = "pointsBalance")]
        public int PointsBalance { get; set; }

        [JsonProperty(PropertyName = "marketCount")]
        public int MarketCount { get; set; }

        public override string ToString()
        {
            return new StringBuilder().AppendFormat("{0}", "AccountFundsResponse")
                        .AppendFormat(" : AvailableToBetBalance={0}", AvailableToBetBalance)
                        .AppendFormat(" : Exposure={0}", Exposure)
                        .AppendFormat(" : RetainedCommission={0}", RetainedCommission)
                        .AppendFormat(" : ExposureLimit={0}", ExposureLimit)
                        .AppendFormat(" : DiscountRate={0}", DiscountRate)
                        .AppendFormat(" : PointsBalance={0}", PointsBalance)
                        .AppendFormat(" : MarketCount={0}", MarketCount)
                        .ToString();
        }
    }
}
