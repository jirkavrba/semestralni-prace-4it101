package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.GameTest;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.EquipableItem;
import dev.vrba.vse.adventure.game.entity.items.Key;
import dev.vrba.vse.adventure.game.entity.items.PickableItem;
import dev.vrba.vse.adventure.game.entity.items.Sword;
import dev.vrba.vse.adventure.game.plan.Room;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DropCommandTest extends GameTest {

    @Test
    public void testCommandHasExpectedName() {
        DropCommand command = new DropCommand();
        assertEquals("polož", command.getName());
    }

    @Test
    public void testCannotInvokeCommandWithoutArguments() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        DropCommand command = new DropCommand();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game));

        assertEquals(
                "Příkaz vyžaduje právě jeden argument, a to jméno věci, kterou chceš položit.",
                exception.getMessage()
        );
    }


    @Test
    public void testCannotInvokeCommandWithInvalidTarget() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        DropCommand command = new DropCommand();

        PickableItem key = new Key(Key.KeyColor.GREEN);
        Player player = game.getPlayer();

        player.getBackpack().add(key);

        assertTrue(player.getBackpack().containsItem(key));
        assertEquals("zelený klíč", key.getName());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game, "červený klíč"));

        assertEquals(
                "Věc s názvem červený klíč nebyla v batohu nalezena.",
                exception.getMessage()
        );
    }

    @Test
    public void testCanInvokeCommandWithValidTarget() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        DropCommand command = new DropCommand();

        PickableItem key = new Key(Key.KeyColor.GREEN);
        Player player = game.getPlayer();
        Room room = game.getGamePlan().getCurrentRoom();

        player.getBackpack().add(key);

        assertTrue(player.getBackpack().containsItem(key));
        assertFalse(room.getItems().contains(key));

        assertEquals("zelený klíč", key.getName());

        assertDoesNotThrow(() -> command.execute(game, "zelený klíč"));

        assertTrue(room.getItems().contains(key));
        assertFalse(player.getBackpack().containsItem(key));
    }

    @Test
    public void testItemIsDroppedWhenEquipped() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        DropCommand command = new DropCommand();

        EquipableItem sword = new Sword(20);
        Player player = game.getPlayer();

        Room room = game.getGamePlan().getCurrentRoom();

        player.getBackpack().add(sword);
        player.equip(sword);

        assertTrue(player.hasEquippedItem());
        assertEquals(sword, player.getEquippedItem());

        assertTrue(player.getBackpack().containsItem(sword));
        assertFalse(room.getItems().contains(sword));

        assertEquals("meč", sword.getName());

        assertDoesNotThrow(() -> command.execute(game, "meč"));

        assertTrue(room.getItems().contains(sword));
        assertFalse(player.getBackpack().containsItem(sword));

        assertFalse(player.hasEquippedItem());
        assertNull(player.getEquippedItem());
    }
}
