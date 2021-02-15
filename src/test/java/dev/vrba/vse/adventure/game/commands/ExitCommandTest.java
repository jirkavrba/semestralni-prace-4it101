package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.GameTest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExitCommandTest extends GameTest {

    @Test
    public void testCommandHasExpectedName() {
        Command command = new ExitCommand();
        assertEquals("konec", command.getName());
    }

    @Test
    public void testCanBeInvoked() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new ExitCommand();

        assertTrue(game.isPlaying());

        assertDoesNotThrow(() -> command.execute(game));

        assertFalse(game.isPlaying());
    }
}
