package com.github.idelstak.uno.card.game;

import java.util.Objects;

class NumberedCard extends Card {

    enum Numbered {

        ZERO(0),
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9);

        private final int number;

        private Numbered(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

    }
    private final Numbered numbered;

    NumberedCard(Colored colored, Numbered numbered) {
        super(colored);

        this.numbered = numbered;
    }

    int getNumber() {
        return numbered.getNumber();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.numbered);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NumberedCard other = (NumberedCard) obj;
        if (this.numbered.getNumber() != other.numbered.getNumber()) {
            return false;
        }
        return Objects.equals(this.colored, other.colored);
    }

    @Override
    public String toString() {
        return String.format("%s%s", numbered.getNumber(), colored.toString());
    }

}
