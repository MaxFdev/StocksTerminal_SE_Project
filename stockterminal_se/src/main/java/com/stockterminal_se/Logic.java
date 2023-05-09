package com.stockterminal_se;

import java.util.*;

public class Logic {

    private Data dataManager;
    private Scanner argScanner;
    private String[] args;
    private char[] flags;
    private Mode mode;
    private String output;
    private boolean end;

    private enum Mode {
        Error,
        Help,
        Query,
        Clear,
        Refresh,
        Remove,
        History,
        Live,
        Quit
    }

    /**
     * Basic constructor.
     */    
    public Logic() {
        animateStartUp();
        this.dataManager = new Data();
        this.end = false;
        start(getArgs());
        while (!end) {
            continueProgram();
        }
    }

    /**
     * Constructor with args passed in.
     * 
     * @param args
     * 
     */
    public Logic(String[] args) {
        this.dataManager = new Data();
        this.end = false;
        start(args);
        while (!end) {
            continueProgram();
        }
    }
    
    /**
     * Main method for if the program is called on its own.
     * 
     * @param args
     * 
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            Logic logic = new Logic(args);
        } else {
            Logic logic = new Logic();
        }

    }

    /**
     * Animates the startup for the program.
     */
    private void animateStartUp() {
        clearTerminal();

        waitTime(500);

        String logo = 
        " ___  _             _    _____                   _              _ " +"\n"+
        "/ __|| |_  ___  __ | |__|_   _| ___  _ _  _ __  (_) _ _   __ _ | |"+"\n"+
        "\\__ \\|  _|/ _ \\/ _|| / /  | |  / -_)| '_|| '  \\ | || ' \\ / _` || |"+"\n"+
        "|___/ \\__|\\___/\\__||_\\_\\  |_|  \\___||_|  |_|_|_||_||_||_|\\__/_||_|";
        
        System.out.println("\u001B[32m" + logo + "\n");

        waitTime(500);

        String start = "Welcome to StockTerminal-SE!\nEnter commands when prompted (or \"help\" for help).";
        for (char c : start.toCharArray()) {
            if (start.indexOf(c) < start.indexOf("!") || c == '!') {
                System.out.print("\033[31m" + c);
                start = start.substring(start.indexOf(c) + 1);
            } else {
                System.out.print("\033[0m" + c);
            }
            waitTime(10);
        }
        
        waitTime(5000);

        clearTerminal();

        loadingWheel();

        clearTerminal();
    }

    /**
     * This method starts the whole program once it has proper input.
     * 
     * @param args
     * 
     */
    private void start(String[] args) {
        this.args = args;

        if (this.args.length == 0) {
            this.mode = Mode.Error;
        } else {
            checkMode(this.args);
        }
        
        runMode();
    }

    /**
     * This method continues running the program while nothing is going on.
     */
    private void continueProgram() {
        this.args = getArgs();

        clearTerminal();

        checkMode(this.args);

        runMode();
    }

    /**
     * This method checks args to determine the proper processing mode.
     * 
     * @param args
     * 
     */
    private void checkMode(String[] args) {
        if (args.length == 1) {
            switch (args[0]) {
                case "quit" -> {
                    this.mode = Mode.Quit;
                }
                case "help" -> {
                    this.mode = Mode.Help;
                }
                case "clear" -> {
                    this.mode = Mode.Clear;
                }
                case "refresh" -> {
                    this.mode = Mode.Refresh;
                }
                case "history" -> {
                    this.mode = Mode.History;
                }
                default -> {
                    filter(args);
                }
            }
        } else if (args.length == 2) {
            switch (args[0]) {
                case "remove" -> {
                    if (check(args[1])) {
                        this.mode = Mode.Remove;
                    } else {
                        this.mode = Mode.Error;
                    }
                }
                case "Live" -> {
                    if (check(args[1])) {
                        this.mode = Mode.Live;
                    } else {
                        this.mode = Mode.Error;
                    }
                }
                default -> {
                    filter(args);
                }
            }
        } else {
            filter(args);
        }
    }

