package dev.vrba.vse.adventure.game.items;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.ui.Color;

public class Key implements PickableItem {
    // TODO: Maybe extract color rendering etc. to an UI-related class?
    public enum KeyColor {
        RED("červený"),
        BLUE("modrý"),
        GREEN("zelený");

        private final String name;

        KeyColor(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    private final KeyColor color;

    public Key(@NotNull KeyColor color) {
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
