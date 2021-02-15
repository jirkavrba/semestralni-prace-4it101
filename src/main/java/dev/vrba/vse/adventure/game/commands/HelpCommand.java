package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.ui.Color;

/**
 * Příkaz, který vypíše nápovědu k programu
 */
public class HelpCommand implements Command {
    @Override
    public String getName() {
        return "nápověda";
    }

    @Override
    public String getDescription() {
        return "Poskytuje nápovědu k programu a ostatním příkazům.";
    }

    @Override
    public void execute(DungeonGame game, String... arguments) {
        // TODO: add description to each command
        Command[] commands = game.getCommandPrompt().getCommands();

        StringBuilder builder = new StringBuilder();

        builder
                .append(Color.YELLOW)
                .append("Dungeon game")
                .append(Color.RESET)
                .append("\n---------------\n\n");

        for (Command command : commands) {
            builder.append("Příkaz ")
                    .append(Color.CYAN)
                    .append(command.getName())
                    .append(Color.RESET)
                    .append(":\n - ")
                    .append(command.getDescription())
                    .append("\n\n");
        }

        System.out.println(builder.toString());
    }
}
