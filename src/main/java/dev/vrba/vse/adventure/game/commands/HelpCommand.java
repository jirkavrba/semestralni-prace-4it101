package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;

public class HelpCommand implements Command {

    @Override
    public String getName() {
        return "napoveda";
    }

    @Override
    public DungeonGame execute(DungeonGame game, String... arguments) {
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec venenatis feugiat consequat. Integer varius posuere accumsan. Pellentesque lacinia odio at dolor commodo vestibulum. Suspendisse orci nunc, consectetur id metus sed, consequat consequat mauris. Aenean pretium lorem sit amet tellus tristique convallis. Curabitur scelerisque ligula suscipit, vulputate nulla id, dictum turpis. Aliquam congue aliquam fermentum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla in consectetur lacus. Vestibulum ac magna vitae enim dictum tempor at ut ex. Sed feugiat sed justo vitae vulputate. Cras tincidunt gravida magna vel accumsan. Ut sed erat turpis. Morbi vitae dui lobortis, viverra dui sed, imperdiet enim.");
        return game;
    }
}