    /**
     * Checks the mode to run the correct operation.
     */
    private void runMode() {
        switch (this.mode) {
            case Quit -> {
                quit();
            }
            case Error -> {
                inputError();
            }
            case Help -> {
                help();
            }
            case Clear -> {
                clear();
            }
            case Refresh -> {
                refresh();
            }
            case History -> {
                history();
            }
            case Remove -> {
                remove(this.args[1]);
            }
            case Live -> {
                live(this.args[1]);
            }
            case Query -> {
                filter(this.args);
                query();
            }
        }
        clearValues();
        clearTerminal();
        loadingWheel();
        clearTerminal();
    }
    
    /*
     * <-----| These methods manage the output and make calls to preform operations |----->
    */

    private void quit() {
        this.end = true;
    }

    private void inputError() {
        clearTerminal();
        this.output = "Input was invalid! Please try again when prompted. (enter \"help\" for help or \"quit\" to exit)";
        animateOutput();
        waitTime(1000);
    }

    private void help() {
        clearTerminal();
        String help = 
        "(a) \"help\" - prints a list of available commands, requests, and options,\n as well as a short description of each, akin to this list." + "\n\n" +
        "(b) \"[stock_symbol(s, up to 10)] [request(s) flag(s)]\"" + "\n" +
        "    - executes the given command on the stock symbol(s) provided" + "\n" +
        "    - separate symbols and requests with a space. Example provided:" + "\n" +
        "    \"aapl amzn -pov\" - fetches the price, open" + "\n" +
        "    price, and volume for aapl and amzn (empty" + "\n" +
        "    request status assumes current)" + "\n" +
        "    ---" + "\n" +
        "    request flags and their uses:" + "\n" +
        "        No specification / \"\" - assumes all other requests (same as \"-cehloprtv\")" + "\n" +
        "        (All the following go after a \"-\")" + "\n" +
        "        1. open / \"o\" - day's open price" + "\n" +
        "        2. high / \"h\" - day's high price" + "\n" +
        "        3. low / \"l\" - day's low price" + "\n" +
        "        4. price / \"e\" - current price" + "\n" +
        "        4. volume / \"v\" - stock volume" + "\n" +
        "        5. latest trading day / \"t\" - last day that trading     was available" + "\n" +
        "        6. previous close / \"p\" - the previous trading  day's closing price" + "\n" +
        "        7. change / \"c\" - change in price since day's open" + "\n" +
        "        8. change in percent / \"r\" - change in price since day's open represented as a percentage." + "\n" +
        "(c) \"clear\" - clears the stock-history for refreshing data" + "\n\n" +
        "(d) \"refresh\" - gets the last stocks (up to 10) and requests all information offered on them." + "\n\n" +
        "(e) \"remove [stock_symbol]\" - removes the given stock from the data storage units." + "\n\n" +
        "(f) \"history\" - prints all stocks that have been requested that have not been removed." + "\n\n" +
                        "(g) \"Live [stock_symbol]\" (capital \"L\") - gives a live feed of a specific stock.";
        quickType(help);
        waitTime(10000);
    }

    private void clear() {
        clearTerminal();
        this.dataManager.clear();
        this.output = "Data has been cleared.";
        animateOutput();
        waitTime(1000);
    }

    private void refresh() {
        clearTerminal();
        List<Stock> stocks = this.dataManager.refresh();
        this.output = "Data has been updated, here is all the new data:";
        animateOutput();
        for (Stock stock : stocks) {
            waitTime(50);
            quickType("\n" + stock.toString());
        }
        waitTime(10000);
    }

    private void history() {
        clearTerminal();
        List<Stock> stocks = this.dataManager.history();
        this.output = "Here are all the stocks ever requested:";
        animateOutput();
        for (Stock stock : stocks) {
            waitTime(10);
            quickType("\n" + stock.symbol());
        }
        waitTime(1000);
    }

    private void remove(String ticker) {
        clearTerminal();
        if (this.dataManager.remove(ticker)) {
            this.output = ticker + " was removed from data.";
        } else {
            this.output = ticker + "was not in data.";
        }
        animateOutput();
        waitTime(1000);
    }

    private void live(String ticker) {
        boolean stayLive = true;
        int refreshes = 0;
        while (stayLive) {
            refreshes++;
            clearTerminal();
            System.out.print(this.dataManager.live(ticker).toString());
            stayLive = refreshes == 60 ? false : true;
            waitTime(1005);
        }
    }

