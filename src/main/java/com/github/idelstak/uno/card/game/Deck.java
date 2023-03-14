package com.github.idelstak.uno.card.game;

import com.github.idelstak.uno.card.game.Card.Colored;
import com.github.idelstak.uno.card.game.NumberedCard.Numbered;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import java.util.stream.Stream;

public final class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        cards.addAll(initNumberedCards());
        cards.addAll(initSpecialCards());
    }

    List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    List<NumberedCard> getNumberedCards() {
        return getCards()
                .stream()
                .filter(card -> card instanceof NumberedCard)
                .map(NumberedCard.class::cast)
                .toList();
    }

    Map<Integer, List<Colored>> getNumberedCardsColorMap() {
        return getNumberedCards()
                .stream()
                .collect(
                        groupingBy(
                                NumberedCard::getNumber,
                                collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream().sorted().map(Card::getColored).toList()
                                )
                        )
                );
    }

    List<SpecialCard> getSpecialCards() {
        return getCards()
                .stream()
                .filter(card -> card instanceof SpecialCard)
                .map(SpecialCard.class::cast)
                .toList();
    }

    Map<SpecialCard.Type, List<Colored>> getSpecialCardsColorMap() {
        return getSpecialCards()
                .stream()
                .collect(
                        groupingBy(
                                SpecialCard::getType,
                                collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream().sorted().map(Card::getColored).toList()
                                )
                        )
                );
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

    private static Collection<? extends Card> initSpecialCards() {
        var coloreds = new LinkedList<Colored>(Arrays.asList(Colored.values()));
        var specialCards = Stream.generate(coloreds::pop)
                .limit(coloreds.size())
                .filter(colored -> colored != Colored.BLACK)
                .flatMap(colored -> Arrays.stream(SpecialCard.Type.values()).map(type -> new SpecialCard(colored, type)))
                .toList();

        return Stream.concat(specialCards.stream(), specialCards.stream()).toList();
    }

}
