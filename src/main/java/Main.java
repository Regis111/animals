import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Main {
    public static void main(String [] args){
        Scanner reader = new Scanner(System.in);
        //System.out.print("Podaj długość symulacji w dniach: ");
        int numberOfDays = 3;
        //System.out.print("Energia wystarczająca na rozmnażanie to: ");
        int birthEnergy = 2;
        //System.out.print("Energia maksymalna rośliny to: ");
        int maxPlantEnergy = 2;
        //System.out.print("Podaj szerokość mapy: ");
        int width = 10;
        //System.out.print("Podaj wysokość mapy: ");
        int height = 10;
        reader.close();

        HashMap<Position,Animal> animals = new HashMap<>();
        HashMap<Position,Plant> plants = new HashMap<>();
        RectangularMap map = new RectangularMap(width,height,animals,plants);
        Simulation simulation = new Simulation(birthEnergy,maxPlantEnergy,map);
        simulation.getMap().addAnimal(new Animal(4*maxPlantEnergy,new Position(width/2,height/2)));

        for(int i = 0; i < numberOfDays; i++){
            System.out.println(new MapVisualizer(simulation.getMap()).draw(new Position(0,0),new Position(width,height)));
            simulation.simulateOneDay();
        }
    }
}