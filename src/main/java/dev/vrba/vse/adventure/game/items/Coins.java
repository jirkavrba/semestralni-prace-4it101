package dev.vrba.vse.adventure.game.items;

public class Coins implements PickableItem {

    private final int amount;

    private static final int COIN_WEIGHT = 2;

    public Coins(int amount) {
        this.amount = amount;
    }

    @Override
    public String getName() {
        return amount + " mincí";
    }

    @Override
    public int getWeight() {
        return amount * COIN_WEIGHT;
    }
}
