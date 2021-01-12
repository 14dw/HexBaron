package aqa.hex.baron;

class LESSPiece extends Piece {                                                                                         // Inherits from Piece
    public LESSPiece(boolean player1) {                                                                                 // Constructor
        super(player1);                                                                                                 // Calling super class' constructor
        pieceType = "L";
        vPValue = 3;
    }

    @Override                                                                                                           // Use this method instead of super class' method
    public int checkMoveIsValid(int distanceBetweenTiles, String startTerrain, String endTerrain) {
        if (distanceBetweenTiles == 1 && !startTerrain.equals("#")) {                                                   // Can only move 1 space and cannot move from forrest
            if (startTerrain.equals("~") || endTerrain.equals("~")) {                                                   // If starting from or ending at peat, movement cost is doubled
                return fuelCostOfMove * 2;
            } else {
                return fuelCostOfMove;
            }
        }
        return -1;
    }

    public int saw(String terrain) {                                                                                    // Allows piece to saw if on forrest tile
        if (!terrain.equals("#")) {                                                                                     // Check that it is in not in a forest
            return 0;
        }
        return 1;                                                                                                       // Returns 1 if can saw
    }
}