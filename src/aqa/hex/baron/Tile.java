package aqa.hex.baron;

import java.util.ArrayList;
import java.util.List;

class Tile {
    protected String terrain;
    protected int x, y, z;
    protected Piece pieceInTile;
    protected List<Tile> neighbours = new ArrayList<>();

    public Tile(int xCoord, int yCoord, int zCoord) {
        x = xCoord;
        y = yCoord;
        z = zCoord;
        terrain = " ";
        pieceInTile = null;
    }

    public int getDistanceToTileT(Tile t) {
        return Math.max(Math.max(Math.abs(this.getx() - t.getx()),
                Math.abs(this.gety() - t.gety())),
                Math.abs(this.getz() - t.getz()));
    }

    public void addToNeighbours(Tile N) {
        neighbours.add(N);
    }

    public List<Tile> getNeighbours() {
        return neighbours;
    }

    public void setPiece(Piece thePiece) {
        pieceInTile = thePiece;
    }

    public void setTerrain(String t) {
        terrain = t;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public int getz() {
        return z;
    }

    public String getTerrain() {
        return terrain;
    }

    public Piece getPieceInTile() {
        return pieceInTile;
    }
}