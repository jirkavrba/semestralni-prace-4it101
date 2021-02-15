package dev.vrba.vse.adventure.game.items;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.ui.Color;

public class Key implements PickableItem {
    // TODO: Maybe extract color rendering etc. to an UI-related class?
    public enum KeyColor {
        RED("Červený", Color.RED),
        BLUE("Modrý", Color.BLUE),
        GREEN("Zelený", Color.GREEN);

        private final String name;

        private final String color;

        KeyColor(String name, String color) {
            this.name = name;
            this.color = color;
        }

        public String getName() {
            return this.name;
        }

        public String getColor() {
            return this.color;
        }
    }

    private final KeyColor color;

    public Key(@NotNull KeyColor color) {
        this.color = color;
    }

    @Override
    public String getName() {
        return color.getColor() + color.getName() + " klíč" + Color.RESET;
    }

    @Override
    public int getWeight() {
        return 1;
    }
}
