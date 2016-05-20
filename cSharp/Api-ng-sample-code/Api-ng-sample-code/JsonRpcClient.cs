using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text;
using Api_ng_sample_code.TO;
using System.Web.Services.Protocols;
using System.Net;
using System.IO;
using Api_ng_sample_code.Json;
using Api_ng_sample_code.TO;

namespace Api_ng_sample_code
{
    public class JsonRpcClient  : HttpWebClientProtocol, IClient
    {
        public string EndPoint { get; private set; }
        private static readonly IDictionary<string, Type> operationReturnTypeMap = new Dictionary<string, Type>();
        public const string APPKEY_HEADER = "X-Application";
        public const string SESSION_TOKEN_HEADER = "X-Authentication";
        public NameValueCollection CustomHeaders { get; set; }
        private static readonly string LIST_EVENT_TYPES_METHOD = "SportsAPING/v1.0/listEventTypes";
        private static readonly string LIST_MARKET_TYPES_METHOD = "SportsAPING/v1.0/listMarketTypes";
        private static readonly string LIST_MARKET_CATALOGUE_METHOD = "SportsAPING/v1.0/listMarketCatalogue";
        private static readonly string LIST_MARKET_BOOK_METHOD = "SportsAPING/v1.0/listMarketBook";
        private static readonly string PLACE_ORDERS_METHOD = "SportsAPING/v1.0/placeOrders";
        private static readonly string LIST_MARKET_PROFIT_AND_LOST_METHOD = "SportsAPING/v1.0/listMarketProfitAndLoss";
        private static readonly string LIST_CURRENT_ORDERS_METHOD = "SportsAPING/v1.0/listCurrentOrders";
        private static readonly string LIST_CLEARED_ORDERS_METHOD = "SportsAPING/v1.0/listClearedOrders";
        private static readonly string CANCEL_ORDERS_METHOD = "SportsAPING/v1.0/cancelOrders";
        private static readonly string REPLACE_ORDERS_METHOD = "SportsAPING/v1.0/replaceOrders";
        private static readonly string UPDATE_ORDERS_METHOD = "SportsAPING/v1.0/updateOrders";
        private static readonly string GET_ACCOUNT_FUNDS_METHOD = "AccountAPING/v1.0/getAccountFunds";
        private static readonly String FILTER = "filter";
        private static readonly String LOCALE = "locale";
        private static readonly String WALLET = "wallet";
        private static readonly String CURRENCY_CODE = "currencyCode";
        private static readonly String MARKET_PROJECTION = "marketProjection";
        private static readonly String MATCH_PROJECTION = "matchProjection";
        private static readonly String ORDER_PROJECTION = "orderProjection";
        private static readonly String PRICE_PROJECTION = "priceProjection";
        private static readonly String SORT = "sort";
        private static readonly String MAX_RESULTS = "maxResults";
        private static readonly String MARKET_IDS = "marketIds";
        private static readonly String MARKET_ID = "marketId";
        private static readonly String INSTRUCTIONS = "instructions";
        private static readonly String CUSTOMER_REFERENCE = "customerRef";
        private static readonly String INCLUDE_SETTLED_BETS = "includeSettledBets";
        private static readonly String INCLUDE_BSP_BETS = "includeBspBets";
        private static readonly String NET_OF_COMMISSION = "netOfCommission";
        private static readonly String BET_IDS = "betIds";
        private static readonly String PLACED_DATE_RANGE = "placedDateRange";
        private static readonly String ORDER_BY = "orderBy";
        private static readonly String SORT_DIR = "sortDir";
        private static readonly String FROM_RECORD = "fromRecord";
        private static readonly String RECORD_COUNT = "recordCount";
        private static readonly string BET_STATUS = "betStatus";
        private static readonly string EVENT_TYPE_IDS = "eventTypeIds";
        private static readonly string EVENT_IDS = "eventIds";
        private static readonly string RUNNER_IDS = "runnerIds";
        private static readonly string SIDE = "side";
        private static readonly string SETTLED_DATE_RANGE = "settledDateRange";
        private static readonly string GROUP_BY = "groupBy";
        private static readonly string INCLUDE_ITEM_DESCRIPTION = "includeItemDescription";

        public JsonRpcClient(string endPoint, string appKey, string sessionToken)
		{
            this.EndPoint = endPoint + "/json-rpc/v1";
            CustomHeaders = new NameValueCollection();
            if (appKey != null)
            {
                CustomHeaders[APPKEY_HEADER] = appKey;
            }
            if (sessionToken != null)
            {
                CustomHeaders[SESSION_TOKEN_HEADER] = sessionToken;
            }
		}


