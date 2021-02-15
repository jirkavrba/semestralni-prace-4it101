package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;

import java.util.Set;

public interface GamePlan {
    Set<Room> getRooms();
    Room getCurrentRoom();
    GamePlan setCurrentRoom(@NotNull Room room);
}
