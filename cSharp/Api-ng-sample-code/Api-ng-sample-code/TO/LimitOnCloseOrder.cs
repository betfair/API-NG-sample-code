using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class LimitOnCloseOrder
    {
        [JsonProperty(PropertyName = "size")]
        public double Size { get; set; }

        [JsonProperty(PropertyName = "liability")]
        public double Liability { get; set; }

        public override string ToString()
        {
            return new StringBuilder()
                        .AppendFormat("Size={0}", Size)
                        .AppendFormat(" : Liability={0}", Liability)
                        .ToString();
        }
    }
}
