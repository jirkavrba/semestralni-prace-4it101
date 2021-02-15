package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.entity.LivingEntity;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.plan.Room;

public class BonkCommand implements Command {
    @Override
    public String getName() {
        return "bonkni";
    }

    @Override
    public void execute(DungeonGame game, String... arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Příkaz vyžaduje právě jeden argument, a to jméno nepřítele, kterého chceš bonknout.");
        }

        Player player = game.getPlayer();
        Room room = game.getGamePlan().getCurrentRoom();

        String name = String.join(" ", arguments);

        LivingEntity enemy = room.getEnemies()
                .stream()
                .filter(current -> current.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nepřítel se jménem " + name + " nebyl v mísnosti s názvem " + room.getName() + " nalezen."));

        enemy.damage(player.getStats().getStrength());

        if (enemy.isDead()) {
            // TODO: Enemies might drop loot?
            room.removeEnemy(enemy); // F
            game.getCommandPrompt().showKilledEnemy(enemy);
        }

    }
}