    private void query() {
        if (this.flags == null) {
            if (this.args.length == 1) {
                query(this.args[0]);
            } else {
                query(this.args);
            }
        } else {

        }
    }

    private void query(String[] tickers) {
        List<Stock> stocks = this.dataManager.query(tickers);
        if (stocks == null) {
            clearTerminal();
            this.output = "There were some unexpected problems. Please try again.";
            animateOutput();
        } else {
            for (Stock stock : stocks) {
                waitTime(50);
                quickType(stock.toString());
            }
            waitTime(1000);
        }
    }
    
    private void query(String ticker) {
        Stock stock = this.dataManager.query(ticker);
        if (stock == null) {
            clearTerminal();
            this.output = "There were some unexpected problems. Please try again.";
            animateOutput();
        } else {
            quickType(stock.toString());
            waitTime(1000);
        }
    }

    /*
     * <----------------------------------------------------------------------------------->
    */
    
    /**
     * Takes in unchecked args and makes sure they are all valid.
     * 
     * @param args
     *
     */
    private void filter(String[] args) {
        ArrayList<String> tickersAndFlags = new ArrayList<String>();
        boolean badInput = args.length > 11 || args.length < 1 ? true : false;
        for (int index = 0; index < args.length && !badInput; index++) {
            if (args[index].matches("[a-z-]+")) {
                if (args[index].matches("[a-z]+")) {
                    if (args[index].length() > 0 && args[index].length() < 5) {
                        tickersAndFlags.add(args[index]);
                        if (index == args.length - 1 && tickersAndFlags.size() > 0) {
                            this.args = tickersAndFlags.toArray(new String[tickersAndFlags.size()]);
                            this.mode = Mode.Query;
                            return;
                        }
                    } else{
                        badInput = true;
                    }
                } else if (args[index].startsWith("-") && index == args.length - 1) {
                    getFlags(args[index]);
                    this.args = tickersAndFlags.toArray(new String[tickersAndFlags.size()]);
                    this.mode = Mode.Query;
                    return;
                } else {
                    badInput = true;
                }
            }
        }
        this.mode = Mode.Error;
    }
    
    /**
     * Checks for flags.
     * 
     * @param args
     * 
     */
    private void getFlags(String arg) {
        if (arg.startsWith("-") && arg.substring(1).matches("[cehloprtv]+")) {
            this.flags = arg.substring(1).toCharArray();
        } else {
            this.mode = Mode.Error;
        }
    }

    /**
     * This method checks if a single arg is a valid ticker.
     * 
     * @param arg
     * @return true if its valid
     * 
     */
    private boolean check(String arg) {
        return (arg.length() > 1 && arg.length() < 5 && arg.matches("[a-z]+")) ? true : false;
    }
    
    /**
     * This method is called on startup to get the first args if they are not provided.
     * 
     * @return String[] of args
     * 
     */
    private String[] getArgs() {
        clearTerminal();

        quickType("Please enter commands bellow.\n");

        this.argScanner = new Scanner(System.in);
        String in = this.argScanner.nextLine();
        String[] sp;
        if (in != null) {
            sp = in.split(" ");
        } else {
            sp = new String[0];
        }
        
        clearTerminal();

        if (sp.length == 0) {
            quickType("Input was empty, please try again. (If you need help, enter \"help\")");
            waitTime(2000);
            return getArgs();
        }

        return sp;
    }

    private void animateOutput() {
        for (char c : this.output.toCharArray()) {
            System.out.print(c);
            waitTime(100);
        }
    }

    private void quickType(String print) {
        for (char c : print.toCharArray()) {
            System.out.print(c);
            waitTime(25);
        }
    }

    private void loadingWheel() {
        String[] animationFrames = { "|", "/", "-", "\\" };
        int frame = 0;
        int frameIndex = 0;
        System.out.print("\033[1;1H");
        while (frame < 15) {
            System.out.print("\033[1;1H");
            System.out.print(animationFrames[frameIndex]);
            frameIndex = (frameIndex + 1) % animationFrames.length;
            frame++;
            waitTime(50);
        }
    }

    private void clearValues() {
        this.args = null;
        this.flags = null;
        this.mode = null;
        this.output = null;
        this.mode = null;
    }
    
    private void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void waitTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
}