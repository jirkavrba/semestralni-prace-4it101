package dev.vrba.vse.adventure.game;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.entity.LivingEntity;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.*;
import dev.vrba.vse.adventure.game.plan.*;
import dev.vrba.vse.adventure.game.ui.CommandPrompt;

public class DungeonGame {

    private boolean playing = false;

    private final Player player;

    private final GamePlan gamePlan;

    private CommandPrompt prompt;

    private Trophy trophy;

    public DungeonGame(@NotNull Player player, @NotNull GamePlan gamePlan) {
        this.player = player;
        this.gamePlan = gamePlan;
        this.prompt = new CommandPrompt(this);
    }

    public void performGameTick() {
        if (!this.isPlaying()) {
            return;
        }

        Player player = this.getPlayer();
        Room room = this.getGamePlan().getCurrentRoom();

        if (player.hasEquippedItem() && player.getEquippedItem() == this.trophy) {
            this.playing = false;
            this.prompt.showWinNote();
        }

        if (!room.getEnemies().isEmpty()) {
            for (LivingEntity enemy : room.getEnemies()) {
                this.prompt.showDamagedByEnemyNote(enemy);
                player.damage(enemy.getStats().getStrength());
            }
        }

        if (player.isDead()) {
            this.playing = false;
            this.prompt.showDeadNote();
        }
    }

    public void start() {
        playing = true;
        prompt.startInputLoop();
    }

    public void stop() {
        playing = false;
        prompt.showExitNote();
    }

    // TODO: Split this method into several smaller ones

    public Player getPlayer() {
        return this.player;
    }
    public boolean isPlaying() {
        return this.playing;
    }

    public GamePlan getGamePlan() {
        return this.gamePlan;
    }

    public CommandPrompt getCommandPrompt() {
        return this.prompt;
    }

    public void setCommandPrompt(@NotNull CommandPrompt prompt) {
        this.prompt = prompt;
    }

    public void setTrophy(@NotNull Trophy trophy) {
        this.trophy = trophy;
    }

}
