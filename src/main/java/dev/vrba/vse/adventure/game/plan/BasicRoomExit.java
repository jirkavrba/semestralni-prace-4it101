package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;

public class BasicRoomExit implements RoomExit {

    private final Room from;

    private final Room to;

    public BasicRoomExit(@NotNull Room from, @NotNull Room to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Room getFrom() {
        return this.from;
    }

    @Override
    public Room getTo() {
        return this.to;
    }
}
