package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;

public class ExitCommand implements Command {
    @Override
    public String getName() {
        return "konec";
    }

    @Override
    public void execute(DungeonGame game, String... arguments) {
        game.stop();
    }
}
