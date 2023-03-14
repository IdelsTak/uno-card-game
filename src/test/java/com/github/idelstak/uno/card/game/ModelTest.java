package com.github.idelstak.uno.card.game;

import com.github.idelstak.uno.card.game.Card.Colored;
import com.github.idelstak.uno.card.game.NumberedCard.Numbered;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
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

        assertThat(numberCardsColorMap, hasEntry(Numbered.ZERO.getNumber(), colorsList));
    }

    @Test
    public void a_new_deck_contains_2_cards_numbered_1_to_9_for_each_color_except_black() {
        var deck = new Deck();
        var numberCardsColorMap = deck.getNumberedCardsColorMap();
        var coloredsList = Arrays.asList(Colored.values());
        var coloredsStream = Stream.concat(coloredsList.stream(), coloredsList.stream());
        var doubleColorsList = coloredsStream
                .filter(colored -> colored != Colored.BLACK)
                .sorted(Comparator.comparing(Colored::name))
                .toList();

        assertThat(
                numberCardsColorMap,
                allOf(
                        hasEntry(Numbered.ONE.getNumber(), doubleColorsList),
                        hasEntry(Numbered.TWO.getNumber(), doubleColorsList),
                        hasEntry(Numbered.THREE.getNumber(), doubleColorsList),
                        hasEntry(Numbered.FOUR.getNumber(), doubleColorsList),
                        hasEntry(Numbered.FIVE.getNumber(), doubleColorsList),
                        hasEntry(Numbered.SIX.getNumber(), doubleColorsList),
                        hasEntry(Numbered.SEVEN.getNumber(), doubleColorsList),
                        hasEntry(Numbered.EIGHT.getNumber(), doubleColorsList),
                        hasEntry(Numbered.NINE.getNumber(), doubleColorsList)
                )
        );
    }

}
