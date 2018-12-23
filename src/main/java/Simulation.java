import java.util.*;
import java.util.stream.Collectors;

public class Simulation {
    private final int energyRequiredToGiveBirth;
    private RectangularMap map; ////obszar dzungli to prostokat o położeniu (w/2 - 0.1w,y/2 - 0.1y) do (w/2 + 0.1w,y/2 + 0.1y)

    public Simulation(int energyRequiredToGiveBirth, RectangularMap map){
        this.map = map;
        this.energyRequiredToGiveBirth = energyRequiredToGiveBirth;
    }

    public void simulateOneDay(){
        moveAnimals();
        generatePlants();
    }

    private void moveAnimals(){
        List<Animal> animalList = new ArrayList<Animal>(map.getAnimals().values());
        animalList = animalList.stream().sorted(Comparator.comparing(Animal::getEnergy)).collect(Collectors.toList());
        for(Animal animal : animalList){
            animal.move();
            animal.eat();
            animal.setEnergy(animal.getEnergy() - 1);
            if (animal.getEnergy() == 0){
                map.getAnimals().remove(animal);
            }
            if(animal.getEnergy() >= energyRequiredToGiveBirth){
                Animal smallAnimal = animal.getChild();
                map.getAnimals().put(smallAnimal.getPosition(),smallAnimal);
            }
        }
    }

    private void generatePlants(){ //TO DO
        ///1. random number z przedziału (w/2 - 0.1w, w/2 + 0.1w) oraz 2.random number z przedziału (y/2 - 0.1y,y/2 + 0.1y)
        //i to jest roślina w dzungli

        ///1. random number z przedziału (0,w-1) / (w/2 - 0.1w, w/2 + 0.1w) oraz 2.random number z przedziału (0,y-1) / (y/2 - 0.1y,y/2 + 0.1y)
        //i to jest roślina poza dzungla
    }
}