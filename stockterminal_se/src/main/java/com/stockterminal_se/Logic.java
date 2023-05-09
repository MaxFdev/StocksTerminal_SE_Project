package com.stockterminal_se;

import java.util.*;

public class Logic {

    private Data dataManager;
    private Scanner argScanner;
    private String[] args;
    private char[] flags;
    private String output;

    /**
     * Basic constructor.
     */    
    public Logic() {
        this.dataManager = new Data();
        System.out.println("Please enter stock tickers when prompted.");
        start(getArgs());
    }

    /**
     * Constructor with args passed in.
     * 
     * @param args
     */
    public Logic(String[] args) {
        this.dataManager = new Data();
        start(args);
    }
    
    /**
     * Main method for if the program is called on its own.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Logic logic = new Logic(args);
    }

    /**
     * This method is called on startup to get the first args if they are not provided.
     * 
     * @return String[] of args
     * 
     */
    private String[] getArgs() {
        this.argScanner = new Scanner(System.in);
        ArrayList<String> args = new ArrayList<>();
        String in = this.argScanner.next();
        while (in != null) {
            args.add(in);
            in = this.argScanner.next();
        }
        this.argScanner.close();
        if (args.size() == 0) {
            System.out.println("Input was empty, please try again. (If you need help, enter \"help\")");
            return getArgs();
        }
        return (String[]) args.toArray();
    }

    /**
     * This method starts the whole program once it has proper input.
     * 
     * @param args
     * 
     */
    private void start(String[] args) {
        if (args.length == 0) {
            System.out.println("Input was empty, please try again. (If you need help, enter \"help\")");
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
     * Takes in unchecked args and makes sure they are all valid.
     * 
     * @param args
     * @return filtered args
     *
     */
    private String[] filter(String[] args) {
        ArrayList<String> tickersAndFlags = new ArrayList<>();
        boolean badInput = args.length > 11 ? true : false;
        for (int index = 0; index < args.length && !badInput; index++) {
            if (args[index].matches("[a-z-]+")) {
                if (args[index].matches("[a-z]+")) {
                    if (args[index].length() > 0 && args[index].length() < 5) {
                        tickersAndFlags.add(args[index]);
                        if (index == args.length - 1)
                            return (String[]) tickersAndFlags.toArray();
                    } else
                        badInput = true;
                } else if (args[index].startsWith("-") && index == args.length - 1) {
                    tickersAndFlags.add(args[index]);
                    return (String[]) tickersAndFlags.toArray();
                } else
                    badInput = true;
            }
        }
        System.out.println(
                "Input or part of input was invalid. Please try again. (If you need help, enter \"help\")");
        return null;
    }
    
    /**
     * Checks for flags. If there are none return null.
     * 
     * @param args
     * @return checked flags
     */
    private char[] getFlags(String[] args) {
        if (args[args.length - 1].startsWith("-") && args[args.length - 1].matches("[chloprtv]")) {
            return args[args.length - 1].substring(1).toCharArray();
        } else
            return null;
    }

    /**
     * This method continues running the program while nothing is going on.
     */
    private void continueProgram() {
        this.args = filter(getArgs());
        this.flags = getFlags(this.args);
        if (this.args == null || this.flags == null) {
            continueProgram();
        }

        // finish
    }
}