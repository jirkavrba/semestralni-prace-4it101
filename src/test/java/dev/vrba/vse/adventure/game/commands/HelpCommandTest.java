package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.GameTest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HelpCommandTest extends GameTest {
    @Test
    public void testCommandHasExpectedName() {
        Command command = new HelpCommand();
        assertEquals("nápověda", command.getName());
    }

    @Test
    public void testCanBeInvokedWithoutArguments() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new HelpCommand();

        assertDoesNotThrow(() -> command.execute(game));
    }
}
