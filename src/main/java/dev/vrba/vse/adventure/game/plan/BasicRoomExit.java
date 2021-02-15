package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.entity.Player;

public class BasicRoomExit implements RoomExit {

    private final Room to;

    public BasicRoomExit(@NotNull Room to) {
        this.to = to;
    }

    @Override
    public Room getTo() {
        return this.to;
    }

    @Override
    public boolean canBeUsed(@NotNull Player player) {
        return true;
    }
}
