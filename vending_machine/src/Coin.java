public enum Coin {
    Penny(0.1),
    Rupee(1);

    private final double value ;

    Coin(double value) {
        this.value = value;
    }

    public double getCoinValue(){
        return value;
    }
}
