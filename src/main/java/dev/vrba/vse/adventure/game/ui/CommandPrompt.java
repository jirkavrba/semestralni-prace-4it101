package dev.vrba.vse.adventure.game.ui;

import dev.vrba.vse.adventure.game.commands.Command;
import dev.vrba.vse.adventure.game.entity.LivingEntity;

/**
 * Společné rozhraní pro asbtraktní přikazový řádek
 */
public interface CommandPrompt {
    /**
     * Příkazový řádek začne ve smyčce přijímat příkazy od uživatele a vyhodnocovat je
     */
    void startInputLoop();

    /**
     * Zaregistruje příkazy, aby je následně mohl hráč volat
     * @param commands příkazy, které se mají zaregistrovat
     */
    void registerCommands(Command... commands);

    /**
     * @return všechny zaregistrované příkazy
     */
    Command[] getCommands();

    /**
     * Zobrazí logo při zapnutí hry
     */
    void showIntroBanner();

    /**
     * Zobrazí zprávu při ukončení hry
     */
    void showExitNote();

    /**
     * Zobrazí zprávu, když uživatel vyhraje hru
     */
    void showWinNote();

    /**
     * Zobrazí zprávu, pokud byl hráč zraněn některým z nepřátelů v místnosti
     * @param enemy nepřítel, který zranění způsobil
     */
    void showDamagedByEnemyNote(LivingEntity enemy);

    /**
     * Zobrazí zprávu, že uživatel zabil nepřítele
     * @param enemy nepřítel, kterého hráč zabil
     */
    void showKilledEnemy(LivingEntity enemy);

    /**
     * Zobrazí zprávu, pokud hráč zemře
     */
    void showDeadNote();
}
