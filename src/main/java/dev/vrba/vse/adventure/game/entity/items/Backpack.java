package dev.vrba.vse.adventure.game.entity.items;

import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Třída reprezentující batoh s omezenou nosností
 */
public class Backpack {

    private final int availableWeight;

    private final Set<PickableItem> items;

    public Backpack(int availableWeight) {
        this.availableWeight = availableWeight;
        this.items = new HashSet<>();
    }

    /**
     * @return celková nosnost batohu (kolik může být maximálně celková váha)
     */
    public int getAvailableWeight() {
        return availableWeight;
    }

    /**
     * @return zbývající nosnost batohu (kolik je ještě možné vložit váhy, než bude překročena nosnost)
     */
    public int getRemainingWeight() {
        return availableWeight - getTotalItemsWeight();
    }

    public int getTotalItemsWeight() {
        return items
                .stream()
                .map(PickableItem::getWeight)
                .reduce(0, Integer::sum);
    }

    /**
     * @return vrátí všechny předměty v batohu
     */
    public Set<PickableItem> getItems() {
        return items;
    }

    /**
     * @param item věc, u které se ověřuje, jestli je v batohu
     * @return jestli daná věc je obsažena v batohu
     */
    public boolean containsItem(@NotNull PickableItem item) {
        return items.contains(item);
    }

    /**
     * @param item věc, u které se ověřuje, jestli je možné ji přidat
     * @return jestli je možné přidat danou věc do batohu (jestli není moc těžká)
     */
    public boolean canAdd(@NotNull PickableItem item) {
       return item.getWeight() <= this.getRemainingWeight();
    }

    /**
     * Přidá věc do batohu
     * @param item věc, která se má do batohu přidat
     * @return instance batohu, aby bylo možné řetězit volání
     */
    @SuppressWarnings("UnusedReturnValue")
    public Backpack add(@NotNull PickableItem item) {
        this.items.add(item);
        return this;
    }

    /**
     * Odbere věc z batohu
     * @param item věc, která se má z batohu odebrat
     * @return instance batohu, aby bylo možné řetězit volání
     */
    @SuppressWarnings("UnusedReturnValue")
    public Backpack remove(@NotNull PickableItem item) {
        this.items.remove(item);
        return this;
    }
}
