package com.gildedrose.iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PokemonExplorerIterator implements PokemonIterator {
    private List<Pokemon> filteredPokemons;
    private int currentPosition = 0;

    public PokemonExplorerIterator(List<Pokemon> pokemons, String filterType, int minimunPowerFilter) {
        this.filteredPokemons = new ArrayList<>();
        for (Pokemon pokemon : pokemons) {
            if ((filterType == null || pokemon.getType().equalsIgnoreCase(filterType)) &&
                pokemon.getPower() >= minimunPowerFilter) {
                this.filteredPokemons.add(pokemon);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return currentPosition < filteredPokemons.size();
    }

    @Override
    public Pokemon next() {
        if (!hasNext()) {
            throw new NoSuchElementException("There are no more Pokémon available with the current filters.");
        }
        return filteredPokemons.get(currentPosition++);
    }
}
