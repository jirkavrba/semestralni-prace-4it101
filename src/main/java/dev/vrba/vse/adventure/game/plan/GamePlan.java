package dev.vrba.vse.adventure.game.plan;

import java.util.Set;

public interface GamePlan {
    Set<Room> getRooms();
    Room getCurrentRoom();
}
