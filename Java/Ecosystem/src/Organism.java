//
//
//  Generated by StarUML(tm) Java Add-In
//
//
//


public class Organism {
    private double rate;
    private double size;

    /**
     * Constructor organism
     *
     * @param initRate
     * @param initSize
     **/
    public Organism(double initRate, double initSize) {
        rate = initRate;
        size = initSize;
    }

    /**
     * Initialize rate
     *
     * @param newRate
     **/
    public void setRate(double newRate) {
        rate = newRate;
    }

    /**
     * Changes the size of the organism to the value amount
     *
     * @param amount
     **/
    public void alterSize(double amount) {
        size += amount;
    }

    /**
     * Checks for the mass of the body
     **/
    public boolean isAlive() {
        if (size > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns size
     **/
    public double getSize() {
        return size;
    }

    /**
     * Returns rate
     **/
    public double getRate() {
        return rate;
    }

    /**
     * Sets the rate of  weight to zero
     **/
    public void death() {
        size = 0;
    }

    /**
     * Changes in body weight of the value of rate
     **/
    public void simulateWeek() {
        size += rate;
    }
}
