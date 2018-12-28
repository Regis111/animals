import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Main {
    
    public static void main(String [] args){
        Scanner reader = new Scanner(System.in);
        //System.out.print("Podaj długość symulacji w dniach: ");
        int numberOfDays = 1;
        //System.out.print("Energia wystarczająca na rozmnażanie to: ");
        int birthEnergy = 20;
        //System.out.print("Energia maksymalna rośliny to: ");
        int maxPlantEnergy = 2;
        //System.out.print("Podaj szerokość mapy: ");
        int width = 4;
        //System.out.print("Podaj wysokość mapy: ");
        int height = 4;
        reader.close();

        HashMap<Position,Animal> animals = new HashMap<>();
        HashMap<Position,Plant> plants = new HashMap<>();
        RectangularMap map = new RectangularMap(width,height,animals,plants);
        Simulation simulation = new Simulation(birthEnergy,maxPlantEnergy,map);

        ArrayList<Integer> genes = new ArrayList<>();
        genes.add(0, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(1, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(2, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(3, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(4, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(5, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(6, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(7, ThreadLocalRandom.current().nextInt(0,8));

        simulation.getMap().addAnimal(new Animal(genes,4*maxPlantEnergy,new Position(2,2)));

        for(int i = 0; i < numberOfDays; i++){
            System.out.println(new MapVisualizer(simulation.getMap()).draw(new Position(0,0),new Position(width,height)));
            simulation.simulateOneDay();
            System.out.println(simulation.getMap().getAnimals().values().stream().map(MapElement::getPosition).collect(Collectors.toList()));
            System.out.println(simulation.getMap().getAnimals().get(simulation.getMap().getAnimals().values().stream().map(MapElement::getPosition).collect(Collectors.toList()).get(0)) == null);
            System.out.println(simulation.getMap().getAnimals().get(new Position(2,2)) == null);
            System.out.println(new MapVisualizer(simulation.getMap()).draw(new Position(0,0),new Position(width,height)));
        }
    }
}