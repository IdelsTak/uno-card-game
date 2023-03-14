package com.github.idelstak.uno.card.game;

import com.github.idelstak.uno.card.game.Card.Colored;
import com.github.idelstak.uno.card.game.NumberedCard.Numbered;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.paint.Color;

public final class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        cards.addAll(initNumberedCards());
    }

    List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    Set<Color> getCardColors() {
        return getCards()
                .stream()
                .map(Card::getColor)
                .collect(Collectors.toSet());
    }

    List<NumberedCard> getNumberedCards() {
        return getCards()
                .stream()
                .filter(card -> card instanceof NumberedCard)
                .map(NumberedCard.class::cast)
                .toList();
    }

    Optional<NumberedCard> getNumberedCard(Numbered numbered, Colored colored) {
        return getNumberedCards()
                .stream()
                .filter(card -> card.getNumber() == numbered.getNumber())
                .filter(card -> Objects.equals(card.getColor(), colored.getColor()))
                .findFirst();
    }

    List<NumberedCard> getNumberedCards(Numbered numbered, Colored colored) {
        return getNumberedCards()
                .stream()
                .filter(card -> card.getNumber() == numbered.getNumber())
                .filter(card -> Objects.equals(card.getColor(), colored.getColor()))
                .toList();
    }

    List<NumberedCard> getNumberedCards(Numbered numbered) {
        return getNumberedCards()
                .stream()
                .filter(card -> card.getNumber() == numbered.getNumber())
                .toList();
    }

    private static Collection<? extends Card> initNumberedCards() {
        return Stream
                .concat(
                        getNumberedCardForEachColorExcluding(Colored.BLACK, Optional.empty()).stream(),
                        getNumberedCardForEachColorExcluding(Colored.BLACK, Optional.of(Numbered.ZERO)).stream()
                )
                .toList();
    }

    private static List<NumberedCard> getNumberedCardForEachColorExcluding(Colored coloredValue, Optional<Numbered> optionalNumbered) {
        var coloreds = new LinkedList<Colored>(Arrays.asList(Colored.values()));

        return Stream
                .generate(coloreds::pop)
                .limit(coloreds.size())
                .filter(colored -> colored != coloredValue)
                .flatMap(colored -> getNumberedCardForColorExcludingNumbered(colored, optionalNumbered))
                .toList();
    }

    private static Stream<NumberedCard> getNumberedCardForColorExcludingNumbered(Colored colored, Optional<Numbered> optionalNumbered) {
        return Arrays
                .stream(Numbered.values())
                .filter(numbered -> optionalNumbered.map(value -> numbered != value).orElse(true))
                .map(numbered -> new NumberedCard(colored, numbered));
    }

}
