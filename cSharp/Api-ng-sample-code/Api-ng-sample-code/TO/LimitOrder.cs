using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class LimitOrder
    {
        [JsonProperty(PropertyName = "size")]
        public double Size { get; set; }

        [JsonProperty(PropertyName = "price")]
        public double Price { get; set; }

        [JsonProperty(PropertyName = "persistenceType")]
        public PersistenceType PersistenceType { get; set; }

        public override string ToString()
        {
            return new StringBuilder()
                        .AppendFormat("Size={0}", Size)
                        .AppendFormat(" : Price={0}", Price)
                        .AppendFormat(" : PersistenceType={0}", PersistenceType)
                        .ToString();
        }
    }
}
