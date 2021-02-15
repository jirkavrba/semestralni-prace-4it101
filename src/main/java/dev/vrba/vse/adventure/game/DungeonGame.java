package dev.vrba.vse.adventure.game;

import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.plan.*;
import dev.vrba.vse.adventure.game.items.Backpack;
import dev.vrba.vse.adventure.game.ui.CommandPrompt;

import java.util.HashSet;
import java.util.Set;

public class DungeonGame {

    private boolean playing = false;

    private final Player player;

    private final GamePlan gamePlan;

    private final CommandPrompt prompt;

    public DungeonGame() {
        this.player = createDefaultPlayer();
        this.gamePlan = createDefaultGamePlan();
        this.prompt = new CommandPrompt(this);
    }

    public void start() {
        playing = true;
        prompt.startInputLoop();
    }

    public Player getPlayer() {
        return this.player;
    }

    public GamePlan getGamePlan() {
        return this.gamePlan;
    }

    public boolean isPlaying() {
        return this.playing;
    }

    private Player createDefaultPlayer() {
        Backpack backpack = new Backpack(100);
        return new Player(backpack);
    }

    private GamePlan createDefaultGamePlan() {
        Set<Room> rooms = new HashSet<>();

        BasicRoom hallway = new BasicRoom("Chodba");
        BasicRoom entry = new BasicRoom("Vstup", new BasicRoomExit(hallway));

        rooms.add(entry);
        rooms.add(hallway);

        return new DungeonGamePlan(rooms, entry);
    }
}
