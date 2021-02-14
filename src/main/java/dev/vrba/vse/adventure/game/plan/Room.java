package dev.vrba.vse.adventure.game.plan;

import java.util.Set;

public interface Room {
    String getName();
    Set<RoomExit> getExits();
}
