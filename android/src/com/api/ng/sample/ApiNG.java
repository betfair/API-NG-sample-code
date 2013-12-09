package com.api.ng.sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.betfair.api.ng.client.Operations;
import com.betfair.api.ng.json.Constants;
import com.betfair.api.ng.objects.EventType;
import com.betfair.api.ng.objects.LimitOrder;
import com.betfair.api.ng.objects.MarketBook;
import com.betfair.api.ng.objects.MarketCatalogue;
import com.betfair.api.ng.objects.MarketFilter;
import com.betfair.api.ng.objects.MarketProjection;
import com.betfair.api.ng.objects.MarketSort;
import com.betfair.api.ng.objects.MatchProjection;
import com.betfair.api.ng.objects.OrderProjection;
import com.betfair.api.ng.objects.OrderType;
import com.betfair.api.ng.objects.PersistenceType;
import com.betfair.api.ng.objects.PlaceExecutionReport;
import com.betfair.api.ng.objects.PlaceInstruction;
import com.betfair.api.ng.objects.PriceData;
import com.betfair.api.ng.objects.PriceProjection;
import com.betfair.api.ng.objects.Runner;
import com.betfair.api.ng.objects.RunnerCatalog;
import com.betfair.api.ng.objects.Side;
import com.betfair.api.ng.objects.TimeRange;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class ApiNG extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_api_ng);

		enableLogingIn();
