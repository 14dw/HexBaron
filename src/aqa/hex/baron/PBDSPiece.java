package aqa.hex.baron;

import java.util.Random;

class PBDSPiece extends Piece {                                                                                         // Inherits from Piece
    public static Random rNoGen = new Random();                                                                         // Declares and initialises a random number generator.

    public PBDSPiece(boolean player1) {                                                                                 // Constructor
        super(player1);                                                                                                 // Calls super class constructor.
        pieceType = "P";                                                                                                // Initialise variables
        vPValue = 2;
        fuelCostOfMove = 2;
    }

    @Override                                                                                                           // Override method in super class
    public int checkMoveIsValid(int distanceBetweenTiles, String startTerrain, String endTerrain) {                     // Calculate the fuel needed to move, -1 if move is impossible
        if (distanceBetweenTiles != 1 || startTerrain.equals("~")) {    //
            return -1;
        }
        return fuelCostOfMove;
    }

    public int dig(String terrain) {
        if (!terrain.equals("~")) {
            return 0;
        }
        if (rNoGen.nextFloat() < 0.9) {
            return 1;
        } else {
            return 5;
        }
    }
}