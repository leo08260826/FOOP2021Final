public class Position {
    public int x;
    public int y;

    public Position(int _x, int _y) {
        x = _x;
        y = _y;
    }
    public Position(String xStr, String yStr) {
        this(Integer.parseInt(xStr), Integer.parseInt(yStr));
    }

    @Override
    public String toString(){
        return String.format("(%d, %d)", x, y);
    }
}
