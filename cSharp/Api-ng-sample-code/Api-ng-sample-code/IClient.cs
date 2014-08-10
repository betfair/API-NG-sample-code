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

        /**
         * Lists market profit and loss
         * */
        IList<MarketProfitAndLoss> listMarketProfitAndLoss(IList<string> marketIds, bool includeSettledBets = false, bool includeBspBets = false, bool netOfCommission = false);

        /**
         * Lists current orders
         * */
        CurrentOrderSummaryReport listCurrentOrders(ISet<String> betIds, ISet<String> marketIds, OrderProjection? orderProjection = null, TimeRange placedDateRange = null, OrderBy? orderBy = null, SortDir? sortDir = null, int? fromRecord = null, int? recordCount = null);

        /**
         * Lists cleared orders
         * */
        ClearedOrderSummaryReport listClearedOrders(BetStatus betStatus, ISet<string> eventTypeIds = null, ISet<string> eventIds = null, ISet<string> marketIds = null, ISet<RunnerId> runnerIds = null, ISet<string> betIds = null, Side? side = null, TimeRange settledDateRange = null, GroupBy? groupBy = null, bool? includeItemDescription = null, String locale = null, int? fromRecord = null, int? recordCount = null);

        /**
         * Cancels a bet, or decreases its size
         * */
        CancelExecutionReport cancelOrders(string marketId, IList<CancelInstruction> instructions, string customerRef);

        /**
         * Replaces a bet: changes the price
         * */
        ReplaceExecutionReport replaceOrders(String marketId, IList<ReplaceInstruction> instructions, String customerRef);

        /**
         * updates a bet
         * */
        UpdateExecutionReport updateOrders(String marketId, IList<UpdateInstruction> instructions, String customerRef);


    }
}
