package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.Set;

public class BasicRoom implements Room {

    private final Set<RoomExit> exits;

    public BasicRoom() {
        this.exits = new HashSet<>();
    }

    public BasicRoom(@NotNull Set<RoomExit> exits) {
        this.exits = exits;
    }

    @Override
    public Set<RoomExit> getExits() {
        return this.exits;
    }
}
