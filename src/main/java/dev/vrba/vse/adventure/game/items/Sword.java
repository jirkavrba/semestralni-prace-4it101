package dev.vrba.vse.adventure.game.items;

public class Sword implements EquipableItem {
    @Override
    public String getName() {
        return "meč";
    }

    @Override
    public int getWeight() {
        return 35;
    }
}
