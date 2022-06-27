package lab01;

import java.util.Objects;

public class Word {
    private String word;
    private int length;
    private int line;
    private int column;
    private Direction direction;

    public Word(String word, int line, int column, Direction direction) {
        this.word = word;
        this.length = word.length();
        this.line = line;
        this.column = column;
        this.direction = direction;
    }

    public Word (String word) {
        this.word = word;
        this.length = word.length();
        this.line = -1;
        this.column = -1;
    }

    public String getWord() {
        return word;
    }

    public int getLength() {
        return length;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        //TODO Fix format
        return String.format("%-15s %-8d %-10s %-1s\n",this.word.toUpperCase(),this.length,String.format("%d,%d", this.line+1,this.column+1),this.direction.label);
        //return String.format("%-15s %-2d %-2d,%-8d %-1s\n",this.word,this.length,this.line+1,this.column+1,this.direction.label);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return length == word1.length && line == word1.line && column == word1.column && Objects.equals(word, word1.word) && Objects.equals(direction, word1.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, length, line, column, direction);
    }
}
