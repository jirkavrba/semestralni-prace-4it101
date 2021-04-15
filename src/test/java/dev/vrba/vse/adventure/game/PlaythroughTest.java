package dev.vrba.vse.adventure.game;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.commands.*;
import dev.vrba.vse.adventure.game.ui.Color;
import dev.vrba.vse.adventure.game.ui.TerminalGameOutput;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

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

    @Test
    public void testGameProducesCorrectOutput() {
        DungeonGame game = createGameWithMockedCommandPrompt();
        TerminalGameOutput output = new TerminalGameOutput(game);

        // Disable System.out.println output
        // This probably doesn't do well on Windows...
        try (var stream = new PrintStream("/dev/null")) {
            System.setOut(stream);
        }
        catch (FileNotFoundException exception) {
            // Exception can be safely ignored
        }

        assertEquals("""
                Váha věcí v batohu: 0/100
                Tvoje životy: 50
                Tvoje síla: 10
                Nacházíš se v místnosti vstup
                Východy, které je možné použít:
                 - chodba
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "chodba");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 0/100
                Tvoje životy: 50
                Tvoje síla: 10
                Nacházíš se v místnosti chodba
                Východy, které je možné použít:
                 - laboratoř
                 - vstup
                 - schodiště
                V místnosti se nachází následující věci:
                 - 30 mincí - váha: 30 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "schodiště");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 0/100
                Tvoje životy: 50
                Tvoje síla: 10
                Nacházíš se v místnosti schodiště
                Východy, které je možné použít:
                 - chodba
                Východy, které není možné použít:
                 - <Zamčený východ> - Tento je zamčen a může být odemčen pouze pomocí předmětu zelený klíč
                 - <Zamčený východ> - Tento je zamčen a může být odemčen pouze pomocí předmětu červený klíč
                V místnosti se nachází následující věci:
                 - 20 mincí - váha: 20 (tuto věc lze sebrat)
                 - meč - váha: 35 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new PickCommand().execute(game, "meč");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 35/100
                Věci v batohu: meč
                Tvoje životy: 50
                Tvoje síla: 10
                Nacházíš se v místnosti schodiště
                Východy, které je možné použít:
                 - chodba
                Východy, které není možné použít:
                 - <Zamčený východ> - Tento je zamčen a může být odemčen pouze pomocí předmětu zelený klíč
                 - <Zamčený východ> - Tento je zamčen a může být odemčen pouze pomocí předmětu červený klíč
                V místnosti se nachází následující věci:
                 - 20 mincí - váha: 20 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new EquipCommand().execute(game, "meč");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 35/100
                Věci v batohu: meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 50
                Tvoje síla: 50
                Nacházíš se v místnosti schodiště
                Východy, které je možné použít:
                 - chodba
                Východy, které není možné použít:
                 - <Zamčený východ> - Tento je zamčen a může být odemčen pouze pomocí předmětu zelený klíč
                 - <Zamčený východ> - Tento je zamčen a může být odemčen pouze pomocí předmětu červený klíč
                V místnosti se nachází následující věci:
                 - 20 mincí - váha: 20 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "chodba");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 35/100
                Věci v batohu: meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 50
                Tvoje síla: 50
                Nacházíš se v místnosti chodba
                Východy, které je možné použít:
                 - laboratoř
                 - vstup
                 - schodiště
                V místnosti se nachází následující věci:
                 - 30 mincí - váha: 30 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "laboratoř");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 35/100
                Věci v batohu: meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 45
                Tvoje síla: 50
                Nacházíš se v místnosti laboratoř
                V místnosti se nachází nepřátelé!
                 - Swole doge (životy: 20, síla: 5)
                Východy, které je možné použít:
                 - chodba
                V místnosti se nachází následující věci:
                 - červený klíč - váha: 1 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new BonkCommand().execute(game, "Swole doge");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 35/100
                Věci v batohu: meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 45
                Tvoje síla: 50
                Nacházíš se v místnosti laboratoř
                Východy, které je možné použít:
                 - chodba
                V místnosti se nachází následující věci:
                 - červený klíč - váha: 1 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new PickCommand().execute(game, "červený klíč");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 36/100
                Věci v batohu: červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 45
                Tvoje síla: 50
                Nacházíš se v místnosti laboratoř
                Východy, které je možné použít:
                 - chodba
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "chodba");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 36/100
                Věci v batohu: červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 45
                Tvoje síla: 50
                Nacházíš se v místnosti chodba
                Východy, které je možné použít:
                 - laboratoř
                 - vstup
                 - schodiště
                V místnosti se nachází následující věci:
                 - 30 mincí - váha: 30 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "schodiště");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 36/100
                Věci v batohu: červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 45
                Tvoje síla: 50
                Nacházíš se v místnosti schodiště
                Východy, které je možné použít:
                 - chodba
                 - skrýš
                Východy, které není možné použít:
                 - <Zamčený východ> - Tento je zamčen a může být odemčen pouze pomocí předmětu zelený klíč
                V místnosti se nachází následující věci:
                 - 20 mincí - váha: 20 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "skrýš");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 36/100
                Věci v batohu: červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 30
                Tvoje síla: 50
                Nacházíš se v místnosti skrýš
                V místnosti se nachází nepřátelé!
                 - Uganda knuckle (životy: 15, síla: 15)
                Východy, které je možné použít:
                 - schodiště
                V místnosti se nachází následující věci:
                 - zelený klíč - váha: 1 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new BonkCommand().execute(game, "Uganda knuckle");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 36/100
                Věci v batohu: červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 30
                Tvoje síla: 50
                Nacházíš se v místnosti skrýš
                Východy, které je možné použít:
                 - schodiště
                V místnosti se nachází následující věci:
                 - zelený klíč - váha: 1 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new PickCommand().execute(game, "zelený klíč");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 30
                Tvoje síla: 50
                Nacházíš se v místnosti skrýš
                Východy, které je možné použít:
                 - schodiště
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "schodiště");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 30
                Tvoje síla: 50
                Nacházíš se v místnosti schodiště
                Východy, které je možné použít:
                 - chodba
                 - hala
                 - skrýš
                V místnosti se nachází následující věci:
                 - 20 mincí - váha: 20 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "hala");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 30
                Tvoje síla: 50
                Nacházíš se v místnosti hala
                Východy, které je možné použít:
                 - rozcestí
                 - skladiště
                 - schodiště
                V místnosti se nachází následující věci:
                 - 30 mincí - váha: 30 (tuto věc lze sebrat)
                 - socha
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "skladiště");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 30
                Tvoje síla: 50
                Nacházíš se v místnosti skladiště
                Východy, které je možné použít:
                 - hala
                V místnosti se nachází následující věci:
                 - lektvar života - váha: 10 (tuto věc lze sebrat)
                 - 20 mincí - váha: 20 (tuto věc lze sebrat)
                 - křeslo
                """, removeColors(output.printCurrentState()));

        new PickCommand().execute(game, "lektvar života");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 47/100
                Věci v batohu: zelený klíč, lektvar života, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 30
                Tvoje síla: 50
                Nacházíš se v místnosti skladiště
                Východy, které je možné použít:
                 - hala
                V místnosti se nachází následující věci:
                 - 20 mincí - váha: 20 (tuto věc lze sebrat)
                 - křeslo
                """, removeColors(output.printCurrentState()));

        new ConsumeCommand().execute(game, "lektvar života");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 80
                Tvoje síla: 50
                Nacházíš se v místnosti skladiště
                Východy, které je možné použít:
                 - hala
                V místnosti se nachází následující věci:
                 - 20 mincí - váha: 20 (tuto věc lze sebrat)
                 - křeslo
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "hala");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 80
                Tvoje síla: 50
                Nacházíš se v místnosti hala
                Východy, které je možné použít:
                 - rozcestí
                 - skladiště
                 - schodiště
                V místnosti se nachází následující věci:
                 - 30 mincí - váha: 30 (tuto věc lze sebrat)
                 - socha
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "rozcestí");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 80
                Tvoje síla: 50
                Nacházíš se v místnosti rozcestí
                Východy, které je možné použít:
                 - cela
                 - hala
                 - doupě big chunguse
                Východy, které není možné použít:
                 - <Zamčený východ> - Tento je zamčen a může být odemčen pouze pomocí předmětu modrý klíč
                V místnosti se nachází následující věci:
                 - 10 mincí - váha: 10 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "cela");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 80
                Tvoje síla: 50
                Nacházíš se v místnosti cela
                Východy, které je možné použít:
                 - rozcestí
                V místnosti se nachází následující věci:
                 - lektvar síly - váha: 10 (tuto věc lze sebrat)
                 - postel
                """, removeColors(output.printCurrentState()));

        new PickCommand().execute(game, "lektvar síly");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 47/100
                Věci v batohu: zelený klíč, lektvar síly, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 80
                Tvoje síla: 50
                Nacházíš se v místnosti cela
                Východy, které je možné použít:
                 - rozcestí
                V místnosti se nachází následující věci:
                 - postel
                """, removeColors(output.printCurrentState()));

        new ConsumeCommand().execute(game, "lektvar síly");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 90
                Tvoje síla: 70
                Nacházíš se v místnosti cela
                Východy, které je možné použít:
                 - rozcestí
                V místnosti se nachází následující věci:
                 - postel
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "rozcestí");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 90
                Tvoje síla: 70
                Nacházíš se v místnosti rozcestí
                Východy, které je možné použít:
                 - cela
                 - hala
                 - doupě big chunguse
                Východy, které není možné použít:
                 - <Zamčený východ> - Tento je zamčen a může být odemčen pouze pomocí předmětu modrý klíč
                V místnosti se nachází následující věci:
                 - 10 mincí - váha: 10 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "doupě big chunguse");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 80
                Tvoje síla: 70
                Nacházíš se v místnosti doupě big chunguse
                V místnosti se nachází nepřátelé!
                 - Big chungus (životy: 100, síla: 10)
                Východy, které je možné použít:
                 - rozcestí
                V místnosti se nachází následující věci:
                 - 40 mincí - váha: 40 (tuto věc lze sebrat)
                 - lektvar síly - váha: 10 (tuto věc lze sebrat)
                 - modrý klíč - váha: 1 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new BonkCommand().execute(game, "Big chungus");
        game.performGameTick();
        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 70
                Tvoje síla: 70
                Nacházíš se v místnosti doupě big chunguse
                V místnosti se nachází nepřátelé!
                 - Big chungus (životy: 30, síla: 10)
                Východy, které je možné použít:
                 - rozcestí
                V místnosti se nachází následující věci:
                 - 40 mincí - váha: 40 (tuto věc lze sebrat)
                 - lektvar síly - váha: 10 (tuto věc lze sebrat)
                 - modrý klíč - váha: 1 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new BonkCommand().execute(game, "Big chungus");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 37/100
                Věci v batohu: zelený klíč, červený klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 70
                Tvoje síla: 70
                Nacházíš se v místnosti doupě big chunguse
                Východy, které je možné použít:
                 - rozcestí
                V místnosti se nachází následující věci:
                 - 40 mincí - váha: 40 (tuto věc lze sebrat)
                 - lektvar síly - váha: 10 (tuto věc lze sebrat)
                 - modrý klíč - váha: 1 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new PickCommand().execute(game, "modrý klíč");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 38/100
                Věci v batohu: zelený klíč, červený klíč, modrý klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 70
                Tvoje síla: 70
                Nacházíš se v místnosti doupě big chunguse
                Východy, které je možné použít:
                 - rozcestí
                V místnosti se nachází následující věci:
                 - 40 mincí - váha: 40 (tuto věc lze sebrat)
                 - lektvar síly - váha: 10 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new PickCommand().execute(game, "lektvar síly");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 48/100
                Věci v batohu: zelený klíč, lektvar síly, červený klíč, modrý klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 70
                Tvoje síla: 70
                Nacházíš se v místnosti doupě big chunguse
                Východy, které je možné použít:
                 - rozcestí
                V místnosti se nachází následující věci:
                 - 40 mincí - váha: 40 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new ConsumeCommand().execute(game, "lektvar síly");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 38/100
                Věci v batohu: zelený klíč, červený klíč, modrý klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 80
                Tvoje síla: 90
                Nacházíš se v místnosti doupě big chunguse
                Východy, které je možné použít:
                 - rozcestí
                V místnosti se nachází následující věci:
                 - 40 mincí - váha: 40 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "rozcestí");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 38/100
                Věci v batohu: zelený klíč, červený klíč, modrý klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 80
                Tvoje síla: 90
                Nacházíš se v místnosti rozcestí
                Východy, které je možné použít:
                 - cela
                 - hala
                 - východ
                 - doupě big chunguse
                V místnosti se nachází následující věci:
                 - 10 mincí - váha: 10 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new MoveCommand().execute(game, "východ");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 38/100
                Věci v batohu: zelený klíč, červený klíč, modrý klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 80
                Tvoje síla: 90
                Nacházíš se v místnosti východ
                Východy, které je možné použít:
                 - rozcestí
                V místnosti se nachází následující věci:
                 - Thanosova rukavice - váha: 10 (tuto věc lze sebrat)
                """, removeColors(output.printCurrentState()));

        new PickCommand().execute(game, "Thanosova rukavice");
        game.performGameTick();

        assertEquals("""
                Váha věcí v batohu: 48/100
                Věci v batohu: zelený klíč, Thanosova rukavice, červený klíč, modrý klíč, meč
                Momentálně držíš: meč (+0 životů, +40 síly)
                Tvoje životy: 80
                Tvoje síla: 90
                Nacházíš se v místnosti východ
                Východy, které je možné použít:
                 - rozcestí
                """, removeColors(output.printCurrentState()));

        new EquipCommand().execute(game, "Thanosova rukavice");
        game.performGameTick();

        assertEquals("Hra skončila.", removeColors(output.printCurrentState()));
    }

    private String removeColors(@NotNull String input) {
        return List.of(
                Color.RESET, Color.RED, Color.GREEN, Color.YELLOW,
                Color.BLUE, Color.PURPLE, Color.CYAN
        )
                .stream()
                .reduce(input, (accumulator, color) -> accumulator.replace(color, ""));
    }
}
