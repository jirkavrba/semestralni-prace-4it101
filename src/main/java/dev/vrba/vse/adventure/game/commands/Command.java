package dev.vrba.vse.adventure.game.commands;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.DungeonGame;

/**
 * Společné rozhraní pro všechny příkazy v rámci hry
 */
public interface Command {
    /**
     * @return jméno příkazu
     */
    String getName();

    /**
     * @return popis příkazu a jeho parametrů
     */
    String getDescription();

    /**
     * Samotná implementace příkazu
     * @param game hra, nad kterou se má příkaz vykonat
     * @param arguments argumenty příkazu předané přes terminál
     */
    void execute(@NotNull DungeonGame game, String... arguments);
}
