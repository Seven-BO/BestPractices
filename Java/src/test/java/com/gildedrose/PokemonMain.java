package com.gildedrose;

import com.gildedrose.iterator.Pokedex;
import com.gildedrose.iterator.Pokemon;
import com.gildedrose.iterator.PokemonIterator;

public class PokemonMain {
    public static void main(String[] args) {
        Pokedex pokedex = new Pokedex();
        pokedex.addPokemon(new Pokemon("Pikachu", "Electric", 80));
        pokedex.addPokemon(new Pokemon("Charizard", "Fire", 95));
        pokedex.addPokemon(new Pokemon("Squirtle", "Water", 70));
        pokedex.addPokemon(new Pokemon("Bulbasaur", "Plant", 75));
        pokedex.addPokemon(new Pokemon("Jolteon", "Electric", 85));

        System.out.println("Exploring 'Electric' type Pokemon with a minimum power of 80:");
        PokemonIterator iterador = pokedex.createIterador("Electric", 80);
        while (iterador.hasNext()) {
            System.out.println("You found " + iterador.next());
        }
    }
}
