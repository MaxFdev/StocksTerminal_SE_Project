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

1.  Run the program .jar file.

2.  make a call to the program using "stock" as the first item of your args.

3.  use one of the following commands to call and retrieve data:

        (a) "help" - prints a list of available commands, requests, and options,
        as well as a short description of each, akin to this list.

        (b) "[stock_symbol(s)] [live /or/ current] [requests(s)]" 
        - executes the given command(s) on the stock symbol provided
        - separate symbols and requests with commas. Example provided:
            "aapl, amzn -pov" - fetches the price, open
            price, and volume for aapl and amzn (empty
            request status assumes current)

        (c) requests and their uses:
            1. no request / "" - assumes all other
            requests
            2. open / "o" - day's open price
            3. high / "h" - day's high price
            4. low / "l" - day's low price
            5. volume / "v" - stock volume
            6. latest trading day / "t" - last day that
            trading was available
            7. previous close / "p" - the previous
            trading day's closing price
            8. change / "c" - change in price since day's
            open
            9. change in percent / "r" - change in price
            since day's open represented as a
            percentage.

        (d) "clear" - clears the stock-history for refreshing data

        (e) "refresh" - gets the last stocks (up to 10) and requests all information offered on them.

The ideal experience using this product is straightforward: use the "help" or read the code in order to understand the commands, then apply them as you wish.

---

> Ideas/Functionality:

-   Query; Commands to get data (specify wanted data)

-   Live; Live command (updates info every certain amount of time)
-   `List`; List (lists multiple stocks that are specified)
-   Help; Help (get help)

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
