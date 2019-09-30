package clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

public class ClockModel extends JPanel{
    
    private Map<String,String[]> numbers;
    private Graphics2D g2d;
    private String numberType = "Decimal";
    private double second;
    private double minute;
    private double hour;
    private double secondAngle;
    private double minuteAngle;
    private double hourAngle;
    private int posX;
    private int posY;
    private double propX;
    private double propY;

    public ClockModel() {
        numbers = new HashMap<String, String[]>();
        numbers.put("Decimal", new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"});
        numbers.put("Roman", new String[]{"I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII"});
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        posX = getWidth()/2;
        posY = getHeight()/2;
        propX = getWidth()/500.0;
        propY = getHeight()/500.0;
        
        setBackground(getWidth(), getHeight(), 6, Color.white, Color.black);
        setNumbers("Arial", numbers.get(numberType));
        clockHand(hourAngle, 150, 8, Color.black);
        clockHand(minuteAngle,  200, 6, Color.black);
        clockHand(secondAngle, 230, 3, Color.red);
        g2d.fillOval(posX-8, posY-8, 16, 16);
    }
    
    public void newSecond(){
        Calendar calendar = new GregorianCalendar();
        second = calendar.get(Calendar.SECOND);
        minute = calendar.get(Calendar.MINUTE) + second/60.0;
        hour = (calendar.get(Calendar.HOUR_OF_DAY))%12 + minute/60.0;
        secondAngle = Math.toRadians((270 - second*6)%360);
        minuteAngle = Math.toRadians((270 - minute*6)%360);
        hourAngle = Math.toRadians((270 - 30*hour)%360);
    }
    
    private void setBackground(int width, int height, int borderThickness, Color backgroundColor, Color borderColor){
        g2d.setColor(backgroundColor);
        g2d.fillOval(borderThickness/2, borderThickness/2, width-borderThickness, height-borderThickness);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderThickness));
        g2d.drawOval(borderThickness/2, borderThickness/2, width-borderThickness, height-borderThickness);
    }
    
    private void clockHand(double angle, int length, int thickness, Color color){
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawLine(posX, posY, posX - (int)(length*Math.cos(angle)*propX), posY + (int)(length*Math.sin(angle)*propY));
    }
    
    private void setNumbers(String font, String[] numbers){
        for (int i = -60; i < 300; i+=30){
            drawCenteredString(g2d, getNumberToBackground(numbers, i), getLimitsToNumberToBackground(i, 20, 20), getFontToNumberToBackground(font,20));
        }
    }

    private Font getFontToNumberToBackground(String font, int size) {
        return new Font(font, 1, size);
    }

    private Rectangle getLimitsToNumberToBackground(double angle, int width, int height){
        double cosine = Math.cos(Math.toRadians(angle));
        double sine = Math.sin(Math.toRadians(angle));
        int recX = posX + (int)(cosine*230*propX)-width/2;
        int recY = posY + (int)(sine*230*propY)-height/2;
        return new Rectangle(recX, recY, width, height);
    }

    private String getNumberToBackground(String[] numbers, int i) {
        return numbers[(i+60)/30];
    }
    
    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
    
    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }
}
