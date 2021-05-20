package dev.vrba.vse.adventure.game.ui;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.commands.Command;
import dev.vrba.vse.adventure.game.commands.EquipCommand;
import dev.vrba.vse.adventure.game.entity.LivingEntity;
import dev.vrba.vse.adventure.game.exceptions.CommandNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Příkazový řádek, který vyhodnocuje vstup od uživatele a následně mu vrací výpis reprezentující stav hry
 */
public class TerminalCommandPrompt implements CommandPrompt {

    private final DungeonGame game;

    private final TerminalGameOutput output;

    private Command[] commands = new Command[]{};

    private Set<Command> unusedCommands = new HashSet<>();

    public TerminalCommandPrompt(@NotNull DungeonGame game) {
        this.game = game;
        this.output = new TerminalGameOutput(game);
    }

    @Override
    public void registerCommands(Command... commands) {
        this.commands = commands;
        this.unusedCommands.addAll(Arrays.asList(commands));
    }

    @Override
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

    @Override
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

        unusedCommands.remove(command);

        this.printUnusedCommands();
    }

    @Override
    public void showIntroBanner() {
        System.out.println(Color.RED + " _____     __  __     __   __     ______     ______     ______     __   __");
        System.out.println(Color.YELLOW + "/\\  __-.  /\\ \\/\\ \\   /\\ \"-.\\ \\   /\\  ___\\   /\\  ___\\   /\\  __ \\   /\\ \"-.\\ \\\\");
        System.out.println(Color.GREEN + "\\ \\ \\/\\ \\ \\ \\ \\_\\ \\  \\ \\ \\-.  \\  \\ \\ \\__ \\  \\ \\  __\\   \\ \\ \\/\\ \\  \\ \\ \\-.");
        System.out.println(Color.BLUE + " \\ \\____-  \\ \\_____\\  \\ \\_\\\\\"\\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\\\\"\\_\\");
        System.out.println(Color.PURPLE + "  \\/____/   \\/_____/   \\/_/ \\/_/   \\/_____/   \\/_____/   \\/_____/   \\/_/ \\/_/" + Color.RESET);
        System.out.println("      v1.1\n");
        System.out.println("---- Semestrální práce k předmětu 4IT101 - Programování v Javě ----");
        System.out.println("---- Jiří Vrba, LS 2020/2021 --------------------------------------");
        System.out.println("\n");

        System.out.println("Big chungus ukradl Thanosovi jeho rukavici a chce zničit polovinu populace.");
        System.out.println("Tvým úkolem je najít ho a za pomoci meme policie ho poslat do horny jail a zmocnit se rukavice.");
        System.out.println("(k vyhrání hry je potřeba vzít si rukavici pomocí příkazu " + Color.CYAN + new EquipCommand().getName() + Color.RESET + ")\n");

        this.printUnusedCommands();
    }

    @Override
    public void showExitNote() {
        this.printUnusedCommands();

        System.out.println("Na viděnou přístě...");
        System.out.println("Ukončuji hru.");
    }

    @Override
    public void showWinNote() {
        System.out.println("Vyhrál jsi.");
    }

    @Override
    public void showDamagedByEnemyNote(LivingEntity enemy) {
        System.out.println(Color.RED + enemy.getName() + " na tebe zaútočil a ubral ti " + enemy.getStats().getStrength() + " životů!" + Color.RESET);
    }

    @Override
    public void showKilledEnemy(LivingEntity enemy) {
        System.out.println(Color.YELLOW + "bonknul jsi " + enemy.getName() + " a meme policie ho odvedla do horny jail." + Color.RESET);
    }

    @Override
    public void showDeadNote() {
        System.out.println(
                "Podlehl jsi svým zraněním a zemřel jsi. \n" +
                        Color.CYAN + "Press F to pay respect." + Color.RESET
        );
    }

    private void printUnusedCommands() {
       System.out.println("----------- Nepoužité příkazy -----------");
       System.out.println(
               this.unusedCommands.stream()
                    .map(command -> Color.BLUE + command.getName() + Color.RESET)
                    .collect(Collectors.joining(", "))
       );
       System.out.println("\n");
    }
}