        public IList<EventTypeResult> listEventTypes(MarketFilter marketFilter, string locale = null)
        {
            var args = new Dictionary<string, object>();
            args[FILTER] = marketFilter;
            args[LOCALE] = locale;
            return Invoke<List<EventTypeResult>>(LIST_EVENT_TYPES_METHOD, args);
            
        }

        public IList<MarketCatalogue> listMarketCatalogue(MarketFilter marketFilter, ISet<MarketProjection> marketProjections, MarketSort marketSort, string maxResult = "1", string locale = null)
        {
            var args = new Dictionary<string, object>();
            args[FILTER] = marketFilter;
            args[MARKET_PROJECTION] = marketProjections;
            args[SORT] = marketSort;
            args[MAX_RESULTS] = maxResult;
            args[LOCALE] = locale;
            return Invoke<List<MarketCatalogue>>(LIST_MARKET_CATALOGUE_METHOD, args);
        }

        public IList<MarketTypeResult> listMarketTypes(MarketFilter marketFilter, string stringLocale)
        {
            var args = new Dictionary<string, object>();
            args[FILTER] = marketFilter;
            args[LOCALE] = stringLocale;
            return Invoke<List<MarketTypeResult>>(LIST_MARKET_TYPES_METHOD, args);
        }

        public IList<MarketBook> listMarketBook(IList<string> marketIds, PriceProjection priceProjection, OrderProjection? orderProjection = null, MatchProjection? matchProjection = null, string currencyCode = null, string locale = null)
        {
            var args = new Dictionary<string, object>();
            args[MARKET_IDS]= marketIds;
            args[PRICE_PROJECTION] = priceProjection;
            args[ORDER_PROJECTION] = orderProjection;
            args[MATCH_PROJECTION] = matchProjection;
            args[LOCALE] = locale;
            args[CURRENCY_CODE] = currencyCode;
            return Invoke<List<MarketBook>>(LIST_MARKET_BOOK_METHOD, args);
        }

        public PlaceExecutionReport placeOrders(string marketId, string customerRef, IList<PlaceInstruction> placeInstructions, string locale = null)
        {
            var args = new Dictionary<string, object>();
            
            args[MARKET_ID] =  marketId;
            args[INSTRUCTIONS] = placeInstructions;
            args[CUSTOMER_REFERENCE] = customerRef;
            args[LOCALE] =  locale;

            return Invoke<PlaceExecutionReport>(PLACE_ORDERS_METHOD, args);
        }



        protected WebRequest CreateWebRequest(Uri uri)
        {
            WebRequest request = WebRequest.Create(new Uri(EndPoint));
            request.Method = "POST";
            request.ContentType = "application/json-rpc";
            request.Headers.Add(HttpRequestHeader.AcceptCharset, "ISO-8859-1,utf-8");
            request.Headers.Add(CustomHeaders);
            ServicePointManager.Expect100Continue = false;
            return request;
        }



        public T Invoke<T>(string method, IDictionary<string, object> args = null)
        {
            if (method == null)
                throw new ArgumentNullException("method");
            if (method.Length == 0)
                throw new ArgumentException(null, "method");

            var request = CreateWebRequest(new Uri(EndPoint));

            using (Stream stream = request.GetRequestStream())
            using (StreamWriter writer = new StreamWriter(stream, Encoding.UTF8))
            {
                var call = new JsonRequest { Method = method, Id = 1, Params = args };
                JsonConvert.Export(call, writer);
            }
            Console.WriteLine("\nCalling: " + method +  " With args: " + JsonConvert.Serialize<IDictionary<string, object>>(args));

            using (WebResponse response = GetWebResponse(request))
            using (Stream stream = response.GetResponseStream())
            using (StreamReader reader = new StreamReader(stream, Encoding.UTF8))
            {
                var jsonResponse = JsonConvert.Import<T>(reader);
               // Console.WriteLine("\nGot Response: " + JsonConvert.Serialize<JsonResponse<T>>(jsonResponse));
                if (jsonResponse.HasError)
                {
                    throw ReconstituteException(jsonResponse.Error);
                }
                else
                {
                    return jsonResponse.Result;
                }
            }
        }


        private static System.Exception ReconstituteException(Api_ng_sample_code.TO.Exception ex)
        {
            var data = ex.Data;

            // API-NG exception -- it must have "data" element to tell us which exception
            var exceptionName = data.Property("exceptionname").Value.ToString();
            var exceptionData = data.Property(exceptionName).Value.ToString();
            return JsonConvert.Deserialize<APINGException>(exceptionData);
        }

