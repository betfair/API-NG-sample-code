using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Api_ng_sample_code.TO
{
    [JsonConverter(typeof(StringEnumConverter))]
    public enum MatchProjection
    {
        NO_ROLLUP, ROLLED_UP_BY_PRICE, ROLLED_UP_BY_AVG_PRICE
    }
}
