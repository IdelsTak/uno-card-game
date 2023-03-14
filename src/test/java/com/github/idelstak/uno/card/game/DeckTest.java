package com.github.idelstak.uno.card.game;

import com.github.idelstak.uno.card.game.Card.Colored;
import com.github.idelstak.uno.card.game.NumberedCard.Numbered;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    public void a_new_deck_has_76_numbered_cards() {
        var deck = new Deck();
        var numberedCards = deck.getNumberedCards();

        assertThat(numberedCards, hasSize(76));
    }

    @Test
    public void a_new_deck_has_a_card_numbered_0_for_each_color_except_black() {
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
    public void a_new_deck_has_2_cards_numbered_1_to_9_for_each_color_except_black() {
        var deck = new Deck();
        var numberCardsColorMap = deck.getNumberedCardsColorMap();
        var coloredsList = Arrays.asList(Colored.values());
        var doubleColoredsList = Stream.concat(coloredsList.stream(), coloredsList.stream())
                .filter(colored -> colored != Colored.BLACK)
                .sorted(Comparator.comparing(Colored::name))
                .toList();

        assertThat(
                numberCardsColorMap,
                allOf(
                        hasEntry(Numbered.ONE.getNumber(), doubleColoredsList),
                        hasEntry(Numbered.TWO.getNumber(), doubleColoredsList),
                        hasEntry(Numbered.THREE.getNumber(), doubleColoredsList),
                        hasEntry(Numbered.FOUR.getNumber(), doubleColoredsList),
                        hasEntry(Numbered.FIVE.getNumber(), doubleColoredsList),
                        hasEntry(Numbered.SIX.getNumber(), doubleColoredsList),
                        hasEntry(Numbered.SEVEN.getNumber(), doubleColoredsList),
                        hasEntry(Numbered.EIGHT.getNumber(), doubleColoredsList),
                        hasEntry(Numbered.NINE.getNumber(), doubleColoredsList)
                )
        );
    }

    @Test
    public void a_new_deck_has_24_special_cards() {
        var deck = new Deck();
        var specialCards = deck.getSpecialCards();

        assertThat(specialCards, hasSize(24));
    }

    @Test
    public void a_new_deck_has_2_special_cards_for_each_color_except_black() {
        var deck = new Deck();
        var specialCardsColorMap = deck.getSpecialCardsColorMap();
        var coloredsList = Arrays.asList(Colored.values());
        var doubleColoredsList = Stream.concat(coloredsList.stream(), coloredsList.stream())
                .filter(colored -> colored != Colored.BLACK)
                .sorted(Comparator.comparing(Colored::name))
                .toList();

        assertThat(
                specialCardsColorMap,
                allOf(
                        hasEntry(SpecialCard.Type.DRAW_2, doubleColoredsList),
                        hasEntry(SpecialCard.Type.REVERSE, doubleColoredsList),
                        hasEntry(SpecialCard.Type.SKIP, doubleColoredsList)
                )
        );
    }

    @Test
    public void a_new_deck_has_8_wild_cards() {
        var deck = new Deck();
        var wildCards = deck.getWildCards();

        assertThat(wildCards, hasSize(8));
    }

}
