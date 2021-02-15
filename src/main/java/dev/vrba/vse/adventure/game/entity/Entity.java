package dev.vrba.vse.adventure.game.entity;

/**
 * Rozhraní reprezentující obecnou herní entitu, to může být věc, hráč, nepřítel, lektvar...
 */
public interface Entity {
    /**
     * @return jméno věci, které se bude renderovat uživateli ve výpise
     */
    String getName();
}
