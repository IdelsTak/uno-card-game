package com.github.idelstak.uno.card.game;

class SpecialCard extends Card {

    enum Type {
        SKIP, REVERSE, DRAW_2;
    }

    private final Type type;

    SpecialCard(Colored colored, Type type) {
        super(colored);
        this.type = type;
    }

    Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%s-%s", type.name(), colored.name());
    }

}
