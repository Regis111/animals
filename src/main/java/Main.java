import javafx.geometry.Pos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static void generateHashMaps(HashMap<Position,Animal> animalHashMap,HashMap<Position,Plant> plantHashMap){//TO DO

    }

    public static void main(String [] args){
        /*
        ArrayList<Integer> genes = new ArrayList<>();
        genes.add(0,2);
        genes.add(1,4);
        genes.add(2,5);
        Animal cat = new Animal(genes,0,new Position(2,2));
        cat.move();
        */
        Scanner reader = new Scanner(System.in);
        System.out.print("Podaj długość symulacji w dniach: ");
        int numberOfDays = reader.nextInt();
        System.out.print("Energia wystarczająca na rozmnażanie to: ");
        int energy = reader.nextInt();
        System.out.print("Podaj szerokość mapy: ");
        int width = reader.nextInt();
        System.out.print("Podaj wysokość mapy: ");
        int height = reader.nextInt();
        reader.close();
        HashMap<Position,Animal> animals = new HashMap<>();
        HashMap<Position,Plant> plants = new HashMap<>();
        generateHashMaps(animals,plants);
        RectangularMap map = new RectangularMap(width,height,animals,plants);
        Simulation simulation = new Simulation(energy,map);
        for(int i = 0;i < numberOfDays; i++){
            simulation.simulateOneDay();
        }
    }
}