//		if (Constants.SSO_TOKEN != null) {
//			
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_api_ng, menu);
		return true;
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void enableLogingIn() {

		WebView webView = new WebView(this);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setAcceptCookie(true);
		cookieManager.removeAllCookie();
		final AlertDialog dialog = new AlertDialog.Builder(this)
				.setTitle("LogIn to your betfair account")
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
								
							}
						}).create();

		webView.setWebViewClient(new WebViewClient() {
			// Webview cannot intercept post requests AND access the body made
			// by the browser/javascript,so have to resort to sniffing cookies
			// for the session token, ideal solution would be to intercept the
			// post request and retrieve the session token from the body.

			public void onPageFinished(WebView view, String url) {
				String cookies = CookieManager.getInstance().getCookie(url);
				if (cookies != null) {
					if (cookies.contains("loggedIn=true;")) {
						String ssoid = cookies.substring(cookies
								.indexOf("ssoid=") + 6);
						ssoid = ssoid.substring(0, ssoid.indexOf(";"));
						if (ssoid != null) {
							Constants.SSO_TOKEN = ssoid;
							dialog.dismiss();
							runSample();
							
						}
					}
				}
			}

		});
		dialog.setView(webView);
		webView.loadUrl(Constants.LOG_IN_URL);
		dialog.show();
	}

	public void runSample() {
		TextView textView = (TextView) findViewById(R.id.display);
		textView.setText("Starting Sample run...\n");
		Operations operations = Operations.getInstance();
		/*
		 * formulate the filter for the next horse race WIN market in GB
		 */
		MarketFilter filter = new MarketFilter();
		Set<String> horserRaceIds = new HashSet<String>();
		// get the eventType id for horse racing
		textView.append("1. Get All Event Types... \n");
		List<EventType> eventTypes = operations.listEventTypes(filter);
		textView.append("2. Extract Event Type Id for Horse Racing");
		for (EventType eventType : eventTypes) {
			if (("Horse Racing").equals(eventType.getName())) {
				textView.append("EventTypeId for \"Horse Racing\" is: "
						+ eventType.getId());
				horserRaceIds.add(eventType.getId());
			}
		}
		// set time range to be from now otherwise could end up with a market
		// thats already started
		TimeRange range = new TimeRange();
		range.setFrom(new Date());
		// only interested in GB
		Set<String> countries = new HashSet<String>();
		countries.add("GB");
		// only interested in WIN market
		Set<String> marketTypes = new HashSet<String>();
		marketTypes.add("WIN");
		// putting it all together
		filter.setEventTypeIds(horserRaceIds);
		filter.setMarketStartTime(range);
		filter.setMarketCountries(countries);
		filter.setMarketTypeCodes(marketTypes);

		textView.append("4. Getting the next horse racing WIN market in the uk \n");
		// along with some market information i would like some runner data
		// aswell
		Set<MarketProjection> marketProjections = new HashSet<MarketProjection>();
		marketProjections.add(MarketProjection.RUNNER_DESCRIPTION);
		int maxResults = 1; // only need one market

		// getBasic market data for the next available market
		List<MarketCatalogue> marketCatalogues = operations
				.listMarketCatalogue(filter, marketProjections, MarketSort.FIRST_TO_START,
						maxResults);

		textView.append("5. Got market data: \n");

		printMarkets(textView, marketCatalogues.get(0));

		String marketId = marketCatalogues.get(0).getMarketId();

		// (not needed for sample)
		OrderProjection orderProjection = null; // for existing bets
		MatchProjection matchProjection = null; // for existing matched bets
		String currencyCode = null; // not mandatory will get defaulted anyway.

		Set<String> marketIds = new HashSet<String>();
		marketIds.add(marketId);

		// get the top three best prices
		PriceProjection priceProjection = new PriceProjection();
		Set<PriceData> priceDatas = new HashSet<PriceData>();
		priceDatas.add(PriceData.EX_BEST_OFFERS);
		priceProjection.setPriceData(priceDatas);
		textView.append("6. Get volatile data for market. \n");
		List<MarketBook> marketBooks = operations
				.listMarketBook(marketIds, priceProjection, orderProjection,
						matchProjection, currencyCode);

		MarketBook marketBook = marketBooks.get(0);
		// get the first runner
		Runner runner = marketBook.getRunners().get(0);

		textView.append("7. Placing a bet below minimum stake for marketId: "
				+ marketBook.getMarketId() + " with selectionId + "
				+ runner.getSelectionId() + "\n");

		List<PlaceInstruction> instructions = new ArrayList<PlaceInstruction>();
		PlaceInstruction instruction = new PlaceInstruction();
		instruction.setHandicap(0.0);
		instruction.setSide(Side.BACK);
		instruction.setOrderType(OrderType.LIMIT);

		LimitOrder limitOrder = new LimitOrder();
		limitOrder.setPersistenceType(PersistenceType.LAPSE);
		// API-NG will return an error with the default size=0.01. This is an
		// expected behaviour.
		limitOrder.setPrice(1.5);
		limitOrder.setSize(0.01);

		instruction.setLimitOrder(limitOrder);
		instruction.setSelectionId(runner.getSelectionId());
		instructions.add(instruction);

		String customerRef = "1";

		PlaceExecutionReport placeBetResult = operations.placeOrders(
				marketBook.getMarketId(), instructions, customerRef);

		// Handling the operation result
		if ("SUCCESS".equals(placeBetResult.getStatus())) {
			textView.append("Your bet has been placed!!");
			textView.append(placeBetResult.getInstructionReports().toString());
		} else if ("FAILURE".equals(placeBetResult.getStatus())) {
			textView.append("Your bet has NOT been placed : ");
			textView.append("The error is: "
					+ placeBetResult.getErrorCode()
					+ ": "
					+ placeBetResult.getInstructionReports().get(0)
							.getErrorCode());
		}

	}

	private void printMarkets(TextView textView, MarketCatalogue mc) {
		textView.append("MarketId: " + mc.getMarketId() + " MarketName: "
				+ mc.getMarketName() + "\n");
		List<RunnerCatalog> runnerCatalogs = mc.getRunners();
		for (RunnerCatalog runner : runnerCatalogs) {
			textView.append("SelectionId: " + runner.getSelectionId()
					+ " RunnerName: " + runner.getRunnerName() + "\n");
		}

	}
}
