package lab08.ex03;

import lab08.ex03.startypes.*;

import java.util.HashMap;

public class StarFactory {
    protected HashMap<Character, StarType> stars;

    public StarFactory(){
        stars = new HashMap<Character, StarType>();
    }

    public Star createStar(char type) {
        int x = random(0, Demo.CANVAS_SIZE);
        int y = random(0, Demo.CANVAS_SIZE);
        StarType star=null;

        if (stars.containsKey(type)) {
            star = stars.get(type);
            return new Star(x, y, star);
        }

        switch (type) {
            case 'O' : star = new OStar(); break;
            case 'A' : star = new AStar(); break;
            case 'B' : star = new BStar(); break;
            case 'F' : star = new FStar(); break;
            case 'G' : star = new GStar(); break;
            case 'K' : star = new KStar(); break;
            case 'M' : star = new MStar(); break;
        }
        stars.put(type, star);
        return new Star(x, y , star);
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
