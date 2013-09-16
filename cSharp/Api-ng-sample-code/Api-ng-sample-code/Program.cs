using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Api_ng_sample_code.TO;
using Api_ng_sample_code.Json;

namespace Api_ng_sample_code
{
    class Program
    {
        private static string Url = "https://api.betfair.com/exchange/betting";

        static void Main(string[] args)
        {
            //app key and session token are mandatory
            if (args.Length < 2)
            {
                Console.WriteLine("wrong number of arguments");
                Environment.Exit(0);
            }
            //first argument is the appkey
            var appkey = args[0];
            if (string.IsNullOrEmpty(appkey)) 
            {
               Console.WriteLine("No App Key");
               Environment.Exit(0);
            }
            Console.WriteLine("App Key being used: " + appkey);
            //second argument is the sessionToken
            var sessionToken = args[1];
            if(string.IsNullOrEmpty(sessionToken))
            {
                Console.WriteLine("No Session Token");
                Environment.Exit(0);
            }
            Console.WriteLine("Session token being used: " + sessionToken);
            //the third argument is which type of client to use, default is json-rpc
            IClient client = null;
            string clientType = null;
            if (args.Length == 3)
                clientType = args[2];
            // if rescript has been passed as the third argument use it otherwise default to json client
            if (!string.IsNullOrEmpty(clientType) && clientType.Equals("rescript"))
            { 
                Console.WriteLine("Using RescriptClient");
                client =  new RescriptClient(Url, appkey, sessionToken);
            }else
            {
                Console.WriteLine("Using JsonRpcClient");
                client = new JsonRpcClient(Url, appkey, sessionToken);
            }
            Console.WriteLine("\nBeginning sample run!\n");

            try
            {
                var marketFilter = new MarketFilter();

                var eventTypes = client.listEventTypes(marketFilter);
                // forming a eventype id set for the eventype id extracted from the result
                ISet<string> eventypeIds = new HashSet<string>();
                foreach (EventTypeResult eventType in eventTypes)
                {
                    if (eventType.EventType.Name.Equals("Horse Racing"))
                    {
                        Console.WriteLine("\nFound event type for Horse Racing: " + JsonConvert.Serialize<EventTypeResult>(eventType));
                        //extracting eventype id
                        eventypeIds.Add(eventType.EventType.Id);
                    }
                }


                //ListMarketCatalogue: Get next available horse races, parameters:
                var time = new TimeRange();
                time.From = DateTime.Now;
                time.To = DateTime.Now.AddDays(1);

                marketFilter = new MarketFilter();
                marketFilter.EventTypeIds = eventypeIds;
                marketFilter.MarketStartTime = time;
                marketFilter.MarketCountries = new HashSet<string>() { "GB" };
                marketFilter.MarketTypeCodes = new HashSet<String>() { "WIN" };

                var marketSort = MarketSort.FIRST_TO_START;
                var maxResults = "1";
				
				//as an example we requested runner metadata 
                ISet<MarketProjection> marketProjections = new HashSet<MarketProjection>();
                marketProjections.Add(MarketProjection.RUNNER_METADATA);


                Console.WriteLine("\nGetting the next available horse racing market");
                var marketCatalogues = client.listMarketCatalogue(marketFilter, marketProjections, marketSort, maxResults);
                //extract the marketId of the next horse race
                String marketId = marketCatalogues[0].MarketId;

                IList<string> marketIds = new List<string>();
                marketIds.Add(marketId);

                ISet<PriceData> priceData = new HashSet<PriceData>();
                //get all prices from the exchange
                priceData.Add(PriceData.EX_BEST_OFFERS);

                var priceProjection = new PriceProjection();
                priceProjection.PriceData = priceData;

                Console.WriteLine("\nGetting prices for market: " + marketId);
                var marketBook = client.listMarketBook(marketIds, priceProjection);

                if (marketBook.Count != 0)
                {
                    //get the first runner from the market
                    var runner = marketBook[0].Runners[0];
                    Console.WriteLine("\nUsing Runner: " + JsonConvert.Serialize<Runner>(runner));
                    var selectionId = runner.SelectionId;
                    Console.WriteLine("\nPreparing to place bet on runner with Selection Id: " + selectionId
                        + "\nBelonging to marketId: " + marketId
                        + "\nBelow minimum betsize and expecting a INVALID_BET_SIZE response");

                    IList<PlaceInstruction> placeInstructions = new List<PlaceInstruction>();
                    var placeInstruction = new PlaceInstruction();

                    placeInstruction.Handicap = 0;
                    placeInstruction.Side = Side.BACK;
                    placeInstruction.OrderType = OrderType.LIMIT;

                    var limitOrder = new LimitOrder();
                    limitOrder.PersistenceType = PersistenceType.LAPSE;
                    // place a back bet at rediculous odds so it doesn't get matched 
                    limitOrder.Price = 1000;
                    limitOrder.Size = 0.1; // placing a bet below minimum stake, expecting a error in report

                    placeInstruction.LimitOrder = limitOrder;
                    placeInstruction.SelectionId = selectionId;
                    placeInstructions.Add(placeInstruction);

                    var customerRef = "123456";
                    var placeExecutionReport = client.placeOrders(marketId, customerRef, placeInstructions);

                    ExecutionReportErrorCode executionErrorcode = placeExecutionReport.ErrorCode;
                    InstructionReportErrorCode instructionErroCode = placeExecutionReport.InstructionReports[0].ErrorCode;
                    Console.WriteLine("\nPlaceExecutionReport error code is: " + executionErrorcode
                        + "\nInstructionReport error code is: " + instructionErroCode);

                    if (executionErrorcode != ExecutionReportErrorCode.BET_ACTION_ERROR && instructionErroCode != InstructionReportErrorCode.INVALID_BET_SIZE)
                        Environment.Exit(0);

                    Console.WriteLine("\nDONE!");

                }
                else
                {
                    Console.Write("\nSorry the market has no runner to place a bet on, try again later");
                }

            }
            catch (APINGException apiExcepion)
            {
                Console.WriteLine("Got an exception from Api-NG: " + apiExcepion.ErrorCode);
                Environment.Exit(0);
            }
            catch (System.Exception e)
            {
                Console.WriteLine("Unknown exception from application: " + e.Message);
                Environment.Exit(0);

            }


            
        }
    }
}
