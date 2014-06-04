package com.betfair.aping;

import com.betfair.aping.api.ApiNgOperations;
import com.betfair.aping.api.ApiNgRescriptOperations;
import com.betfair.aping.entities.*;
import com.betfair.aping.enums.*;
import com.betfair.aping.exceptions.APINGException;

import java.util.*;

/**
 * This is a demonstration class to show a quick demo of the new Betfair API-NG.
 * When you execute the class will: <li>find a market (next horse race in the
 * UK)</li> <li>get prices and runners on this market</li> <li>place a bet on 1
 * runner</li> <li>handle the error</li>
 * 
 */
public class ApiNGJRescriptDemo {

	private ApiNgOperations rescriptOperations = ApiNgRescriptOperations.getInstance();
	private String applicationKey;
	private String sessionToken;

    public void start(String appKey, String ssoid) {

        this.applicationKey = appKey;
        this.sessionToken = ssoid;

        try {

            /**
             * ListEventTypes: Search for the event types and then for the "Horse Racing" in the returned list to finally get
             * the listEventTypeId
             */
            MarketFilter marketFilter;
            marketFilter = new MarketFilter();
            Set<String> eventTypeIds = new HashSet<String>();

            System.out.println("1.(listEventTypes) Get all Event Types...\n");
            List<EventTypeResult> r = rescriptOperations.listEventTypes(marketFilter, applicationKey, sessionToken);
            System.out.println("2. Extract Event Type Id for Horse Racing...\n");
            for (EventTypeResult eventTypeResult : r) {
                if(eventTypeResult.getEventType().getName().equals("Horse Racing")){
                    System.out.println("3. EventTypeId for \"Horse Racing\" is: " + eventTypeResult.getEventType().getId()+"\n");
                    eventTypeIds.add(eventTypeResult.getEventType().getId().toString());
                }
            }

            /**
             * ListMarketCatalogue: Get next available horse races, parameters:
             * eventTypeIds : 7 - get all available horse races for event id 7 (horse racing)
             * maxResults: 1 - specify number of results returned (narrowed to 1 to get first race)
             * marketStartTime: specify date (must be in this format: yyyy-mm-ddTHH:MM:SSZ)
             * sort: FIRST_TO_START - specify sort order to first to start race
             */
            System.out.println("4.(listMarketCataloque) Get next horse racing market in the UK...\n");
            TimeRange time = new TimeRange();
            time.setFrom(new Date());

            Set<String> countries = new HashSet<String>();
            countries.add("GB");

            Set<String> typesCode = new HashSet<String>();
            typesCode.add("WIN");

            marketFilter = new MarketFilter();
            marketFilter.setEventTypeIds(eventTypeIds);
            marketFilter.setMarketStartTime(time);
            marketFilter.setMarketCountries(countries);
            marketFilter.setMarketTypeCodes(typesCode);

            Set<MarketProjection> marketProjection = new HashSet<MarketProjection>();
            marketProjection.add(MarketProjection.RUNNER_DESCRIPTION);

            String maxResults = "1";

            List<MarketCatalogue> marketCatalogueResult = rescriptOperations.listMarketCatalogue(marketFilter, marketProjection, MarketSort.FIRST_TO_START, maxResults,
                    applicationKey, sessionToken);

            System.out.println("5. Print static marketId, name and runners....\n");
            printMarketCatalogue(marketCatalogueResult.get(0));
            /**
             * ListMarketBook: get list of runners in the market, parameters:
             * marketId:  the market we want to list runners
             *
             */
            System.out.println("6.(listMarketBook) Get volatile info for Market including best 3 exchange prices available...\n");
            String marketIdChosen = marketCatalogueResult.get(0).getMarketId();

            PriceProjection priceProjection = new PriceProjection();
            Set<PriceData> priceData = new HashSet<PriceData>();
            priceData.add(PriceData.EX_BEST_OFFERS);
            priceProjection.setPriceData(priceData);

            //In this case we don't need these objects so they are declared null
            OrderProjection orderProjection = null;
            MatchProjection matchProjection = null;
            String currencyCode = null;

            List<String> marketIds = new ArrayList<String>();
            marketIds.add(marketIdChosen);

            List<MarketBook> marketBookReturn = rescriptOperations.listMarketBook(marketIds, priceProjection,
                    orderProjection, matchProjection, currencyCode, applicationKey, sessionToken);

            /**
             * PlaceOrders: we try to place a bet, based on the previous request we provide the following:
             * marketId: the market id
             * selectionId: the runner selection id we want to place the bet on
             * side: BACK - specify side, can be Back or Lay
             * orderType: LIMIT - specify order type
             * size: the size of the bet
             * price: the price of the bet
             * customerRef: 1 - unique reference for a transaction specified by user, must be different for each request
             *
             */

            long selectionId = 0;
            if ( marketBookReturn.size() != 0 ) {
                Runner runner = marketBookReturn.get(0).getRunners().get(0);
                selectionId = runner.getSelectionId();
                System.out.println("7. Place a bet below minimum stake to prevent the bet actually " +
                        "being placed for marketId: "+marketIdChosen+" with selectionId: "+selectionId+"...\n\n");
                List<PlaceInstruction> instructions = new ArrayList<PlaceInstruction>();
                PlaceInstruction instruction = new PlaceInstruction();
                instruction.setHandicap(0);
                instruction.setSide(Side.BACK);
                instruction.setOrderType(OrderType.LIMIT);

                LimitOrder limitOrder = new LimitOrder();
                limitOrder.setPersistenceType(PersistenceType.LAPSE);
                //API-NG will return an error with the default size=0.01. This is an expected behaviour.
                //You can adjust the size and price value in the "apingdemo.properties" file
                limitOrder.setPrice(getPrice());
                limitOrder.setSize(getSize());

                instruction.setLimitOrder(limitOrder);
                instruction.setSelectionId(selectionId);
                instructions.add(instruction);

                String customerRef = "1";

                PlaceExecutionReport placeBetResult = rescriptOperations.placeOrders(marketIdChosen, instructions, customerRef, applicationKey, sessionToken);

                // Handling the operation result
                if (placeBetResult.getStatus() == ExecutionReportStatus.SUCCESS) {
                    System.out.println("Your bet has been placed!!");
                    System.out.println(placeBetResult.getInstructionReports());
                } else if (placeBetResult.getStatus() == ExecutionReportStatus.FAILURE) {
                    System.out.println("Your bet has NOT been placed :*( ");
                    System.out.println("The error is: " + placeBetResult.getErrorCode() + ": " + placeBetResult.getErrorCode().getMessage());
                }
            } else {
                System.out.println("Sorry, no runners found\n\n");
            }

        } catch (APINGException apiExc) {
            System.out.println(apiExc.toString());
        }
    }

    private static double getPrice() {

        try {
            return new Double((String) ApiNGDemo.getProp().get("BET_PRICE"));
        } catch (NumberFormatException e) {
            //returning the default value
            return new Double(1000);
        }

    }

    private static double getSize(){
        try{
            return new Double((String)ApiNGDemo.getProp().get("BET_SIZE"));
        } catch (NumberFormatException e){
            //returning the default value
            return new Double(0.01);
        }
    }

    private void printMarketCatalogue(MarketCatalogue mk){
        System.out.println("Market Name: "+mk.getMarketName() + "; Id: "+mk.getMarketId()+"\n");
        List<RunnerCatalog> runners = mk.getRunners();
        if(runners!=null){
            for(RunnerCatalog rCat : runners){
                System.out.println("Runner Name: "+rCat.getRunnerName()+"; Selection Id: "+rCat.getSelectionId()+"\n");
            }
        }
    }

}
