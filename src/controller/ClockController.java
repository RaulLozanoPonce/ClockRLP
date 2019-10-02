package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JPanel;
import model.Clock;

public class ClockController extends JPanel{
    
    private Clock clock;
    private String numberType = "Decimal";
    private double secondAngle;
    private double minuteAngle;
    private double hourAngle;

    public ClockController() {
        clock = new Clock();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        int posX = getWidth()/2;
        int posY = getHeight()/2;
        double propX = getWidth()/500.0;
        double propY = getHeight()/500.0;
        
        setBackground(g2d, getWidth(), getHeight(), 6, Color.white, Color.black);
        setNumbers(g2d, posX, posY, propX, propY, "Arial", clock.getNumbers().get(numberType));
        clockHand(g2d, posX, posY, propX, propY, hourAngle, 150, 8, Color.black);
        clockHand(g2d, posX, posY, propX, propY, minuteAngle,  200, 6, Color.black);
        clockHand(g2d, posX, posY, propX, propY, secondAngle, 230, 3, Color.red);
        
        g2d.fillOval(posX-8, posY-8, 16, 16);
    }
    
    public void newSecond(){
        Calendar calendar = new GregorianCalendar();
        
        clock.setSecond(calendar.get(Calendar.SECOND));
        clock.setMinute(calendar.get(Calendar.MINUTE) + clock.getSecond()/60.0);
        clock.setHour((calendar.get(Calendar.HOUR_OF_DAY))%12 + clock.getMinute()/60.0);
        
        secondAngle = Math.toRadians((270 - 6*clock.getSecond())%360);
        minuteAngle = Math.toRadians((270 - 6*clock.getMinute())%360);
        hourAngle = Math.toRadians((270 - 30*clock.getHour())%360);
    }
    
    private void setBackground(Graphics2D g2d, int width, int height, int borderThickness, Color backgroundColor, Color borderColor){
        g2d.setColor(backgroundColor);
        g2d.fillOval(borderThickness/2, borderThickness/2, width-borderThickness, height-borderThickness);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderThickness));
        g2d.drawOval(borderThickness/2, borderThickness/2, width-borderThickness, height-borderThickness);
    }
    
    private void setNumbers(Graphics2D g2d, int x, int y, double propX, double propY, String font, String[] numbers){
        for (int i = -60; i < 300; i+=30){
            drawCenteredString(g2d, getNumberToBackground(numbers, i), getLimitsToNumberToBackground(x, y, propX, propY, i, 20, 20), getFontToNumberToBackground(font,20));
        }
    }
    
    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
    
    private String getNumberToBackground(String[] numbers, int i) {
        return numbers[(i+60)/30];
    }
    
    private Rectangle getLimitsToNumberToBackground(int x, int y, double propX, double propY, double angle, int width, int height){
        double cosine = Math.cos(Math.toRadians(angle));
        double sine = Math.sin(Math.toRadians(angle));
        int recX = x + (int)(cosine*220*propX)-width/2;
        int recY = y + (int)(sine*220*propY)-height/2;
        return new Rectangle(recX, recY, width, height);
    }
    
    private Font getFontToNumberToBackground(String font, int size) {
        return new Font(font, 1, size);
    }
    
    
    
    private void clockHand(Graphics2D g2d, int x, int y, double propX, double propY, double angle, int length, int thickness, Color color){
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawLine(x, y, x - (int)(length*Math.cos(angle)*propX), y + (int)(length*Math.sin(angle)*propY));
    }
    
    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    public Clock getClock() {
        return clock;
    }
}
