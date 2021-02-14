package dev.vrba.vse.adventure.game.entity;


import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.plan.items.Backpack;

public class Player implements Entity {

    private final Backpack backpack;

    public Player(@NotNull Backpack backpack) {
        this.backpack = backpack;
    }

    @Override
    public String getName() {
        return "player";
    }

    public Backpack getBackpack() {
        return this.backpack;
    }
}
