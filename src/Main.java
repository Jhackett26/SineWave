import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JPanel {
    int prevY;
    int prevX;
    double unchangedPrevY = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the color for drawing


        // Get the dimensions of the panel
        boolean isInvertable = true;
        boolean isOdd = false;
        ArrayList<Double> Ys = new ArrayList<Double>();
        int width = getWidth();
        int height = getHeight();


        // Scale factors to fit the sine wave in the window
        int amplitude = height / 4; // Amplitude of sine wave (adjust to fit)
        int frequency = 1; // Frequency multiplier for the wave
        // Calculate the x-axis (middle of the panel)
        int xAxis = height / 2;
        int left_shift = 0;
        int added_height  = 400;

        // Draw the sine wave
        g.setColor(Color.BLACK);
        for (int x = 0; x < width; x++) {
//            double y = (int)((amplitude * Math.sin(2*(x * 2 *Math.PI * frequency) / width)+added_height)+left_shift);
//            double y = f(g(x));
            double y = x;
            double colorNum = Math.abs((y-unchangedPrevY)*150);
            unchangedPrevY = y;
            if(colorNum>255) {
                colorNum = 255;
            }
            else if(colorNum<0) {
                colorNum = 0;
            }
            Color lineColor = new Color((int)(colorNum), 0, 0);
            g.setColor(lineColor);
            y=(int)((y*-1)+height);
            g.drawLine(prevX,(int)(y),prevX,prevY);
            prevY = (int)(y);
            prevX = x;
            Ys.add(y);
        }
        for(int i = 0; i <= Ys.size()-1;i++) {
            for(int n = 0; n <= Ys.size()-1; n++) {
                if (n != i && Ys.get(n).equals(Ys.get(i))) {
                    isInvertable = false;
                    break;
                }
            }
            if(!isInvertable){
                break;
            }
        }
        if(isInvertable){
            g.drawString("IT IS INVERTABLE",500,400);
        }
        else{
            g.drawString("IT IS NOT INVERTABLE",500,400);
        }
    }

    public static void main(String[] args) {
        // Create a window (JFrame) to display the sine wave
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.add(new Main());
        frame.setVisible(true);
    }
    public static double g(double x){
        return x*x;
    }
    public static double f(double x){
        return x/1000;
    }
}

