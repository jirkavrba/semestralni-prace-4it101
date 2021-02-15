package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;

import java.util.Set;

/**
 * Rozhraní představující herní plán, který obsahuje všechny místnosti a udržuje informaci o tom, kde se momontálně nachází hráč
 */
public interface GamePlan {
    /**
     * @return množina (kolekce) všech místností ve hře
     */
    Set<Room> getRooms();

    /**
     * @return místnost, ve které se právě nachází hráč
     */
    Room getCurrentRoom();

    /**
     * Přesune hráče do místnosti, předané v parametru
     * @param room místnost, do které se má hráč přesunout
     */
    void setCurrentRoom(@NotNull Room room);
}
