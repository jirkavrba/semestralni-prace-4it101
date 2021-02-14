package dev.vrba.vse.adventure.game;

import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.plan.BasicRoom;
import dev.vrba.vse.adventure.game.plan.DungeonGamePlan;
import dev.vrba.vse.adventure.game.plan.GamePlan;
import dev.vrba.vse.adventure.game.plan.Room;
import dev.vrba.vse.adventure.game.items.Backpack;

import java.util.HashSet;
import java.util.Set;

public class DungeonGame {

    private Player player;

    private GamePlan gamePlan;

    public void start() {
        this.player = createDefaultPlayer();
        this.gamePlan = createDefaultGamePlan();
    }

    public Player getPlayer() {
        return this.player;
    }

    public GamePlan getGamePlan() {
        return this.gamePlan;
    }

    private Player createDefaultPlayer() {
        Backpack backpack = new Backpack(100);
        return new Player(backpack);
    }

    private GamePlan createDefaultGamePlan() {
        Set<Room> rooms = new HashSet<>();
        BasicRoom entry = new BasicRoom("Entrance");

        rooms.add(entry);

        return new DungeonGamePlan(rooms, entry);
    }
}
