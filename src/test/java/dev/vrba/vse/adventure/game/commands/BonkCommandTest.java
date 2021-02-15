package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.GameTest;
import dev.vrba.vse.adventure.game.entity.Enemy;
import dev.vrba.vse.adventure.game.entity.LivingEntityStats;
import dev.vrba.vse.adventure.game.entity.Player;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BonkCommandTest extends GameTest {
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
}