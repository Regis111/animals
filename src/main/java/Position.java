public class Position {
    public final int x;
    public final int y;

    public Position(int x0,int y0){
        x = x0;
        y = y0;
    }

    public Position add(Position position){
        Position newPosition = new Position(position.x + x, position.y + y);
        return newPosition;
    }

    public String toString(){
        return ("(" + this.x + "," + this.y + ")");
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (!(other instanceof Position)) return false;
        Position that = (Position) other;
        if(this.x == that.x && this.y == that.y) return true;
        else return false;
    }

    @Override
    public int hashCode(){
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
}
