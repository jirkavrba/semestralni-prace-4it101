package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.GameTest;
import dev.vrba.vse.adventure.game.entity.Enemy;
import dev.vrba.vse.adventure.game.entity.LivingEntityStats;
import dev.vrba.vse.adventure.game.plan.*;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MoveCommandTest extends GameTest {

    @Test
    public void testCommandHasExpectedName() {
        Command command = new MoveCommand();
        assertEquals("jdi", command.getName());
    }

    @Test
    public void testCannotBeInvokedWithoutArguments() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new MoveCommand();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game));

        assertEquals(
                "Příkaz vyžaduje právě jeden argument, a to jméno cílové místnosti.",
                exception.getMessage()
        );
    }

    @Test
    public void testCannotBeInvokedWithInvalidArgument() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new MoveCommand();
        Room room = game.getGamePlan().getCurrentRoom();

        Room adjacent = new BasicRoom("test");
        RoomExit exit = new BasicRoomExit(adjacent);

        room.addExit(exit);

        assertTrue(room.getExits().contains(exit));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game, "notTest"));

        assertEquals(
                "Východ do místnosti notTest nenalezen!",
                exception.getMessage()
        );
    }

    @Test
    public void testCannotRunFromRoomWithEnemies() {
        DungeonGame game = createGameWithMockedCommandPrompt();

        Command command = new MoveCommand();
        GamePlan plan = game.getGamePlan();

        Room room = plan.getCurrentRoom();
        Room hallway = plan.getRooms()
                .stream()
                .filter(current -> current.getName().equals("chodba"))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

        Enemy dummy = new Enemy("Dummy", new LivingEntityStats(100, 0));
        hallway.addEnemy(dummy);

        assertDoesNotThrow(() -> command.execute(game, "chodba"));

        assertEquals(hallway, game.getGamePlan().getCurrentRoom());
        assertNotEquals(room, game.getGamePlan().getCurrentRoom());

        assertFalse(hallway.getEnemies().isEmpty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(game, "vstup"));

        assertEquals(
                "Nemůžeš utéct z místnosti, kde jsou nepřátelé.",
                exception.getMessage()
        );
    }

    @Test
    public void testCanBeInvokedWithValidArgument() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        Command command = new MoveCommand();
        GamePlan plan = game.getGamePlan();
        Room room = plan.getCurrentRoom();

        assertDoesNotThrow(() -> command.execute(game, "chodba"));

        assertNotEquals(room, game.getGamePlan().getCurrentRoom());
    }
}
