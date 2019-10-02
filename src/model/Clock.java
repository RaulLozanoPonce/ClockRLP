package model;

import java.util.HashMap;
import java.util.Map;

public class Clock{
    
    private Map<String,String[]> numbers;
    private double second;
    private double minute;
    private double hour;
    
    public Clock() {
        numbers = new HashMap<String, String[]>();
        numbers.put("Decimal", new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"});
        numbers.put("Roman", new String[]{"I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII"});
        numbers.put("Binary", new String[]{"0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100"});
        numbers.put("Hexadecimal", new String[]{"1","2","3","4","5","6","7","8","9","A","B","C"});
    }
    
    public Map<String, String[]> getNumbers() {
        return numbers;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    public void setMinute(double minute) {
        this.minute = minute;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }

    public double getSecond() {
        return second;
    }

    public double getMinute() {
        return minute;
    }

    public double getHour() {
        return hour;
    }
}
