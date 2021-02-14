package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.Set;

public class BasicRoom implements Room {

    private final String name;

    private final Set<RoomExit> exits;

    public BasicRoom(@NotNull String name) {
        this.name = name;
        this.exits = new HashSet<>();
    }

    public BasicRoom(@NotNull String name, @NotNull Set<RoomExit> exits) {
        this.name = name;
        this.exits = exits;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Set<RoomExit> getExits() {
        return this.exits;
    }
}
