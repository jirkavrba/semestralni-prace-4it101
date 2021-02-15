package dev.vrba.vse.adventure.game;

import dev.vrba.vse.adventure.game.commands.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaythroughTest extends GameTest {
    @Test
    public void testGameCanBeWon() {
        DungeonGame game = createGameWithMockedCommandPrompt();

        assertTrue(game.isPlaying());

        new MoveCommand().execute(game, "chodba");
        game.performGameTick();
        new MoveCommand().execute(game, "schodiště");
        game.performGameTick();

        new PickCommand().execute(game, "meč");
        game.performGameTick();
        new EquipCommand().execute(game, "meč");
        game.performGameTick();

        new MoveCommand().execute(game, "chodba");
        game.performGameTick();
        new MoveCommand().execute(game, "laboratoř");
        game.performGameTick();

        new BonkCommand().execute(game, "Swole doge");
        game.performGameTick();

        new PickCommand().execute(game, "červený klíč");
        game.performGameTick();

        new MoveCommand().execute(game, "chodba");
        game.performGameTick();
        new MoveCommand().execute(game, "schodiště");
        game.performGameTick();
        new MoveCommand().execute(game, "skrýš");
        game.performGameTick();

        new BonkCommand().execute(game, "Uganda knuckle");
        game.performGameTick();

        new PickCommand().execute(game, "zelený klíč");
        game.performGameTick();

        new MoveCommand().execute(game, "schodiště");
        game.performGameTick();
        new MoveCommand().execute(game, "hala");
        game.performGameTick();
        new MoveCommand().execute(game, "skladiště");
        game.performGameTick();

        new PickCommand().execute(game, "lektvar života");
        game.performGameTick();
        new ConsumeCommand().execute(game, "lektvar života");
        game.performGameTick();

        new MoveCommand().execute(game, "hala");
        game.performGameTick();
        new MoveCommand().execute(game, "rozcestí");
        game.performGameTick();
        new MoveCommand().execute(game, "cela");
        game.performGameTick();

        new PickCommand().execute(game, "lektvar síly");
        game.performGameTick();
        new ConsumeCommand().execute(game, "lektvar síly");
        game.performGameTick();

        new MoveCommand().execute(game, "rozcestí");
        game.performGameTick();
        new MoveCommand().execute(game, "doupě big chunguse");
        game.performGameTick();

        new BonkCommand().execute(game, "Big chungus");
        game.performGameTick();
        new BonkCommand().execute(game, "Big chungus");
        game.performGameTick();

        new PickCommand().execute(game, "modrý klíč");
        game.performGameTick();
        new PickCommand().execute(game, "lektvar síly");
        game.performGameTick();
        new ConsumeCommand().execute(game, "lektvar síly");
        game.performGameTick();

        new MoveCommand().execute(game, "rozcestí");
        game.performGameTick();
        new MoveCommand().execute(game, "východ");
        game.performGameTick();

        new PickCommand().execute(game, "Thanosova rukavice");
        game.performGameTick();
        new EquipCommand().execute(game, "Thanosova rukavice");
        game.performGameTick();

        game.performGameTick();

        // Tada... 🎉

        assertFalse(game.isPlaying());
    }
}
