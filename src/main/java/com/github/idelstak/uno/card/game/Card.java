package com.github.idelstak.uno.card.game;

import javafx.scene.paint.Color;

class Card {

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

}