        public IList<MarketProfitAndLoss> listMarketProfitAndLoss(IList<string> marketIds, bool includeSettledBets = false, bool includeBspBets = false, bool netOfCommission = false)
        {
            var args = new Dictionary<string, object>();
            args[MARKET_IDS] = marketIds;
            args[INCLUDE_SETTLED_BETS] = includeSettledBets;
            args[INCLUDE_BSP_BETS] = includeBspBets;
            args[NET_OF_COMMISSION] = netOfCommission;
            return Invoke<List<MarketProfitAndLoss>>(LIST_MARKET_PROFIT_AND_LOST_METHOD, args);
        }

        public CurrentOrderSummaryReport listCurrentOrders(ISet<String> betIds, ISet<String> marketIds, OrderProjection? orderProjection = null, TimeRange placedDateRange = null, OrderBy? orderBy = null, SortDir? sortDir = null, int? fromRecord = null, int? recordCount = null)
        {
            var args = new Dictionary<string, object>();
            args[BET_IDS] = betIds;
            args[MARKET_IDS] = marketIds;
            args[ORDER_PROJECTION] = orderProjection;
            args[PLACED_DATE_RANGE] = placedDateRange;
            args[ORDER_BY] = orderBy;
            args[SORT_DIR] = sortDir;
            args[FROM_RECORD] = fromRecord;
            args[RECORD_COUNT] = recordCount;

            return Invoke<CurrentOrderSummaryReport>(LIST_CURRENT_ORDERS_METHOD, args);
        }

        public ClearedOrderSummaryReport listClearedOrders(BetStatus betStatus, ISet<string> eventTypeIds = null, ISet<string> eventIds = null, ISet<string> marketIds = null, ISet<RunnerId> runnerIds = null, ISet<string> betIds = null, Side? side = null, TimeRange settledDateRange = null, GroupBy? groupBy = null, bool? includeItemDescription = null, String locale = null, int? fromRecord = null, int? recordCount = null)
        {
            var args = new Dictionary<string, object>();
            args[BET_STATUS] = betStatus;
            args[EVENT_TYPE_IDS] = eventTypeIds;
            args[EVENT_IDS] = eventIds;
            args[MARKET_IDS] = marketIds;
            args[RUNNER_IDS] = runnerIds;
            args[BET_IDS] = betIds;
            args[SIDE] = side;
            args[SETTLED_DATE_RANGE] = settledDateRange;
            args[GROUP_BY] = groupBy;
            args[INCLUDE_ITEM_DESCRIPTION] = includeItemDescription;
            args[LOCALE] = locale;
            args[FROM_RECORD] = fromRecord;
            args[RECORD_COUNT] = recordCount;

            return Invoke<ClearedOrderSummaryReport>(LIST_CLEARED_ORDERS_METHOD, args);
        }


        public CancelExecutionReport cancelOrders(string marketId, IList<CancelInstruction> instructions, string customerRef)
        {
            var args = new Dictionary<string, object>();
            args[MARKET_ID] = marketId;
            args[INSTRUCTIONS] = instructions;
            args[CUSTOMER_REFERENCE] = customerRef;

            return Invoke<CancelExecutionReport>(CANCEL_ORDERS_METHOD, args);
        }

        public ReplaceExecutionReport replaceOrders(String marketId, IList<ReplaceInstruction> instructions, String customerRef)
        {
            var args = new Dictionary<string, object>();
            args[MARKET_ID] = marketId;
            args[INSTRUCTIONS] = instructions;
            args[CUSTOMER_REFERENCE] = customerRef;

            return Invoke<ReplaceExecutionReport>(REPLACE_ORDERS_METHOD, args);
        }

        public UpdateExecutionReport updateOrders(String marketId, IList<UpdateInstruction> instructions, String customerRef)
        {
            var args = new Dictionary<string, object>();
            args[MARKET_ID] = marketId;
            args[INSTRUCTIONS] = instructions;
            args[CUSTOMER_REFERENCE] = customerRef;

            return Invoke<UpdateExecutionReport>(UPDATE_ORDERS_METHOD, args);
        }

        public AccountFundsResponse getAccountFunds(Wallet wallet)
        {
            var args = new Dictionary<string, object>();
            args[WALLET] = wallet;
            return Invoke<AccountFundsResponse>(GET_ACCOUNT_FUNDS_METHOD, args);
        }
    }
}
