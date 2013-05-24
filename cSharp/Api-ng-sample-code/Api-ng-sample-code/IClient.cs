using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Api_ng_sample_code.TO;

namespace Api_ng_sample_code
{
    public interface IClient
    {
        /**
         * calls api-ng to get a list of events
         * 
         * */
        IList<EventTypeResult> listEventTypes(MarketFilter marketFilter, string locale = null);
        
        /**
         * calls api-ng to get a list of market catalogues
         * */
        IList<MarketCatalogue> listMarketCatalogue(MarketFilter marketFilter, ISet<MarketProjection> marketProjections, MarketSort marketSort, string maxResult = "1", string locale = null);

        /**
         * calls api-ng to get more detailed info about the specified markets
         * */
        IList<MarketBook> listMarketBook(IList<string> marketIds, PriceProjection priceProjection, OrderProjection? orderProjection = null, MatchProjection? matchProjection = null, string currencyCode = null, string locale = null);

        /**
         * places a bet
         * */
        PlaceExecutionReport placeOrders(string marketId, string customerRef, IList<PlaceInstruction> placeInstructions, string locale = null);

    }
}
