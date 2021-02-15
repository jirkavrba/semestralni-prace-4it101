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

    @SuppressWarnings("DuplicatedCode")
    private GamePlan createDefaultGamePlan() {
        Set<Room> rooms = new HashSet<>();

        Key redKey = new Key(Key.KeyColor.RED);
        Key blueKey = new Key(Key.KeyColor.BLUE);
        Key greenKey = new Key(Key.KeyColor.GREEN);

        BasicRoom entry = new BasicRoom("vstup");
        BasicRoom room1 = new BasicRoom("chodba");
        BasicRoom room2 = new BasicRoom("laboratoř");
        BasicRoom room3 = new BasicRoom("schodiště");
        BasicRoom room4 = new BasicRoom("skrýš");
        BasicRoom room5 = new BasicRoom("hala");
        BasicRoom room6 = new BasicRoom("skladiště");
        BasicRoom room7 = new BasicRoom("rozcestí");
        BasicRoom room8 = new BasicRoom("cela");
        BasicRoom boss = new BasicRoom("doupě big chunguse");
        BasicRoom exit = new BasicRoom("východ");

        entry.addExit(new BasicRoomExit(room1));

        room1.addExit(new BasicRoomExit(entry));
        room1.addExit(new BasicRoomExit(room2));
        room1.addExit(new BasicRoomExit(room3));

        room2.addExit(new BasicRoomExit(room1));
        room2.addItem(redKey);

        room3.addExit(new BasicRoomExit(room1));
        room3.addExit(new LockedRoomExit(room4, redKey));
        room3.addExit(new LockedRoomExit(room5, greenKey));

        room4.addExit(new BasicRoomExit(room3));
        room4.addItem(greenKey);

        room5.addExit(new BasicRoomExit(room3));
        room5.addExit(new BasicRoomExit(room6));
        room5.addExit(new BasicRoomExit(room7));

        room6.addExit(new BasicRoomExit(room5));

        room7.addExit(new BasicRoomExit(room5));
        room7.addExit(new BasicRoomExit(room8));
        room7.addExit(new BasicRoomExit(boss));
        room7.addExit(new LockedRoomExit(exit, blueKey));

        room8.addExit(new BasicRoomExit(room7));

        boss.addExit(new BasicRoomExit(room7));
        boss.addItem(blueKey);

        exit.addExit(new BasicRoomExit(room7));

        rooms.add(entry);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);
        rooms.add(room6);
        rooms.add(room7);
        rooms.add(room8);
        rooms.add(boss);
        rooms.add(exit);

        return new DungeonGamePlan(rooms, entry);
    }
}
