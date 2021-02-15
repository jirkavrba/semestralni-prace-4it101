package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.items.Item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BasicRoom implements Room {

    private final String name;

    private final Set<RoomExit> exits;

    private final Set<Item> items;

    public BasicRoom(@NotNull String name) {
        this.name = name;
        this.exits = new HashSet<>();
        this.items = new HashSet<>();
    }

    public BasicRoom(@NotNull String name, RoomExit... exits) {
        this.name = name;
        this.exits = Arrays.stream(exits).collect(Collectors.toSet());
        this.items = new HashSet<>();
    }

    public BasicRoom(@NotNull String name, @NotNull Set<RoomExit> exits, @NotNull Set<Item> items) {
        this.name = name;
        this.exits = exits;
        this.items = items;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Set<RoomExit> getExits() {
        return this.exits;
    }

    @Override
    public Set<Item> getItems() {
        return this.items;
    }

    @Override
    public Room addItem(@NotNull Item item) {
        this.items.add(item);
        return this;
    }

    @Override
    public Room addExit(@NotNull RoomExit exit) {
        this.exits.add(exit);
        return this;
    }
}
