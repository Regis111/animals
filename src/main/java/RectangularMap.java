import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

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

    public void addPlant(Position position, int maxEnergy){
        plants.put(position,new Plant(position, ThreadLocalRandom.current().nextInt(1,maxEnergy + 1 )));
    }

    public void addAnimal(Animal animal){
        animals.put(animal.getPosition(),animal);
    }

    public boolean canMoveTo(Position position){
        Animal animal = animals.get(position);
        return animal == null;
    }
    public MapElement objectAt(Position position){
        if(animals.get(position)!=null){
            return animals.get(position);
        }
        else if(plants.get(position) != null){
            return plants.get(position);
        }
        else{
            return null;
        }
    }

    public void positionChanged(Position oldPosition){
        Animal animal = getAnimals().get(oldPosition);
        getAnimals().remove(oldPosition);
        getAnimals().put(animal.getPosition(),animal);
    }
}