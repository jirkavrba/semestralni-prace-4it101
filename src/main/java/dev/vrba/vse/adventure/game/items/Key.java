package dev.vrba.vse.adventure.game.items;

import com.sun.istack.NotNull;

public class Key implements PickableItem {
    public enum Color {
        RED("Červený"),
        BLUE("Modrý"),
        GREEN("Zelený");

        private final String name;

        Color(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    private final Color color;

    public Key(@NotNull Color color) {
        this.color = color;
    }

    @Override
    public String getName() {
        return color.getName() + " klíč";
    }

    @Override
    public int getWeight() {
        return 1;
    }
}
