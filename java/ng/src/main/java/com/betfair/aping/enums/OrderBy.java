package com.betfair.aping.enums;

public enum OrderBy {
    /**
     * Use BY_PLACE_TIME instead.
     */
    @Deprecated
    BY_BET,
    BY_MARKET,
    BY_MATCH_TIME,
    BY_PLACE_TIME,
    BY_SETTLED_TIME,
    BY_VOID_TIME;
}
