import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame implements ActionListener {

    private Timer timer = new Timer(2000,this);
    private JTextArea area;
    private Simulation simulation;

    public Display(Simulation simulation){
        this.simulation = simulation;
        area = new JTextArea("");
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN,20));
        add(area);
        setResizable(false);
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Ruch");
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        simulation.simulateOneDay();
        area.setText("");
        area.setText(new MapVisualizer(simulation.getMap())
                .draw(new Position(0,0),new Position(simulation.getMap().getWidth(),simulation.getMap().getHeight())));
    }
}
