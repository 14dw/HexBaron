/*
 * Skeleton Program code for the AQA A Level Paper 1 Summer 2021 examination.
 * this code should be used in conjunction with the Preliminary Material
 * written by the AQA Programmer Team
 * developed in the NetBeans IDE 8.1 environment
 */

package aqa.hex.baron;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HexBaron {

    public HexBaron() {                                                                                                 // The entry point of the program
        boolean fileLoaded;
        Player player1 = new Player();
        Player player2 = new Player();                                                                                  // Objects to store the two players
        HexGrid grid;
        String choice = "";
        while (!choice.equals("Q")) {                                                                                   // Displays menu at start, deals with options.
            displayMainMenu();
            choice = Console.readLine();
            if (choice.equals("1")) {                                                                                   // Default game
                grid = setUpDefaultGame(player1, player2);
                playGame(player1, player2, grid);
            } else if (choice.equals("2")) {                                                                            // Load game from file
                Object[] returnObjects = loadGame(player1, player2);
                fileLoaded = (boolean) returnObjects[1];
                if (fileLoaded) {
                    grid = (HexGrid) returnObjects[0];
                    playGame(player1, player2, grid);                                                                   // Setup finished
                }
            }
        }
    }

    Object[] loadGame(Player player1, Player player2) {
        Console.write("Enter the name of the file to load: ");                                                       // Get the file name to load
        String fileName = Console.readLine();
        List<String> items;                                                                                             // List if items in each line
        String lineFromFile;
        HexGrid grid;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));                                           // Read file line by line
            lineFromFile = in.readLine();                                                                               // Get first line
            items = Arrays.asList(lineFromFile.split(","));                                                       // Split line by commas
            player1.setUpPlayer(items.get(0), Integer.parseInt(items.get(1)), Integer.parseInt(items.get(2)),
                    Integer.parseInt(items.get(3)), Integer.parseInt(items.get(4)));                                    // Setup player 1 from data in file
            lineFromFile = in.readLine();                                                                               // Get second line
            items = Arrays.asList(lineFromFile.split(","));
            player2.setUpPlayer(items.get(0), Integer.parseInt(items.get(1)), Integer.parseInt(items.get(2)),           // Setup player 2 from data in file
                    Integer.parseInt(items.get(3)), Integer.parseInt(items.get(4)));
            int gridSize = Integer.parseInt(in.readLine());                                                             // Get next line and parse to int
            grid = new HexGrid(gridSize);                                                                               // Setup grid from size
            List<String> t = Arrays.asList(in.readLine().split(","));                                             // Get next line and split by commas
            grid.setUpGridTerrain(t);                                                                                   // Add the terrain to the grid.
            lineFromFile = in.readLine();
            while (lineFromFile != null) {                                                                              // Iterate over file lines
                items = Arrays.asList(lineFromFile.split(","));                                                   // Split by commas
                if (items.get(0).equals("1")) {                                                                         // First item is the player
                    grid.addPiece(true, items.get(1), Integer.parseInt(items.get(2)));                    // Second is the type of piece and third is the tile index that its on
                } else {
                    grid.addPiece(false, items.get(1), Integer.parseInt(items.get(2)));
                }
                lineFromFile = in.readLine();
            }
        } catch (Exception e) {                                                                                         // If something goes throws an exception e.g. the file is not found tell the user
            Console.writeLine("File not loaded");
            return new Object[]{null, false};
        }
        return new Object[]{grid, true};                                                                                // Return grid, whether it was loaded
                                                                                                                        // Could just be grid if it is nullable but this is awful code
    }

    HexGrid setUpDefaultGame(Player player1, Player player2) {
        List<String> t = Arrays.asList(new String[]{" ", "#", "#", " ", "~", "~", " ", " ", " ", "~", " ", "#", "#", " ", " ", " ",
                " ", " ", "#", "#", "#", "#", "~", "~", "~", "~", "~", " ", "#", " ", "#", " "});                       // Hardcoded terrain
        int gridSize = 8;
        HexGrid grid = new HexGrid(gridSize);                                                                           // Initialise grid with given size
        player1.setUpPlayer("Player One", 0, 10, 10, 5);                                                  // Setup players (single letter variables and hardcoded :( )
        player2.setUpPlayer("Player Two", 1, 10, 10, 5);                                                  // Why does player 2 start with 1 VP?
        grid.setUpGridTerrain(t);
        grid.addPiece(true, "Baron", 0);                                                // Populate board with pieces
        grid.addPiece(true, "Serf", 8);
        grid.addPiece(false, "Baron", 31);
        grid.addPiece(false, "Serf", 23);
        return grid;
    }

    boolean checkMoveCommandFormat(List<String> items) {                                                                // Checks that a move command is valid
        int result;
        if (items.size() == 3) {                                                                                        // Check number of items is 3
            for (int count = 1; count <= 2; count++) {                                                                  // Check that items at indexes 1 and 2 are valid integers
                try {
                    result = Integer.parseInt(items.get(count));
                } catch (Exception e) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    boolean checkStandardCommandFormat(List<String> items) {                                                            // Checks that a standard command is valid
        int result;
        if (items.size() == 2) {                                                                                        // Check number of items is 2
            try {
                result = Integer.parseInt(items.get(1));                                                                // Check the second one is a valid integer
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }

    boolean checkUpgradeCommandFormat(List<String> items) {                                                             // Checks that an upgrade command is valid
        int result;
        if (items.size() == 3) {                                                                                        // Checks that number of item is 3
            if (!items.get(1).toUpperCase().equals("LESS") && !items.get(1).toUpperCase().equals("PBDS")) {             // Checks that piece name is valid (why not just or?)
                boolean ahhhhhh = (!(items.get(1).toUpperCase().equals("LESS") || items.get(1).toUpperCase().equals("PBDS")));
                return false;
            }
            try {
                result = Integer.parseInt(items.get(2));                                                                // Checks that the third item is valid integer
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }

    boolean checkCommandIsValid(List<String> items) {                                                                   // Checks that a command is valid
        if (items.size() > 0) {
            switch (items.get(0)) {                                                                                     // Switch on the first bit of the command
                case "move":
                    return checkMoveCommandFormat(items);                                                               // If move then validate using move validator
                case "dig":
                case "saw":
                case "spawn":
                    return checkStandardCommandFormat(items);                                                           // If dig, saw or spawn then validate using standard validator
                case "upgrade":
                    return checkUpgradeCommandFormat(items);                                                            // If upgrade then validate using upgrade validator
            }
        }
        return false;
    }

    void playGame(Player player1, Player player2, HexGrid grid) {                                                       // Game loop
        boolean gameOver = false;                                                                                       // Declares variables being used
        boolean player1Turn = true;
        boolean validCommand;
        List<String> commands = new ArrayList<>();
        Console.writeLine("Player One current state - " + player1.getStateString());                                 // Display each players state
        Console.writeLine("Player Two current state - " + player2.getStateString());
        do {                                                                                                            // Loop while the game is not or its player 2's turn
            Console.writeLine(grid.getGridAsString(player1Turn));                                                       // Display grid
            if (player1Turn) {
                Console.writeLine(player1.getName() + " state your three commands, pressing enter after each one."); // Prompt player for commands
            } else {
                Console.writeLine(player2.getName() + " state your three commands, pressing enter after each one.");
            }
            for (int count = 1; count <= 3; count++) {
                Console.write("Enter command: ");
                commands.add(Console.readLine().toLowerCase());                                                         // Parse commands (does to upper case later?)
            }
            for (String c : commands) {                                                                                 // Iterate through commands
                List<String> items = Arrays.asList(c.split(" "));                                                 // Split by spaces
                validCommand = checkCommandIsValid(items);                                                              // Check commands is valid
                if (!validCommand) {
                    Console.writeLine("Invalid command");
                } else {
                    int fuelChange = 0;                                                                                 // Declare and initialise some variables
                    int lumberChange = 0;
                    int supplyChange = 0;
                    String summaryOfResult;
                    Object[] returnObjects;                                                                             // ew
                    if (player1Turn) {
                        returnObjects = grid.executeCommand(items, fuelChange, lumberChange, supplyChange,              // Change the grid state
                                player1.getFuel(), player1.getLumber(),
                                player1.getPiecesInSupply());
                        summaryOfResult = returnObjects[0].toString();
                        fuelChange = (int) returnObjects[1];                                                            // Update resources
                        lumberChange = (int) returnObjects[2];
                        supplyChange = (int) returnObjects[3];
                        player1.updateLumber(lumberChange);
                        player1.updateFuel(fuelChange);
                        if (supplyChange == 1) {
                            player1.removeTileFromSupply();
                        }
                    } else {
                        returnObjects = grid.executeCommand(items, fuelChange, lumberChange, supplyChange,              // Change the grid state
                                player2.getFuel(), player2.getLumber(),
                                player2.getPiecesInSupply());
                        summaryOfResult = returnObjects[0].toString();
                        fuelChange = (int) returnObjects[1];                                                            // Update resources
                        lumberChange = (int) returnObjects[2];
                        supplyChange = (int) returnObjects[3];
                        player2.updateLumber(lumberChange);
                        player2.updateFuel(fuelChange);
                        if (supplyChange == 1) {
                            player2.removeTileFromSupply();
                        }
                    }
                    Console.writeLine(summaryOfResult);                                                                 // Show results of command ("Command Executed" or an error)
                }
            }
            commands.clear();
            player1Turn = !player1Turn;                                                                                 // Switch turns
            int player1VPsGained = 0;
            int player2VPsGained = 0;
            Object[] returnObjects;
            if (gameOver) {
                returnObjects = grid.destroyPiecesAndCountVPs(player1VPsGained, player2VPsGained);                      // Why make this 6 lines instead of 2?
            } else {
                returnObjects = grid.destroyPiecesAndCountVPs(player1VPsGained, player2VPsGained);
                gameOver = (boolean) returnObjects[0];                                                                  // gameOver |= (boolean) returnObjects[0]
            }
            player1VPsGained = (int) returnObjects[1];                                                                  // Update VPs
            player2VPsGained = (int) returnObjects[2];
            player1.addToVPs(player1VPsGained);
            player2.addToVPs(player2VPsGained);
            Console.writeLine("Player One current state - " + player1.getStateString());                             // Show each players state
            Console.writeLine("Player Two current state - " + player2.getStateString());
            Console.write("Press Enter to continue...");
            Console.readLine();
        } while (!gameOver || !player1Turn);                                                                            // End of game loop
        Console.writeLine(grid.getGridAsString(player1Turn));
        displayEndMessages(player1, player2);                                                                           // Display final results
    }

    void displayEndMessages(Player player1, Player player2) {                                                           // Display final results
        Console.writeLine();
        Console.writeLine(player1.getName() + " final state: " + player1.getStateString());
        Console.writeLine();
        Console.writeLine(player2.getName() + " final state: " + player2.getStateString());
        Console.writeLine();
        if (player1.getVPs() > player2.getVPs()) {                                                                      // Check which player won
            Console.writeLine(player1.getName() + " is the winner!");
        } else {
            Console.writeLine(player2.getName() + " is the winner!");
        }
    }

    void displayMainMenu() {                                                                                            // Display main menu options
        Console.writeLine("1. Default game");
        Console.writeLine("2. Load game");
        Console.writeLine("Q. Quit");
        Console.writeLine();
        Console.write("Enter your choice: ");
    }

    public static void main(String[] args) {                                                                            // Entry point
        new HexBaron();
    }
}