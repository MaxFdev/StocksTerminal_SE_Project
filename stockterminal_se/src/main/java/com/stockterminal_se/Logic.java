package com.stockterminal_se;

import java.util.*;

public class Logic {

    private Data dataManager;
    private Scanner argScanner;
    private String[] args;
    private char[] flags;
    private Mode mode;
    private String output;

    private enum Mode {
        Query,
        Clear,
        Refresh,
        Remove,
        History,
        Live
    }

    /**
     * Basic constructor.
     */    
    public Logic() {
        animateStartUp();
        this.dataManager = new Data();
        start(getArgs());
    }

    /**
     * Constructor with args passed in.
     * 
     * @param args
     * 
     */
    public Logic(String[] args) {
        animateToStockInfo();
        this.dataManager = new Data();
        start(args);
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
        String start = "Welcome to StockTerminal-SE!\nEnter stock tickers when prompted.";
        for (char c : start.toCharArray()) {
            if (start.indexOf(c) < start.indexOf("!") || c == '!') {
                System.out.print("\033[31m" + c);
                start = start.substring(start.indexOf(c)+1);
            } else {
                System.out.print("\033[0m" + c);
            }
            waitTime(10);
        }
        waitTime(1000);
        clearTerminal();
        String[] animationFrames = { "|", "/", "-", "\\" };
        int frame = 0;
        int frameIndex = 0;
        System.out.print("\033[1;1H");
        while (frame < 15) {
            System.out.print("\033[1;1H");
            System.out.print(animationFrames[frameIndex]);
            frameIndex = (frameIndex + 1) % animationFrames.length;
            frame++;
            waitTime(100);
        }
        clearTerminal();
    }

    /**
     * Animates startup if stock info has already been entered.
     */
    private void animateToStockInfo() {
        clearTerminal();
        String[] animationFrames = { "|", "/", "-", "\\" };
        int frame = 0;
        int frameIndex = 0;
        while (frame < 10) {
            System.out.print("\033[1;1H");
            System.out.print(animationFrames[frameIndex]);
            frameIndex = (frameIndex + 1) % animationFrames.length;
            frame++;
            waitTime(100);
        }
        clearTerminal();
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

    /**
     * This method is called on startup to get the first args if they are not provided.
     * 
     * @return String[] of args
     * 
     */
    private String[] getArgs() {
        System.out.println("Please enter stock tickers (and flags).");
        this.argScanner = new Scanner(System.in);
        String in = this.argScanner.nextLine();
        String[] sp;
        if (in != null) {
            sp = in.split(" ");
        } else
            sp = new String[0];
        if (sp.length == 0) {
            clearTerminal();
            System.out.println("Input was empty, please try again. (If you need help, enter \"help\")");
            waitTime(500);
            clearTerminal();
            return getArgs();
        }
        return sp;
    }

    /**
     * This method starts the whole program once it has proper input.
     * 
     * @param args
     * 
     */
    private void start(String[] args) {
        if (args.length == 0) {
            clearTerminal();
            System.out.println("Input was empty, please try again. (If you need help, enter \"help\")");
            waitTime(500);
            clearTerminal();
            args = getArgs();
        }
        this.args = filter(args);
        if (this.args == null) {
            continueProgram();
        }
        this.flags = getFlags(this.args);

        // finish
    }

    /**
     * This method continues running the program while nothing is going on.
     */
    private void continueProgram() {
        this.args = null;
        this.flags = null;
        this.mode = null;
        this.output = null;
        this.args = filter(getArgs());
        if (this.args == null) {
            continueProgram();
        }
        this.flags = getFlags(this.args);
        if (this.flags == null) {
            continueProgram();
        }
        // finish
    }

    /**
     * Takes in unchecked args and makes sure they are all valid.
     * 
     * @param args
     * @return filtered args
     *
     */
    private String[] filter(String[] args) {
        ArrayList<String> tickersAndFlags = new ArrayList<String>();
        boolean badInput = args.length > 11 ? true : false;
        for (int index = 0; index < args.length && !badInput; index++) {
            if (args[index].matches("[a-z-]+")) {
                if (args[index].matches("[a-z]+")) {
                    if (args[index].length() > 0 && args[index].length() < 5) {
                        tickersAndFlags.add(args[index]);
                        if (index == args.length - 1 && tickersAndFlags.size() > 0)
                            return tickersAndFlags.toArray(new String[tickersAndFlags.size()]);
                    } else
                        badInput = true;
                } else if (args[index].startsWith("-") && index == args.length - 1) {
                    tickersAndFlags.add(args[index]);
                    return tickersAndFlags.toArray(new String[tickersAndFlags.size()]);
                } else
                    badInput = true;
            }
        }
        clearTerminal();
        System.out.println(
                "Input or part of input was invalid. Please try again. (If you need help, enter \"help\")");
        return null;
    }
    
    /**
     * Checks for flags. If there are none return null.
     * 
     * @param args
     * @return checked flags
     * 
     */
    private char[] getFlags(String[] args) {
        if (args[args.length - 1].startsWith("-") && args[args.length - 1].substring(1).matches("[cehloprtv]")) {
            return args[args.length - 1].substring(1).toCharArray();
        } else if (args[args.length - 1].startsWith("-")) {
            clearTerminal();
            System.out.println(
                    "Input or part of input was invalid. Please try again. (If you need help, enter \"help\")");
            return null;
        } else {
            return new char[0];
        }
    }
}