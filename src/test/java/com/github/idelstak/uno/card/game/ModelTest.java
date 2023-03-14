package com.github.idelstak.uno.card.game;

import com.github.idelstak.uno.card.game.Card.Colored;
import java.util.Arrays;
import java.util.Comparator;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class ModelTest {

    @Test
    public void a_new_deck_contains_76_numbered_cards() {
        var deck = new Deck();
        var numberedCards = deck.getNumberedCards();

        assertThat(numberedCards, hasSize(76));
    }

    @Test
    public void a_new_deck_contains_a_card_numbered_0_for_each_color_except_black() {
        var deck = new Deck();
        var numberCardsColorMap = deck.getNumberedCardsColorMap();
        var colorsList = Arrays.asList(Colored.values())
                .stream()
                .filter(colored -> colored != Colored.BLACK)
                .sorted(Comparator.comparing(Colored::name))
                .toList();

        assertThat(numberCardsColorMap, hasEntry(0, colorsList));
    }

}
