import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Display extends JFrame implements ActionListener {

    private Timer timer;
    private JTextArea area;
    private Simulation simulation;
    private int i = 0;

    public Display(){
        initSimulation();
        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN,20));
        add(area);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Symulacja");
        timer = new Timer(2000,this);
        timer.start();
    }

    private void initSimulation(){
        int birthEnergy = 15;
        int maxPlantEnergy = 15;
        int width = 10;
        int height = 10;

        HashMap<Position,Animal> animals = new HashMap<>();
        HashMap<Position,Plant> plants = new HashMap<>();

        RectangularMap map = new RectangularMap(width,height,animals,plants);

        Simulation simulation = new Simulation(birthEnergy,maxPlantEnergy,map);
        simulation.getMap().addAnimal(new Animal(birthEnergy*4,new Position(width/2,height/2)));

        this.simulation = simulation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        i++;
        simulation.simulateOneDay();
        area.setText("Day is " + (i) + "\n" + new MapVisualizer(simulation.getMap())
                .draw(new Position(0,0),new Position(simulation.getMap().getWidth(),simulation.getMap().getHeight())));
    }
}