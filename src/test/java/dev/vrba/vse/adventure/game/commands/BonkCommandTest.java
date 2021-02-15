package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.GameTest;
import dev.vrba.vse.adventure.game.entity.Enemy;
import dev.vrba.vse.adventure.game.entity.LivingEntityStats;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.plan.Room;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BonkCommandTest extends GameTest {

    @Test
    public void testCommandHasExpectedName() {
        BonkCommand command = new BonkCommand();
        assertEquals("bonkni", command.getName());
    }

    @Test
    public void testCannotInvokeBonkCommandWithoutArguments() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        BonkCommand command = new BonkCommand();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game));

        assertEquals(
                "Příkaz vyžaduje právě jeden argument, a to jméno nepřítele, kterého chceš bonknout.",
                exception.getMessage()
        );
    }

    @Test
    public void testCannotInvokeBonkCommandWithoutValidTarget() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        BonkCommand command = new BonkCommand();

        Enemy dummy = new Enemy("Dummy", new LivingEntityStats(100, 0));
        game.getGamePlan().getCurrentRoom().addEnemy(dummy);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game, "Shouldn't find this one"));

        assertEquals(
                "Nepřítel se jménem Shouldn't find this one nebyl v mísnosti s názvem vstup nalezen.",
                exception.getMessage()
        );
    }

    @Test
    public void testCanInvokeBonkCommandWithValidTarget() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        BonkCommand command = new BonkCommand();

        Enemy dummy = new Enemy("Dummy", new LivingEntityStats(100, 0));
        Player player = game.getPlayer();

        game.getGamePlan().getCurrentRoom().addEnemy(dummy);

        assertNull(player.getEquippedItem());
        assertEquals(10, player.getStats().getStrength());
        assertEquals(100, dummy.getStats().getHealth());

        assertDoesNotThrow(() -> command.execute(game, "Dummy"));

        assertEquals(90, dummy.getStats().getHealth());
    }

    @Test
    public void testUserCanKillEnemiesWithBonk() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        BonkCommand command = new BonkCommand();

        Room room = game.getGamePlan().getCurrentRoom();
        Enemy dummy = new Enemy("Dummy", new LivingEntityStats(100, 0));
        Player player = game.getPlayer();

        room.addEnemy(dummy);

        // Apes together stronk. Ooga booga
        player.getStats().addBoost(new LivingEntityStats(0, 90));

        assertNull(player.getEquippedItem());
        assertFalse(room.getEnemies().isEmpty());

        assertEquals(100, player.getStats().getStrength());
        assertEquals(100, dummy.getStats().getHealth());

        assertDoesNotThrow(() -> command.execute(game, "Dummy"));

        assertTrue(room.getEnemies().isEmpty());
    }
}