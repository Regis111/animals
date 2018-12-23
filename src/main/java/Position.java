public class Position {
    final int x;
    final int y;

    public Position(int x0,int y0){
        x = x0;
        y = y0;
    }

    public Position add(Position position){
        Position newPosition = new Position(position.x + x, position.y + y);
        return newPosition;
    }
}
