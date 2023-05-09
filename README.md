# StocksTerminal - SE_Project

![](https://s40424.pcdn.co/in/wp-content/uploads/2022/07/info-systems.jpg.webp)

---

**Software Engineering Project:** CLI Stock Data Manager

**Collaborators:** Max Franklin `&&` Noam Ben Simon

---

> Needs Being Met:

Ease of access to current and live stock data at a moment's notice.

> Who would use it?

Anyone from brokers trying to get a quick breakdown of a certain stock to everyday people trying to educate themselves on current public finances, and software engineers trying to simplify stock data.

This software can be particularly useful for automation. For example, someone can make a bash script that can send email notifications when certain stock data triggers a defined threshold.

> What functions are provided?

This software will be capable of retrieving all sorts of data with regard to stock ticker symbols. The data can be provide as a querry - whereby a single request is sent for the current market data for a stock (or stocks), or live data - which will continue to update the data in the CLI every decided interval.

It will also allow for summarizing tools in order to isolate and prioritize the most wanted information. Additionally, there will be functionality to querry multiple stock tickers and get a list of formatted data for each.

> Why?

To simplify and organize the information-getting process for stock data.

> How?

1.  Run the program .jar file (or directly through program files).

2.  make a call to the program using "stock" as the first item of your args.

3.  use one of the following commands to call and retrieve data:

        (a) "help" - prints a list of available commands, requests, and options,
        as well as a short description of each, akin to this list.

        (b) "[stock_symbol(s)] [request(s) flag(s)]"
            - executes the given command on the stock symbol(s) provided
            - separate symbols and requests with a space. Example provided:
            "aapl amzn -pov" - fetches the price, open
            price, and volume for aapl and amzn (empty
            request status assumes current)
            ---
            request flags and their uses:
                No specification / "" - assumes all other requests (same as "-cehloprtv")
                (All the following go after a "-")
                1. open / "o" - day's open price
                2. high / "h" - day's high price
                3. low / "l" - day's low price
                4. price / "e" - current price
                4. volume / "v" - stock volume
                5. latest trading day / "t" - last day that trading     was available
                6. previous close / "p" - the previous trading  day's closing price
                7. change / "c" - change in price since day's open
                8. change in percent / "r" - change in price since day's open represented as a percentage.

        (c) "clear" - clears the stock-history for refreshing data

        (d) "refresh" - gets the last stocks (up to 10) and requests all information offered on them.

        (e) "remove [stock_symbol(s)]" - removes the listed stock(s) from the data storage units. No listed stock will just delete the most recent stock symbol added.

        (f) "history" - prints all stocks that have been requested that have not been removed.

        (g) "Live [stock_symbol]" (capital "L") - gives a live feed of a specific stock.

        (h) "quit" - leaves the program.

The ideal experience using this product is straightforward: use the "help" or read the code in order to understand the commands, then apply them as you wish.

---

> Ideas/Functionality:

-   Help; Help (get help)
-   Query; Commands to get data (specify wanted data)
-   Clear; Clears stock cache
-   Refresh; Updates prices for a certain amount of stocks
-   Remove; Removes a stock from the cache
-   History; lists all stocks ever requested
-   Live; Live command (updates info every certain amount of time)
-   Quit; Exits the program

> Stock data we can get:

1.  symbol
2.  open
3.  high
4.  low
5.  price
6.  volume
7.  latest trading day
8.  previous close
9.  change
10. change percent
