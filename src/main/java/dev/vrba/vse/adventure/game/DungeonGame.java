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

    /**
     * Metoda, která je volána po každém provedeném příkazu, probíhá v něm útok nepřátel a kontrola, jestli hráč vyhrál hru
     */
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

    /**
     * Spustí hru a přijímání příkazů od uživatele
     */
    public void start() {
        playing = true;
        prompt.startInputLoop();
    }

    /**
     * Zastaví hru a zobrazí zprávu o konci hry uživateli
     */
    public void stop() {
        playing = false;
        prompt.showExitNote();
    }

    /**
     * @return jestli je momentální hra aktivní
     */
    public boolean isPlaying() {
        return this.playing;
    }

    /**
     * @return instance hráče, kterou hra využívá
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * @return instance herního plánu, který hra využívá
     */
    public GamePlan getGamePlan() {
        return this.gamePlan;
    }

    /**
     * @return instance příkazového řádku, který hra využívá
     */
    public CommandPrompt getCommandPrompt() {
        return this.prompt;
    }

    /**
     * Nastaví novou instanci příkazového řádku, který má hra začít využívat
     * @param prompt příkazový řádek, který má hra začít využívat
     */
    public void setCommandPrompt(@NotNull CommandPrompt prompt) {
        this.prompt = prompt;
    }

    /**
     * Nastaví cílový předmět, po jehož vzatí do ruky, hráč vyhraje hru
     * @param trophy předmět, který se nastaví jako cílový předmět hry
     */
    public void setTrophy(@NotNull Trophy trophy) {
        this.trophy = trophy;
    }
}
