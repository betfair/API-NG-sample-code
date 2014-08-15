using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Api_ng_sample_code.TO
{
    public class ReplaceInstructionReport
    {
        [JsonProperty(PropertyName = "status")]
        public InstructionReportStatus Status { get; set; }

        [JsonProperty(PropertyName = "placeInstructionReport")]
        public PlaceInstructionReport PlaceInstructionReport { get; set; }

        [JsonProperty(PropertyName = "errorCode")]
        public InstructionReportErrorCode ErrorCode { get; set; }

        [JsonProperty(PropertyName = "cancelInstructionReport")]
        public CancelInstructionReport CancelInstructionReport { get; set; }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.AppendFormat("{0}", "ReplaceInstructionReport")
                        .AppendFormat(" : Status={0}", Status)
                        .AppendFormat(" : PlaceInstructionReport={0}", PlaceInstructionReport)
                        .AppendFormat(" : ErrorCode={0}", ErrorCode)
                        .AppendFormat(" : CancelInstructionReport={0}", CancelInstructionReport);

            return sb.ToString();
        }
    }
}
