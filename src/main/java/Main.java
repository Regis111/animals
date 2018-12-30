import java.awt.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner reader = new Scanner(System.in);
        //System.out.print("Energia wystarczająca na rozmnażanie to: ");
        int birthEnergy = 6;
        //System.out.print("Energia maksymalna rośliny to: ");
        int maxPlantEnergy = 3;
        //System.out.print("Podaj szerokość mapy: ");
        int width = 10;
        //System.out.print("Podaj wysokość mapy: ");
        int height = 10;
        reader.close();

        HashMap<Position,Animal> animals = new HashMap<>();
        HashMap<Position,Plant> plants = new HashMap<>();
        RectangularMap map = new RectangularMap(width,height,animals,plants);
        Simulation simulation = new Simulation(birthEnergy,maxPlantEnergy,map);
        simulation.getMap().addAnimal(new Animal(birthEnergy*2,new Position(width/2,height/2),0));

        EventQueue.invokeLater(() -> {
            new Display(simulation);
        });
    }

}