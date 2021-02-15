package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.GameTest;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.Coins;
import dev.vrba.vse.adventure.game.entity.items.Item;
import dev.vrba.vse.adventure.game.entity.items.Key;
import dev.vrba.vse.adventure.game.entity.items.PickableItem;
import dev.vrba.vse.adventure.game.plan.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PickCommandTest extends GameTest {

    @Test
    public void testCommandHasExpectedName() {
        Command command = new PickCommand();
        assertEquals("seber", command.getName());
    }

    @Test
    public void testCannotBeInvokedWithoutArguments() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new PickCommand();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game));

        assertEquals(
                "Příkaz vyžaduje právě jeden argument, a to jméno věci, kterou má hráč sebrat.",
                exception.getMessage()
        );
    }

    @Test
    public void testCannotBeInvokedWithInvalidArgument() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new PickCommand();

        Room room = game.getGamePlan().getCurrentRoom();
        PickableItem key = new Key(Key.KeyColor.RED);

        room.addItem(key);

        assertTrue(room.getItems().contains(key));
        assertEquals("červený klíč", key.getName());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game, "modrý klíč"));

        assertEquals(
                "Věc s názvem modrý klíč nebyla v aktuální místnosti nalezena.",
                exception.getMessage()
        );
    }

    @Test
    public void testCannotBeInvokedWithNonPickableItems() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new PickCommand();

        Room room = game.getGamePlan().getCurrentRoom();
        Item key = () -> "something non-pickable";

        room.addItem(key);

        assertTrue(room.getItems().contains(key));
        assertEquals("something non-pickable", key.getName());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game, "something non-pickable"));

        assertEquals(
                "Věc s názvem something non-pickable nelze sebrat.",
                exception.getMessage()
        );
    }

    @Test
    public void testCannotBeInvokedWithItemsThatAreTooHeavy() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new PickCommand();

        Player player = game.getPlayer();
        Room room = game.getGamePlan().getCurrentRoom();
        PickableItem heavy = new Coins(1000);

        room.addItem(heavy);

        assertTrue(room.getItems().contains(heavy));
        assertFalse(player.getBackpack().containsItem(heavy));

        assertEquals("1000 mincí", heavy.getName());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game, "1000 mincí"));

        assertEquals(
                "Věc s názvem 1000 mincí je příliš těžká a nevešla by se do batohu.",
                exception.getMessage()
        );
    }

    @Test
    public void testCanBeInvokedWithValidArgument() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new PickCommand();

        Player player = game.getPlayer();
        Room room = game.getGamePlan().getCurrentRoom();
        PickableItem key = new Key(Key.KeyColor.RED);

        room.addItem(key);

        assertTrue(room.getItems().contains(key));
        assertFalse(player.getBackpack().containsItem(key));

        assertEquals("červený klíč", key.getName());

        assertDoesNotThrow(() -> command.execute(game, "červený klíč"));

        assertFalse(room.getItems().contains(key));
        assertTrue(player.getBackpack().containsItem(key));
    }
}
