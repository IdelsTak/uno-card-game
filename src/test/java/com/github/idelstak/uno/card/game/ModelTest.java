package com.github.idelstak.uno.card.game;

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
    
    

}
