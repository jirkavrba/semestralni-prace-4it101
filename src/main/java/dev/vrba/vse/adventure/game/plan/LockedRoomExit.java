package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.Key;

/**
 * Třída implementující průchod mezi místnostmi, ke kterému je vyžadován klíč
 */
public class LockedRoomExit extends BasicRoomExit {

    private final Key key;

    public LockedRoomExit(@NotNull Room target, @NotNull Key key) {
        super(target);
        this.key = key;
    }

    @Override
    public boolean canBeUsed(Player player) {
        return player.getBackpack().containsItem(this.key);
    }

    @Override
    public String getReasonWhyCannotBeUsed() {
        return "Tento je zamčen a může být odemčen pouze pomocí předmětu " + this.key.getName();
    }
}
