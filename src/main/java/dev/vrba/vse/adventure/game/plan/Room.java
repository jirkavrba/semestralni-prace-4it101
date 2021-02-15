package dev.vrba.vse.adventure.game.plan;

import dev.vrba.vse.adventure.game.items.Item;

import java.util.Set;

public interface Room {
    String getName();
    Set<RoomExit> getExits();
    Set<Item> getItems();

    Room addItem(Item item);
    Room addExit(RoomExit exit);
}
