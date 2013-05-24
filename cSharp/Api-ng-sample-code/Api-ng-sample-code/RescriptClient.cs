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

namespace Api_ng_sample_code
{
    public class RescriptClient : HttpWebClientProtocol, IClient
    {
        public string EndPoint { get; private set; }
        private static readonly IDictionary<string, Type> operationReturnTypeMap = new Dictionary<string, Type>();
        public const string APPKEY_HEADER = "X-Application";
        public const string SESSION_TOKEN_HEADER = "X-Authentication";
        public NameValueCollection CustomHeaders { get; set; }
        private static readonly string LIST_EVENT_TYPES_METHOD = "listEventTypes";
        private static readonly string LIST_MARKET_CATALOGUE_METHOD = "listMarketCatalogue";
        private static readonly string LIST_MARKET_BOOK_METHOD = "listMarketBook";
        private static readonly string PLACE_ORDERS_METHOD = "placeOrders";
        private static readonly String FILTER = "filter";
        private static readonly String LOCALE = "locale";
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

     
        public RescriptClient(string endPoint, string appKey, string sessionToken)
		{
            this.EndPoint = endPoint + "/rest/v1/";
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

        public IList<MarketBook> listMarketBook(IList<string> marketIds, PriceProjection priceProjection, OrderProjection? orderProjection = null, MatchProjection? matchProjection = null, string currencyCode = null, string locale = null)
        {
            var args = new Dictionary<string, object>();
            args[MARKET_IDS] = marketIds;
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

            args[MARKET_ID] = marketId;
            args[INSTRUCTIONS] = placeInstructions;
            args[CUSTOMER_REFERENCE] = customerRef;
            args[LOCALE] = locale;

            return Invoke<PlaceExecutionReport>(PLACE_ORDERS_METHOD, args);
        }


        protected HttpWebRequest CreateWebRequest(String restEndPoint)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(restEndPoint);
            request.Method = "POST";
            request.ContentType = "application/json";
            request.ContentLength = 0;
            request.Headers.Add(HttpRequestHeader.AcceptCharset, "UTF-8");
            request.Accept = "application/json";
            request.Headers.Add(CustomHeaders);
            return request;
        }


        public T Invoke<T>(string method, IDictionary<string, object> args = null)
        {
            if (method == null)
                throw new ArgumentNullException("method");
            if (method.Length == 0)
                throw new ArgumentException(null, "method");

            var restEndpoint = EndPoint + method + "/";
            var request = CreateWebRequest(restEndpoint);

            var postData = JsonConvert.Serialize<IDictionary<string, object>>(args) + "}";

            Console.WriteLine("\nCalling: " + method + " With args: " + postData);

            var bytes = Encoding.GetEncoding("UTF-8").GetBytes(postData);
            request.ContentLength = bytes.Length;

            using (Stream stream = request.GetRequestStream())
            {
                stream.Write(bytes, 0, bytes.Length);
            }

            using (HttpWebResponse response = (HttpWebResponse)GetWebResponse(request))
           
            using (Stream stream = response.GetResponseStream())
            using (StreamReader reader = new StreamReader(stream, Encoding.UTF8))
            {

                var jsonResponse = reader.ReadToEnd();
                Console.WriteLine("\nGot response: " + jsonResponse);
                
                if (response.StatusCode != HttpStatusCode.OK) {
                    throw ReconstituteException(JsonConvert.Deserialize<Api_ng_sample_code.TO.Exception>(jsonResponse));
                }
                return JsonConvert.Deserialize<T>(jsonResponse);

            }
        }

        private static System.Exception ReconstituteException(Api_ng_sample_code.TO.Exception ex)
        {
            var data = ex.Detail;
        
            // API-NG exception -- it must have "data" element to tell us which exception
            var exceptionName = data.Property("exceptionname").Value.ToString();
            var exceptionData = data.Property(exceptionName).Value.ToString();
            return JsonConvert.Deserialize<APINGException>(exceptionData);
            
        }

    }
}
