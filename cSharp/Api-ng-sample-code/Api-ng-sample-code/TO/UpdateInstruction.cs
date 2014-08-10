using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class UpdateInstruction
    {
        [JsonProperty(PropertyName = "betId")]
        public string BetId { get; set; }

        [JsonProperty(PropertyName = "newPersistenceType")]
        public PersistenceType NewPersistenceType { get; set; }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.AppendFormat("{0}", "UpdateInstruction")
                        .AppendFormat(" : BetId={0}", BetId)
                        .AppendFormat(" : NewPersistenceType={0}", NewPersistenceType);

            return sb.ToString();
        }
    }
}
