using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class RunnerDescription
    {
        [JsonProperty(PropertyName = "selectionId")]
        public long SelectionId { get; set; }

        [JsonProperty(PropertyName = "runnerName")]
        public string RunnerName { get; set; }

        [JsonProperty(PropertyName = "handicap")]
        public double Handicap { get; set; }

        [JsonProperty(PropertyName = "sortPriority")]
        public int SortPriority { get; set; }

        [JsonProperty(PropertyName = "metadata")]
        public Dictionary<string, string> Metadata { get; set; }

        public override string ToString()
        {
            return new StringBuilder().AppendFormat("{0}", "RunnerDescription")
                        .AppendFormat(" : SelectionId={0}", SelectionId)
                        .AppendFormat(" : runnerName={0}", RunnerName)
                        .AppendFormat(" : Handicap={0}", Handicap)
                        .AppendFormat(" : SortPriority={0}", SortPriority)
                        .AppendFormat(" : Metadata={0}", Metadata)
                        .ToString();
        }
    }
}
