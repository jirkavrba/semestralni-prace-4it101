package dev.vrba.vse.adventure;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.entity.Enemy;
import dev.vrba.vse.adventure.game.entity.LivingEntityStats;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.*;
import dev.vrba.vse.adventure.game.plan.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Třída zastřešující vytvoření, připravení a následného spuštění hry
 */
public class AdventureGame {

    /**
     * Vstupní metoda celého programu, je zavolána po spuštění aplikace operačním systémem
     * @param args argumenty konzolové aplikace
     */
    public static void main(String[] args) {
        Trophy trophy = createDefaultTrophy();
        Player player = createDefaultPlayer();
        GamePlan plan = createDefaultGamePlan(trophy);

        DungeonGame game = new DungeonGame(player, plan);

        game.setTrophy(trophy);
        game.start();
    }

    /**
     * Vytvoří výchozí cílový předmět hry
     * @return předmět, který se následně nastaví jako výchozí cílový předmět
     */
    public static Trophy createDefaultTrophy() {
        return new Trophy("Thanosova rukavice", 10);
    }

    /**
     * Vytvoří výchozí instanci hráče, kterou začné hra používat
     */
    public static Player createDefaultPlayer() {
        Backpack backpack = new Backpack(100);
        LivingEntityStats stats = new LivingEntityStats(50, 10);

        return new Player(backpack, stats);
    }

    /**
     * Vytvoří výchozí herní plán, který začne hra používat
     * @param trophy cílový předmět, který musí hráč získat
     * @return vytvořená instance herního plánu
     */
    @SuppressWarnings("DuplicatedCode")
    public static GamePlan createDefaultGamePlan(@NotNull Trophy trophy) {
        Set<Room> rooms = new HashSet<>();

        Key redKey = new Key(Key.KeyColor.RED);
        Key blueKey = new Key(Key.KeyColor.BLUE);
        Key greenKey = new Key(Key.KeyColor.GREEN);

        Potion healthPotion = new Potion("lektvar života", new LivingEntityStats(50, 0), 10);
        Potion strengthPotion = new Potion("lektvar síly", new LivingEntityStats(10, 20), 10);

        Enemy orc = new Enemy("Uganda knuckle", new LivingEntityStats(15, 15));
        Enemy druid = new Enemy("Swole doge", new LivingEntityStats(20, 5));
        Enemy bigChungus = new Enemy("Big chungus", new LivingEntityStats(100, 10));

        Furniture chair = new Furniture("křeslo");
        Furniture bed = new Furniture("postel");
        Furniture statue = new Furniture("socha");

        BasicRoom entry = new BasicRoom("vstup");
        BasicRoom room1 = new BasicRoom("chodba");
        BasicRoom room2 = new BasicRoom("laboratoř");
        BasicRoom room3 = new BasicRoom("schodiště");
        BasicRoom room4 = new BasicRoom("skrýš");
        BasicRoom room5 = new BasicRoom("hala");
        BasicRoom room6 = new BasicRoom("skladiště");
        BasicRoom room7 = new BasicRoom("rozcestí");
        BasicRoom room8 = new BasicRoom("cela");
        BasicRoom boss = new BasicRoom("doupě big chunguse");
        BasicRoom exit = new BasicRoom("východ");

        entry.addExit(new BasicRoomExit(room1));

        room1.addExit(new BasicRoomExit(entry));
        room1.addExit(new BasicRoomExit(room2));
        room1.addExit(new BasicRoomExit(room3));
        room1.addItem(new Coins(30));

        room2.addExit(new BasicRoomExit(room1));
        room2.addItem(redKey);
        room2.addEnemy(druid);

        room3.addExit(new BasicRoomExit(room1));
        room3.addExit(new LockedRoomExit(room4, redKey));
        room3.addExit(new LockedRoomExit(room5, greenKey));
        room3.addItem(new Coins(20));
        room3.addItem(new Sword(40));

        room4.addExit(new BasicRoomExit(room3));
        room4.addItem(greenKey);
        room4.addEnemy(orc);

        room5.addExit(new BasicRoomExit(room3));
        room5.addExit(new BasicRoomExit(room6));
        room5.addExit(new BasicRoomExit(room7));
        room5.addItem(new Coins(30));
        room5.addItem(statue);

        room6.addExit(new BasicRoomExit(room5));
        room6.addItem(new Coins(20));
        room6.addItem(healthPotion);
        room6.addItem(chair);

        room7.addExit(new BasicRoomExit(room5));
        room7.addExit(new BasicRoomExit(room8));
        room7.addExit(new BasicRoomExit(boss));
        room7.addExit(new LockedRoomExit(exit, blueKey));
        room7.addItem(new Coins(10));

        room8.addExit(new BasicRoomExit(room7));
        room8.addItem(strengthPotion);
        room8.addItem(bed);

        boss.addExit(new BasicRoomExit(room7));
        boss.addItem(blueKey);
        boss.addItem(strengthPotion);
        boss.addItem(new Coins(40));
        boss.addEnemy(bigChungus);

        exit.addExit(new BasicRoomExit(room7));
        exit.addItem(trophy);

        rooms.add(entry);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);
        rooms.add(room6);
        rooms.add(room7);
        rooms.add(room8);
        rooms.add(boss);
        rooms.add(exit);

        return new DungeonGamePlan(rooms, entry);
    }
}
