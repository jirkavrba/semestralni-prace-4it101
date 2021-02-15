package dev.vrba.vse.adventure.game.ui;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.commands.*;
import dev.vrba.vse.adventure.game.entity.LivingEntity;
import dev.vrba.vse.adventure.game.exceptions.CommandNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CommandPrompt {

    private final DungeonGame game;

    private final GameOutput output;

    private final Command[] commands = new Command[]{
        new BonkCommand(),
        new DropCommand(),
        new ExitCommand(),
        new EquipCommand(),
        new HelpCommand(),
        new MoveCommand(),
        new PickCommand(),
    };

    public CommandPrompt(@NotNull DungeonGame game) {
        this.game = game;
        this.output = new GameOutput(game);
    }

    public void startInputLoop() {
        while (game.isPlaying()) {
            try {
                printCurrentState();
                handleNextCommand();

                System.out.println("------------------------------------");

                game.performGameTick();
            }
            catch (IOException exception) {
                System.out.println(Color.RED + "Došlo k chybě I/O." + Color.RESET);
            }
            catch (CommandNotFoundException exception) {
                System.out.println(Color.RED + "Příkaz nenalezen!" + Color.RESET);
            }
            catch (IllegalArgumentException exception) {
                System.out.println(Color.RED + "Chybné argumenty příkazu:\n" + exception.getMessage() + Color.RESET);
            }
        }
    }

    private void printCurrentState() {
        String state = output.printCurrentState();
        System.out.println(state);
    }

    private void handleNextCommand() throws CommandNotFoundException, IOException {
        System.out.println("----------- zadej příkaz -----------");
        System.out.print("~> ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        String[] parts = line.split(" ");

        String name = parts[0];
        Command command = Arrays.stream(commands)
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(CommandNotFoundException::new);

        command.execute(game, Arrays.copyOfRange(parts, 1, parts.length));
    }

    public void showExitNote() {
        System.out.println("Na viděnou přístě...");
        System.out.println("Ukončuji hru.");
    }

    public void showWinNote() {
        System.out.println("You won lmao, IDK...");
    }

    public void showDamagedByEnemyNote(LivingEntity enemy) {
        System.out.println(Color.RED + enemy.getName() + " na tebe zaútočil a ubral ti " + enemy.getStats().getStrength() + " životů!" + Color.RESET);
    }

    public void showDeadNote() {
        System.out.println(
                "Podlehl jsi svým zraněním a zemřel jsi. \n" +
                Color.CYAN + "Press F to pay respect." + Color.RESET
        );
    }
}
