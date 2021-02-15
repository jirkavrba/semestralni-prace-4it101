package dev.vrba.vse.adventure.game.entity.items;

import com.sun.istack.NotNull;

public class Furniture implements Item {

    private final String name;

    public Furniture(@NotNull String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
