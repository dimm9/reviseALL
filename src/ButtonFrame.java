import javax.swing.*;
import java.awt.*;

public class ButtonFrame extends JFrame {
    JButton button1 = new JButton("NUMBER");
    JButton button2 = new JButton("COLOR");
    Color[] colors = {Color.RED, Color.GREEN};
    int number = 5;
    int temp = 1;
    JLabel num = new JLabel();

    public ButtonFrame() {
        button1.setBounds(100, 100, 100, 50);
        button1.addActionListener((e) -> {number++; num.setText(String.valueOf(number));});
        button2.setBounds(100, 200, 100, 50);
        button2.addActionListener((e) -> {paintOval(getGraphics(), colors[temp%2]); temp++;});
        this.add(button1);
        this.add(button2);
        num.setText(String.valueOf(number));
        num.setFont(new Font("Arial", Font.BOLD, 48));
        num.setBounds(300, 100, 100, 50);
        this.add(num);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintOval(g, Color.GREEN);
    }

    public void paintOval(Graphics g, Color color) {
        g.setColor(color);
        g.fillOval(250, 250, 100, 100);
    }

    public static void main(String[] args) {
        new ButtonFrame();
    }
}
