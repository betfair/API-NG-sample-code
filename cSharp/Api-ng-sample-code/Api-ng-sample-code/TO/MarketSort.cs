using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Api_ng_sample_code.TO
{
     [JsonConverter(typeof(StringEnumConverter))]
    public enum MarketSort
    {
         MINIMUM_TRADED, MAXIMUM_TRADED, MINIMUM_AVAILABLE, MAXIMUM_AVAILABLE, FIRST_TO_START, LAST_TO_START,
    }
}
