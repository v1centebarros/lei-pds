package lab07.ex02;

abstract class TextDecorator implements TextReaderInterface {

    protected TextReaderInterface t;

    TextDecorator(TextReaderInterface t) {this.t = t;}
    
    public abstract boolean hasNext();

    public abstract String next();

}
