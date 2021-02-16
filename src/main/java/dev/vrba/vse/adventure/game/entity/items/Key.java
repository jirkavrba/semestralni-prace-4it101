package dev.vrba.vse.adventure.game.entity.items;

import com.sun.istack.NotNull;

/**
 * Třída představující klíč, který odemyká průchod zamčenými dveřmi
 */
public class Key implements PickableItem {

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
