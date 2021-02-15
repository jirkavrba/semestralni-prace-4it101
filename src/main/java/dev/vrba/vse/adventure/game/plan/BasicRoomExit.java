package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.entity.Player;

public class BasicRoomExit implements RoomExit {

    private final Room target;

    public BasicRoomExit(@NotNull Room target) {
        this.target = target;
    }

    @Override
    public Room getTargetRoom() {
        return this.target;
    }

    @Override
    public boolean canBeUsed(@NotNull Player player) {
        return true;
    }

    @Override
    public String getReasonWhyCannotBeUsed() {
        // This shouldn't happen at all...
        return "";
    }
}
