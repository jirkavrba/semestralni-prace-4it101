package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.GameTest;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.EquipableItem;
import dev.vrba.vse.adventure.game.entity.items.Key;
import dev.vrba.vse.adventure.game.entity.items.PickableItem;
import dev.vrba.vse.adventure.game.entity.items.Sword;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquipCommandTest extends GameTest {

    @Test
    public void testCommandHasExpectedName() {
        Command command = new EquipCommand();
        assertEquals("vezmi", command.getName());
    }

    @Test
    public void testCannotBeInvokedWithoutArguments() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new EquipCommand();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game));

        assertEquals(
                "Příkaz vyžaduje právě jeden argument, a to jméno věci, kterou má hráč vzít do ruky.",
                exception.getMessage()
        );
    }

    @Test
    public void testCannotBeInvokedWithInvalidTarget() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new EquipCommand();

        EquipableItem sword = new Sword(10);
        Player player = game.getPlayer();

        player.getBackpack().add(sword);

        assertTrue(player.getBackpack().containsItem(sword));
        assertFalse(player.hasEquippedItem());
        assertNull(player.getEquippedItem());

        assertEquals("meč", sword.getName());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game, "červený klíč"));

        assertEquals(
                "Věc s názvem červený klíč nebyla v batohu nalezena.",
                exception.getMessage()
        );
    }

    @Test
    public void testCannotBeInvokedWithNonEquipableItem() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new EquipCommand();

        PickableItem key = new Key(Key.KeyColor.RED);
        Player player = game.getPlayer();

        player.getBackpack().add(key);

        assertTrue(player.getBackpack().containsItem(key));
        assertFalse(player.hasEquippedItem());
        assertNull(player.getEquippedItem());

        assertEquals("červený klíč", key.getName());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game, "červený klíč"));

        assertEquals(
                "Věc s názvem červený klíč není možné vzít do ruky.",
                exception.getMessage()
        );
    }

    @Test
    public void testCanBeInvokedWithValidTarget() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new EquipCommand();

        EquipableItem sword = new Sword(420);
        Player player = game.getPlayer();

        player.getBackpack().add(sword);

        assertTrue(player.getBackpack().containsItem(sword));
        assertFalse(player.hasEquippedItem());
        assertNull(player.getEquippedItem());

        assertEquals("meč", sword.getName());
        assertEquals(10, player.getStats().getStrength());

        assertDoesNotThrow(() -> command.execute(game, "meč"));

        assertTrue(player.getBackpack().containsItem(sword));
        assertTrue(player.hasEquippedItem());
        assertEquals(sword, player.getEquippedItem());

        assertEquals(430, player.getStats().getStrength());
    }
}
