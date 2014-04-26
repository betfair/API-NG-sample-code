package com.betfair.aping.api;

import com.betfair.aping.entities.*;
import com.betfair.aping.enums.MarketProjection;
import com.betfair.aping.enums.MarketSort;
import com.betfair.aping.enums.MatchProjection;
import com.betfair.aping.enums.OrderProjection;
import com.betfair.aping.exceptions.APINGException;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public abstract class ApiNgOperations {

    protected final String FILTER = "filter";
    protected final String LOCALE = "locale";
    protected final String SORT = "sort";
    protected final String MAX_RESULT = "maxResults";
    protected final String MARKET_IDS = "marketIds";
    protected final String MARKET_ID = "marketId";
    protected final String INSTRUCTIONS = "instructions";
    protected final String CUSTOMER_REF = "customerRef";
    protected final String locale = Locale.getDefault().toString();
    protected final String MARKET_PROJECTION = "marketProjection";

    public abstract List<EventTypeResult> listEventTypes(MarketFilter filter, String appKey, String ssoId) throws APINGException;

    public abstract List<MarketBook> listMarketBook(List<String> marketIds, PriceProjection priceProjection, OrderProjection orderProjection,
            MatchProjection matchProjection, String currencyCode, String appKey, String ssoId) throws APINGException;

    public abstract List<MarketCatalogue> listMarketCatalogue(MarketFilter filter, Set<MarketProjection> marketProjection,
            MarketSort sort, String maxResult, String appKey, String ssoId) throws APINGException;

    public abstract PlaceExecutionReport placeOrders(String marketId, List<PlaceInstruction> instructions, String customerRef, String appKey, String ssoId) throws APINGException;

    protected abstract String makeRequest(String operation, Map<String, Object> params, String appKey, String ssoToken) throws APINGException;

}
