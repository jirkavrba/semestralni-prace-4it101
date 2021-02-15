package dev.vrba.vse.adventure.game.entity;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.GameTest;
import dev.vrba.vse.adventure.game.entity.items.EquipableItem;
import dev.vrba.vse.adventure.game.entity.items.Sword;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest extends GameTest {
    @Test
    public void testPlayerReturnsTheExpectedName() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Player player = game.getPlayer();

        assertEquals("player", player.getName());
    }

    @Test
    public void testPlayerCanEquipThings() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Player player = game.getPlayer();

        assertFalse(player.hasEquippedItem());
        assertNull(player.getEquippedItem());

        EquipableItem item = new Sword(100);

        assertDoesNotThrow(() -> player.equip(item));

        assertTrue(player.hasEquippedItem());
        assertNotNull(player.getEquippedItem());
        assertEquals(item, player.getEquippedItem());
    }
}
