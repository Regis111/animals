public abstract class MapElement {
    private Position position;
    private int energy;

    public MapElement(Position p,int e){
        this.position = p;
        this.energy = e;
    }

    public Position getPosition(){
        return this.position;
    }

    public int getEnergy(){
        return this.energy;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setEnergy(int energy){
        this.energy = energy;
    }
}
