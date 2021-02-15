package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.Key;

public class LockedRoomExit extends BasicRoomExit {

    private final Key key;

    public LockedRoomExit(@NotNull Room target, @NotNull Key key) {
        super(target);
        this.key = key;
    }

    public Key getKey() {
        return this.key;
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
