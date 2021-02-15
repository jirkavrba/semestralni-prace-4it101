package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.entity.Player;

/**
 * Rozhraní reprezentující východ z mísnosti (respektive průchod mezi dvěma místnostmi)
 */
public interface RoomExit {
    /**
     * @return místnost, do které východ vede
     */
    Room getTargetRoom();

    /**
     * @param player hráč, pro kterého se podmínka průchodu vyhodnocuje
     * @return jestli je možné projít průchodem (např. jestli hráč vlastní klíč)
     */
    boolean canBeUsed(@NotNull Player player);

    /**
     * @return důvod, proč není možné daným průchodem projít
     */
    String getReasonWhyCannotBeUsed();
}
