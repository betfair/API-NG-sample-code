using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Api_ng_sample_code.TO
{
    [JsonConverter(typeof(StringEnumConverter))]
    public enum RunnerStatus
    {
        ACTIVE, WINNER, LOSER, REMOVED_VACANT, REMOVED,
        PLACED, HIDDEN
    }
}
