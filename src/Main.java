import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends JPanel {
    double prevY = 0;
    double prevX = -650;
    double unchangedPrevY = 0;
    double prevSlope = 0;
    public int runs=0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        runs+=1;
        if (runs>=4) {
            boolean isInvertable = true;
            boolean isOdd = false;
            ArrayList<Double> Ys = new ArrayList<Double>();
            ArrayList<Double> Xs = new ArrayList<Double>();
            ArrayList<Double> slopes = new ArrayList<Double>();
            ArrayList<Double> slopeChanges = new ArrayList<Double>();
            int width = getWidth();
            int height = getHeight();

            // Draws axes
            g.drawLine(0, 430, 1200, 430);
            g.drawLine(600, 0, 600, 1200);
            ArrayList<Double> changeInSlope = new ArrayList<Double>();
            for (double x = (double) -600; x < (double) 600; x++) {
                double y = f(g(x));
//                System.out.println("("+x+", "+y+")");
                Ys.add(y);
                Xs.add(x);
            }
            slopes = fillSlopes(Xs,Ys);
            slopeChanges = changeInSlopes(slopes);



          for(int i  = 0; i < slopeChanges.size(); i++){
              double colorNum = (((slopeChanges.get(i) - Collections.min(slopeChanges))/Collections.max(slopeChanges))*255);
              unchangedPrevY = Ys.get(i);
              if (colorNum > 255) {
                  colorNum = 255;
              } else if (colorNum < 0) {
                  colorNum = 0;
              }
              Color lineColor = new Color((int) (colorNum), 0, 0);
              g.setColor(lineColor);
              g.drawLine(((int) prevX + 600), ((int) -(Ys.get(i)) + 430), ((int) prevX + 600), (int) (-prevY + 430));
              prevY = Ys.get(i);
              prevX = Xs.get(i);
            }
//          checkInflectionPoint(slopeChanges, Xs, Ys);
        }
        }
        public ArrayList<Double> fillSlopes(ArrayList <Double> Xs, ArrayList <Double> Ys){
            ArrayList<Double> slopes = new ArrayList<Double>();
            for (int i = 0; i < Xs.size(); i++) {
                slopes.add(f(g(Xs.get(i))) - f(g(Xs.get(i) + 0.0001)) / -0.0001);
            }
            return slopes;
        }
        public ArrayList<Double> changeInSlopes(ArrayList <Double> slopes){
            ArrayList<Double> slopeChanges = new ArrayList<Double>();
            for (int i = 0; i < slopes.size()-1; i++){
                slopeChanges.add(Math.abs(slopes.get(i)-slopes.get(i+1)));
            }
            return slopeChanges;
        }
        public void checkInflectionPoint (ArrayList <Double> slopes, ArrayList <Double> x, ArrayList <Double> y){
            boolean isNegative = false;
            for (int i = 0; i < slopes.size(); i++) {
//            System.out.println(slopes.get(i));
//                System.out.println(x.get(i) + ", " + y.get(i) + "        " +slopes.get(i));
                if(x.get(i)-1>-599) {
                    if (slopes.get(i) - prevSlope < 0 && !isNegative) {
                        System.out.println("Inflection: " + x.get(i - 1) + ", " + y.get(i - 1));
                    }
                    if (slopes.get(i) - prevSlope >= 0 && isNegative) {
                        System.out.println("Inflection: " + x.get(i - 1) + ", " + y.get(i - 1));
                    }
                }
                if (slopes.get(i) - prevSlope < 0) {
                    isNegative = true;
                } else {
                    isNegative = false;
                }
                prevSlope = slopes.get(i);
            }
        }
        public static void main (String[]args){
            // Create a window (JFrame) to display the sine wave
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 1200);
            frame.add(new Main());
            frame.setVisible(true);
        }
        public static double g ( double x){
            return x;
//            return (x*x)/1000;
//            return 100*Math.sin(x/100);
        }
        public static double f ( double x){
            return x;
        }

}

