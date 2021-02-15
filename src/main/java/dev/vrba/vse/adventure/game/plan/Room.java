package dev.vrba.vse.adventure.game.plan;

import dev.vrba.vse.adventure.game.entity.LivingEntity;
import dev.vrba.vse.adventure.game.entity.items.Item;

import java.util.Set;

/**
 * Rozhraní, představující místnost ve hře
 */
public interface Room {
    /**
     * @return jméno místnosti, které se pak zobrazuje ve výpisu
     */
    String getName();

    /**
     * @return množina všech východů z dané místnosti
     */
    Set<RoomExit> getExits();

    /**
     * @return množina všech věcí, které místnost obsahuje
     */
    Set<Item> getItems();

    /**
     * @return množina všech nepřátel, kteří se v místnosti nachází
     */
    Set<LivingEntity> getEnemies();

    /**
     * Přidá východ specifikovaný v parametru do množiny východů dané místnosti
     * @param exit východ, který se má do množiny přidat
     */
    void addExit(RoomExit exit);

    /**
     * Přidá věc specifikovanou v parametru do množiny věcí nacházejících se v dané místnosti
     * @param item věc, která se má do množiny přidat
     */
    void addItem(Item item);

    /**
     * Přidá nepřítele specifikovaného v parametru do množiny nepřátel nacházejících se v dané místnosti
     * @param enemy nepřítel, který se má do množiny přidat
     */
    void addEnemy(LivingEntity enemy);

    /**
     * Odebere věc z množiny věcí nacházejících se v dané místnosti
     * @param item věc, která se má z množiny odebrat
     */
    void removeItem(Item item);

    /**
     * Odebere nepřítele z množin nacházejících se v dané místnosti
     * @param enemy nepřítel, který se má z množiny odebrat
     */
    void removeEnemy(LivingEntity enemy);
}
