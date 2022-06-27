package lab01;

public enum Direction {
    RIGHT("Right","H"),
    LEFT("Left","H"),
    DOWN("Down","V"),
    UP("Up","V"),
    DOWNRIGHT("DownRight","D1"),
    DOWNLEFT("DownLeft","D2"),
    UPRIGHT("UpRight","D1"),
    UPLEFT("UpLeft", "D2"),
    UNKNOWN("Unknown","UN");

    public final String label;
    public final String orientation;

    Direction(String label,String orientation){
        this.label = label;
        this.orientation = orientation;
    }

    public String getOrientation() {
        return orientation;
    }
}
