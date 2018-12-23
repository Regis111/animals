import java.util.HashMap;

public class RectangularMap {
    private int width;
    private int height;
    private HashMap<Position,Animal> animals;
    private HashMap<Position,Plant> plants;

    public RectangularMap(int width, int height, HashMap<Position,Animal> animals, HashMap<Position,Plant> plants) {
        this.width = width;
        this.height = height;
        this.animals = animals;
        this.plants = plants;
    }

    public HashMap<Position, Animal> getAnimals(){
        return animals;
    }

    public HashMap<Position,Plant> getPlants(){
        return plants;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addPlant(){//jako argument obszar

    }

    public boolean canMoveTo(Position position){
        Animal animal = animals.get(position);
        return animal == null;
    }
}
