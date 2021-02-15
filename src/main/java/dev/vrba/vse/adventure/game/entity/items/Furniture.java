package dev.vrba.vse.adventure.game.entity.items;

import com.sun.istack.NotNull;

/**
 * Třída představující nábytek, který není možné vzít a dát do batohu
 */
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
