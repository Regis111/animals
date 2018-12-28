import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Simulation {
    private final int energyRequiredToGiveBirth;
    private final int maxEnergyOfPlant;
    private RectangularMap map; ////obszar dzungli to prostokat o położeniu (w/2 - 0.1w,y/2 - 0.1y) do (w/2 + 0.1w,y/2 + 0.1y)

    public Simulation(int energyRequiredToGiveBirth, int maxEnergyOfPlant, RectangularMap map){
        this.map = map;
        this.maxEnergyOfPlant = maxEnergyOfPlant;
        this.energyRequiredToGiveBirth = energyRequiredToGiveBirth;
    }

    public void simulateOneDay(){
        moveAnimals();
        generatePlants();
    }

    public RectangularMap getMap(){
        return map;
    }

    private void moveAnimals(){
        List<Animal> animalList = new ArrayList<>(map.getAnimals().values());
        animalList = animalList.stream().sorted(Comparator.comparing(Animal::getEnergy)).collect(Collectors.toList());
        for(Animal animal : animalList){
            animal.move(map);
            animal.eat(map);
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

    private void generatePlants(){
        int jungleX = ThreadLocalRandom.current().nextInt((int)(map.getWidth()*0.4),(int)(map.getWidth()*0.6 + 1));
        int jungleY = ThreadLocalRandom.current().nextInt((int)(map.getHeight()*0.4),(int)(map.getHeight()*0.6 + 1));
        ///1. random number z przedziału (w/2 - 0.1w, w/2 + 0.1w) oraz 2.random number z przedziału (y/2 - 0.1y,y/2 + 0.1y)
        //i to jest roślina w dzungli
        int notJungleX1 = ThreadLocalRandom.current().nextInt((0),(int)(map.getWidth()*0.4 + 1));
        int notJungleX2 = ThreadLocalRandom.current().nextInt((int)(map.getWidth()*0.6),(map.getWidth()));

        int random1 = ThreadLocalRandom.current().nextInt(0,2);
        int notJungleX;
        if(random1 == 0){
            notJungleX = notJungleX1;
        }
        else{
            notJungleX = notJungleX2;
        }

        int notJungleY1 = ThreadLocalRandom.current().nextInt((int)(0),(int)(map.getHeight()*0.4 + 1));
        int notJungleY2 = ThreadLocalRandom.current().nextInt((int)(map.getHeight()*0.6),(int)(map.getHeight()));

        int random2 = ThreadLocalRandom.current().nextInt(0,2);
        int notJungleY;
        if(random2 == 0){
            notJungleY = notJungleY1;
        }
        else{
            notJungleY = notJungleY2;
        }
        ///1. random number z przedziału (0,w-1) / (w/2 - 0.1w, w/2 + 0.1w) oraz 2.random number z przedziału (0,y-1) / (y/2 - 0.1y,y/2 + 0.1y)
        //i to jest roślina poza dzungla
        map.addPlant(new Position(jungleX,jungleY), maxEnergyOfPlant);
        map.addPlant(new Position(notJungleX,notJungleY), maxEnergyOfPlant);
    }


}