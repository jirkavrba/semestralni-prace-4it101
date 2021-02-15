package dev.vrba.vse.adventure.game.entity.items;

/**
 * Třída reprezentující mince pohozené v místnosti
 */
public class Coins implements PickableItem {

    /**
     * Počet mincí
     */
    private final int amount;

    /**
     * Váha jedné mince
     */
    private static final int COIN_WEIGHT = 1;

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
