package aqa.hex.baron;

class Piece {
    protected boolean destroyed, belongsToplayer1;                                                                      // Declaring some variables to use later
    protected int fuelCostOfMove, vPValue, connectionsToDestroy;
    protected String pieceType;

    public Piece(boolean player1) {                                                                                     // Constructor
        fuelCostOfMove = 1;                                                                                             // Initialised variables
        belongsToplayer1 = player1;
        destroyed = false;
        pieceType = "S";
        vPValue = 1;
        connectionsToDestroy = 2;
    }

    public int getVPs() {                                                                                               // Public getter for vPValue
        return vPValue;
    }

    public int checkMoveIsValid(int distanceBetweenTiles, String startTerrain, String endTerrain) {                     // Calculates fuel needed to move, -1 if move impossible
        if (distanceBetweenTiles == 1) {                                                                                // Checks that the tile is adjacent
            if (startTerrain.equals("~") || endTerrain.equals("~")) {                                                   // Checks if starting at or ending at peat
                return fuelCostOfMove * 2;
            } else {
                return fuelCostOfMove;
            }
        }
        return -1;                                                                                                      // If move is too far
    }

    public boolean getBelongsToplayer1() {                                                                              // Public getter for belongsToPlayer1
        return belongsToplayer1;
    }

    public boolean hasMethod(String methodName) {
        try {
            this.getClass().getMethod(methodName, String.class);                                                        // Try to get method methodName from class
            return true;
        } catch (NoSuchMethodException e) {                                                                             // If it does't exist, return false
            return false;
        }
    }

    public int getConnectionsNeededToDestroy() {                                                                        // Public getter for connectionsToDestroy
        return connectionsToDestroy;
    }

    public String getPieceType() {                                                                                      // Returns symbol of piece
        if (belongsToplayer1) {                                                                                         // If it belongs player 1 it is upper case
            return pieceType;
        } else {                                                                                                        // Otherwise, it is lower case
            return pieceType.toLowerCase();
        }
    }

    public void destroyPiece() {                                                                                        // Public setter for destroyed
        destroyed = true;
    }
}
