package aqa.hex.baron;



class Player {
    protected int piecesInSupply, fuel, vPs, lumber;                                                                    // Why protected not private? Maybe will need to inherit.
    protected String name;

    public Player() {                                                                                                   // Default constructor
    }

    public void setUpPlayer(String n, int v, int f, int l, int t) {
        name = n;
        vPs = v;
        fuel = f;
        lumber = l;
        piecesInSupply = t;
    }

    public String getStateString() {
        return "VPs: " + vPs + "   Pieces in supply: " + piecesInSupply + "   Lumber: " + lumber + "   Fuel: " + fuel;
    }

    public int getVPs() {
        return vPs;
    }

    public int getFuel() {
        return fuel;
    }

    public int getLumber() {
        return lumber;
    }

    public String getName() {
        return name;
    }

    public void addToVPs(int n) {
        vPs += n;
    }

    public void updateFuel(int n) {
        fuel += n;
    }

    public void updateLumber(int n) {
        lumber += n;
    }

    public int getPiecesInSupply() {
        return piecesInSupply;
    }

    public void removeTileFromSupply() {
        piecesInSupply -= 1;
    }
}