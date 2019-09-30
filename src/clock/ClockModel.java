package clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JPanel;

public class ClockModel extends JPanel{
    
    private double second;
    private double minute;
    private double hour;
    private Graphics2D g2d;
    private double secondAngle;
    private double minuteAngle;
    private double hourAngle;
    private int posX;
    private int posY;
    
    public ClockModel(){
        Calendar calendario = new GregorianCalendar();
        second = calendario.get(Calendar.SECOND);
        minute = calendario.get(Calendar.MINUTE);
        hour = (calendario.get(Calendar.HOUR_OF_DAY))%12;
        setLayout(null);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        posX = getWidth()/2;
        posY = getHeight()/2;
        
        g2d.setColor(Color.white);
        g2d.fillOval(3, 3, getWidth()-6, getHeight()-6);
        
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawOval(3, 3, getWidth()-6, getHeight()-6);
        
        for (int i = -60; i < 300; i+=30) {
            g2d.drawString((i+60)/30 + 1 + "", posX + (int) (Math.cos(Math.toRadians(i))*120) - 3, posY + (int) (Math.sin(Math.toRadians(i))*120) + 3);
        }
        
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(7));
        g2d.drawLine(posX, posY, posX - (int) (Math.cos(hourAngle)*70), posY + (int) (Math.sin(hourAngle)*70));
        
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(posX, posY, posX - (int) (Math.cos(minuteAngle)*100), posY + (int) (Math.sin(minuteAngle)*100));
        
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(posX, posY, posX - (int) (Math.cos(secondAngle)*120), posY + (int) (Math.sin(secondAngle)*120));
        
        g2d.fillOval(posX-6, posY-6, 12, 12);
    }
    
    public void newSecond(){
        second += (1.0)%60;
        minute += (1.0/60.0)%60;
        hour += (1.0/720.0)%12;
        secondAngle = Math.toRadians((270 - second*6)%360);
        minuteAngle = Math.toRadians((270 - minute*6)%360);
        hourAngle = Math.toRadians((270 - 30*hour)%360);
    }
}
