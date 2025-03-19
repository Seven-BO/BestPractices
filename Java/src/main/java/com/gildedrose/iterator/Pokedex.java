package com.gildedrose.iterator;

import java.util.ArrayList;
import java.util.List;

public class Pokedex {
    private List<Pokemon> pokemons = new ArrayList<>();

    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
    }

    public PokemonIterator createIterador(String type, int minimumPower) {
        return new PokemonExplorerIterator(pokemons, type, minimumPower);
    }
}
