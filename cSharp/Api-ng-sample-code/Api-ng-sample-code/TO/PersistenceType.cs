using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Api_ng_sample_code.TO
{
    [JsonConverter(typeof(StringEnumConverter))]
    public enum PersistenceType
    {
        LAPSE,				// lapse the order at turn-in-play
        PERSIST,			// put the order into the auction (SP) at turn-in-play
        MARKET_ON_CLOSE,	// put the order into the auction (SP) at turn-in-play
    }
}
