package dev.vrba.vse.adventure.game.ui;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.commands.Command;
import dev.vrba.vse.adventure.game.exceptions.CommandNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CommandPrompt {

    private DungeonGame game;

    private final Command[] commands = new Command[]{

    };

    public CommandPrompt(@NotNull DungeonGame game) {
        this.game = game;
    }

    public void startInputLoop() {
        while (game.isPlaying()) {
            try {
                handleNextCommand();
            }
            catch (IOException exception) {
                System.out.println("Došlo k chybě I/O.");
            }
            catch (CommandNotFoundException exception) {
                System.out.println("Příkaz nenalezen.");
            }
        }
    }

    private void handleNextCommand() throws CommandNotFoundException, IOException {
        System.out.print("~> ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        String[] parts = line.split(" ");

        String name = parts[0];
        Command command = Arrays.stream(commands)
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(CommandNotFoundException::new);

        game = command.execute(game, Arrays.copyOfRange(parts, 1, parts.length));
    }
}
