package dev.vrba.vse.adventure.game;

import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.items.Key;
import dev.vrba.vse.adventure.game.items.PickableItem;
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

        BasicRoom entry = new BasicRoom("vstup");
        BasicRoom hallway = new BasicRoom("chodba");
        BasicRoom locked = new BasicRoom("tajná místnost");

        Key redKey = new Key(Key.KeyColor.RED);
        Key blueKey = new Key(Key.KeyColor.BLUE);
        Key greenKey = new Key(Key.KeyColor.GREEN);

        entry.addExit(new BasicRoomExit(hallway));
        hallway.addExit(new BasicRoomExit(entry));

        entry.addExit(new LockedRoomExit(locked, greenKey));

        PickableItem wayTooHeavy = new PickableItem() {
            @Override
            public int getWeight() {
                return 110;
            }

            @Override
            public String getName() {
                return "Way too heavy item";
            }
        };

        rooms.add(entry);
        rooms.add(hallway);

        entry.addItem(greenKey);
        hallway.addItem(redKey);
        hallway.addItem(wayTooHeavy);

        return new DungeonGamePlan(rooms, entry);
    }
}
