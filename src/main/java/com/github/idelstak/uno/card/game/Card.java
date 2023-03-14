package com.github.idelstak.uno.card.game;

import java.util.Comparator;
import javafx.scene.paint.Color;

class Card implements Comparable<Card> {

    enum Colored {

        RED(Color.RED),
        YELLOW(Color.YELLOW),
        BLUE(Color.BLUE),
        GREEN(Color.GREEN),
        BLACK(Color.BLACK);

        private final Color color;

        private Colored(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

    }
    protected final Colored colored;

    Card(Colored colored) {
        this.colored = colored;
    }

    Color getColor() {
        return colored.getColor();
    }

    @Override
    public int compareTo(Card otherCard) {
        return Comparator.comparing(Card::toString).compare(this, otherCard);
    }

    @Override
    public String toString() {
        return colored.name();
    }

}
