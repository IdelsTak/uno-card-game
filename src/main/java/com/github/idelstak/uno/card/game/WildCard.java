package com.github.idelstak.uno.card.game;

class WildCard extends Card {

    enum Type {
        WILD, WILD_DRAW_4
    }

    private final Type type;

    WildCard(Colored colored, Type type) {
        super(colored);

        this.type = type;
    }

    Type getType() {
        return type;
    }

}
