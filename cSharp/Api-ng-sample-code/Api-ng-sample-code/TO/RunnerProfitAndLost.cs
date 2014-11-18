using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class RunnerProfitAndLoss
    {
        [JsonProperty(PropertyName = "selectionId")]
        public long SelectionId { get; set; }

        [JsonProperty(PropertyName = "ifWin")]
        public double IfWin { get; set; }

        [JsonProperty(PropertyName = "ifLose")]
        public double IfLose { get; set; }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.AppendFormat("{0}", "RunnerProfitAndLoss")
                        .AppendFormat(" : SelectionId={0}", SelectionId)
                        .AppendFormat(" : IfWin={0}", IfWin)
                        .AppendFormat(" : IfLose={0}", IfLose);

            return sb.ToString();
        }
    }
}
