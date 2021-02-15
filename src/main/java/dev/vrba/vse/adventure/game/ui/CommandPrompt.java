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

/**
 * Příkazový řádek, který vyhodnocuje vstup od uživatele a následně mu vrací výpis reprezentující stav hry
 */
public class CommandPrompt {

    private final DungeonGame game;

    private final GameOutput output;

    private final Command[] commands = new Command[]{
            new BonkCommand(),
            new ConsumeCommand(),
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

    /**
     * Začne ve smyčce přijímat příkazy od uživatele a vyhodnocovat je
     */
    public void startInputLoop() {
        this.showIntroBanner();

        while (game.isPlaying()) {
            try {
                printCurrentState();
                handleNextCommand();

                System.out.println("------------------------------------");

                game.performGameTick();
            }
            catch (IOException exception) {
                System.out.println(Color.RED + "Došlo k chybě I/O." + Color.RESET + "\n");
            }
            catch (CommandNotFoundException exception) {
                System.out.println(Color.RED + "Příkaz nenalezen!" + Color.RESET);
                System.out.println("Pro zobrazení nápovědy použij příkaz " + Color.CYAN + "nápověda" + Color.RESET + "\n");
            }
            catch (IllegalArgumentException exception) {
                System.out.println(Color.RED + "Chybné argumenty příkazu:\n" + exception.getMessage() + Color.RESET + "\n");
            }
        }
    }

    /**
     * @return všechny zaregistrované příkazy
     */
    public Command[] getCommands() {
        return this.commands;
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

    /**
     * Zobrazí logo při zapnutí hry
     */
    public void showIntroBanner() {
        System.out.println(Color.RED + " _____     __  __     __   __     ______     ______     ______     __   __");
        System.out.println(Color.YELLOW + "/\\  __-.  /\\ \\/\\ \\   /\\ \"-.\\ \\   /\\  ___\\   /\\  ___\\   /\\  __ \\   /\\ \"-.\\ \\\\");
        System.out.println(Color.GREEN + "\\ \\ \\/\\ \\ \\ \\ \\_\\ \\  \\ \\ \\-.  \\  \\ \\ \\__ \\  \\ \\  __\\   \\ \\ \\/\\ \\  \\ \\ \\-.");
        System.out.println(Color.BLUE + " \\ \\____-  \\ \\_____\\  \\ \\_\\\\\"\\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\\\\"\\_\\");
        System.out.println(Color.PURPLE + "  \\/____/   \\/_____/   \\/_/ \\/_/   \\/_____/   \\/_____/   \\/_____/   \\/_/ \\/_/" + Color.RESET);
        System.out.println("\n");
        System.out.println("---- Semestrální práce k předmětu 4IT101 - Programování v Javě ----");
        System.out.println("---- Jiří Vrba, LS 2020/2021 --------------------------------------");
        System.out.println("\n");

        System.out.println("Big chungus ukradl Thanosovi jeho rukavici a chce zničit polovinu populace.");
        System.out.println("Tvým úkolem je najít ho a za pomoci meme policie ho poslat do horny jail a zmocnit se rukavice.");
        System.out.println("(k vyhrání hry je potřeba vzít si rukavice pomocí příkazu " + Color.CYAN + new EquipCommand().getName() + Color.RESET + ")\n");
    }

    /**
     * Zobrazí zprávu při ukončení hry
     */
    public void showExitNote() {
        System.out.println("Na viděnou přístě...");
        System.out.println("Ukončuji hru.");
    }

    /**
     * Zobrazí zprávu, když uživatel vyhraje hru
     */
    public void showWinNote() {
        System.out.println("Vyhrál jsi.");
    }

    /**
     * Zobrazí zprávu, pokud byl hráč zraněn některým z nepřátelů v místnosti
     * @param enemy nepřítel, který zranění způsobil
     */
    public void showDamagedByEnemyNote(LivingEntity enemy) {
        System.out.println(Color.RED + enemy.getName() + " na tebe zaútočil a ubral ti " + enemy.getStats().getStrength() + " životů!" + Color.RESET);
    }

    /**
     * Zobrazí zprávu, že uživatel zabil nepřítele
     * @param enemy nepřítel, kterého hráč zabil
     */
    public void showKilledEnemy(LivingEntity enemy) {
        System.out.println(Color.YELLOW + "bonknul jsi " + enemy.getName() + " a meme policie ho odvedla do horny jail." + Color.RESET);
    }

    /**
     * Zobrazí zprávu, pokud hráč zemře
     */
    public void showDeadNote() {
        System.out.println(
                "Podlehl jsi svým zraněním a zemřel jsi. \n" +
                        Color.CYAN + "Press F to pay respect." + Color.RESET
        );
    }
}
