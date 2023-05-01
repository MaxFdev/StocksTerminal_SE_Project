# StocksTerminal - SE_Project

![](https://s40424.pcdn.co/in/wp-content/uploads/2022/07/info-systems.jpg.webp)

---

**Software Engineering Project:** CLI Stock Data Manager

**Collaborators:** Max Franklin `&& Noam Ben Simon

---

Needs:

>Who would use it?

Anyone from brokers trying to get a quick breakdown of a certain stock to everyday people trying to educate themselves on current public finances to software engineers trying to simplify stock data.

> What functions are provided?

 A "get" for general information, as well as a storage for previously accessed stocks that it auto-refreshes. It will also allow for summarizing tools in order to isolate prioritized information.

> Why?

To simplify and organize the information-getting process for stock data.
  
> How?

1. Run the program .jar file.

2. make a call to the program using "stock" as the first item of your args.

3. use one of the following commands to call and retrieve data:

        (a) "help" - prints a list of available commands, requests, and options, as well as a short description of each, akin to this list.

        (b) "[stock_symbol(s)] [live /or/ current] [requests(s)]" - executes the given command(s) on the stock symbol provided - separate symbols and requests with commas. Example provided:
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


The ideal experience using this product is straightforward: use the "help" or read the code in order to understand the commands, then apply them as you wish.


Identify what need the software is meeting

---


> Ideas/Functionality:

- Query; Commands to get data (specify wanted data)
    
    Wanted data (and usage) includes:

        1.symbol - the given


- Live; Live command (updates info every certain amount of time)
- `List`; List (lists multiple stocks that are specified)
- Help; Help (get help)

> Stock data we can get:

01. symbol
02. open
03. high
04. low
05. price
06. volume
07. latest trading day
08. previous close
09. change
10. change percent
