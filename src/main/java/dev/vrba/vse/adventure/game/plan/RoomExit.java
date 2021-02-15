package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.entity.Player;

public interface RoomExit {
    Room getTo();
    boolean canBeUsed(@NotNull Player player);
}
