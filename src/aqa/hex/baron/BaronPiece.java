package aqa.hex.baron;

class BaronPiece extends Piece {
    public BaronPiece(boolean player1) {
        super(player1);
        pieceType = "B";
        vPValue = 10;
    }

    @Override
    public int checkMoveIsValid(int distanceBetweenTiles, String startTerrain, String endTerrain) {
        if (distanceBetweenTiles == 1) {
            return fuelCostOfMove;
        }
        return -1;
    }
}

