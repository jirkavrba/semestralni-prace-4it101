package dev.vrba.vse.adventure.game;

import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.items.Key;
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

    public void stop() {
        playing = false;
        prompt.showExitNote();
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

        BasicRoom entry = new BasicRoom("Vstup");
        BasicRoom hallway = new BasicRoom("Chodba");

        entry.addExit(new BasicRoomExit(hallway));

        rooms.add(entry);
        rooms.add(hallway);

        Key redKey = new Key(Key.KeyColor.RED);
        Key blueKey = new Key(Key.KeyColor.BLUE);
        Key greenKey = new Key(Key.KeyColor.GREEN);

        entry.addItem(greenKey);
        hallway.addItem(redKey);

        return new DungeonGamePlan(rooms, entry);
    }
}
