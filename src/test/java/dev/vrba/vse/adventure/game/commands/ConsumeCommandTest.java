package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.GameTest;
import dev.vrba.vse.adventure.game.entity.LivingEntityStats;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.Potion;
import dev.vrba.vse.adventure.game.entity.items.Sword;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumeCommandTest extends GameTest {

    @Test
    public void cannotInvokeCommandWithoutArguments() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        ConsumeCommand command = new ConsumeCommand();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game));

        assertEquals(
                "Příkaz vyžaduje právě jeden argument, a to jméno předmětu, který chceš vypít.",
                exception.getMessage()
        );
    }

    @Test
    public void cannotInvokeCommandWithoutValidTarget() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        ConsumeCommand command = new ConsumeCommand();

        Potion dummyPotion = new Potion("dummy", new LivingEntityStats(10, 20), 1);
        Player player = game.getPlayer();

        player.getBackpack().add(dummyPotion);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game, "Shouldn't find this one"));

        assertEquals(
                "Věc s názvem Shouldn't find this one nebyla v batohu nalezena.",
                exception.getMessage()
        );
    }

    @Test
    public void canInvokeCommandWithValidTarget() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        ConsumeCommand command = new ConsumeCommand();

        Potion dummyPotion = new Potion("dummy", new LivingEntityStats(10, 20), 1);
        Player player = game.getPlayer();

        player.getBackpack().add(dummyPotion);

        assertEquals(50, player.getStats().getHealth());
        assertEquals(10, player.getStats().getStrength());

        assertDoesNotThrow(() -> command.execute(game, "dummy"));

        assertEquals(60, player.getStats().getHealth());
        assertEquals(30, player.getStats().getStrength());
    }

    @Test
    public void cannotInvokeWithTargetThatIsNotConsumable() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        ConsumeCommand command = new ConsumeCommand();

        Sword dummy = new Sword(10);
        Player player = game.getPlayer();

        player.getBackpack().add(dummy);

        assertEquals(50, player.getStats().getHealth());
        assertEquals(10, player.getStats().getStrength());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game, "meč"));

        assertEquals(
                "Věc s názvem meč není možné vypít.",
                exception.getMessage()
        );

        assertEquals(50, player.getStats().getHealth());
        assertEquals(10, player.getStats().getStrength());
    }
}
