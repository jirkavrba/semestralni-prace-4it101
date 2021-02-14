package dev.vrba.vse.adventure.game.commands;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.DungeonGame;

public interface Command {
    String getName();
    String getDescription();
    DungeonGame execute(@NotNull DungeonGame game, String... arguments);
}
