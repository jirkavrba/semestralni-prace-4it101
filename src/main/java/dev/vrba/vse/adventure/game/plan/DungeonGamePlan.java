package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;

import java.util.Set;

/**
 * Třída implementující herní plán
 */
public class DungeonGamePlan implements GamePlan {

    private Room currentRoom;

    private final Set<Room> rooms;

    public DungeonGamePlan(@NotNull Set<Room> rooms, @NotNull Room entry) {
        this.rooms = rooms;
        this.currentRoom = entry;
    }

    @Override
    public Set<Room> getRooms() {
        return rooms;
    }

    @Override
    public Room getCurrentRoom() {
        return currentRoom;
    }

    @Override
    public void setCurrentRoom(@NotNull Room room) {
        if (!rooms.contains(room)) {
            throw new IllegalStateException("Room was not found in the game plan. Cannot make it the current room.");
        }

        currentRoom = room;
    }
}
