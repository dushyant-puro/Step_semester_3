package week_08.oop.assigment_problems;
import java.util.*;
public class SmartLabControlPanel {
 interface Capability { String name(); }
 interface Power extends Capability { void setPower(boolean on); }
 interface Brightness extends Capability { void setBrightness(int percent); }
 interface Temperature extends Capability { void setTemperature(int celsius); }
 static class Light implements Power,Brightness {boolean on;int brightness;public String name(){return "Light";}public void setPower(boolean v){on=v;}public void setBrightness(int v){if(v<0||v>100)throw new IllegalArgumentException("Brightness must be 0-100");brightness=v;}}
 static class AirConditioner implements Power,Temperature {boolean on;int temperature=22;public String name(){return "AC";}public void setPower(boolean v){on=v;}public void setTemperature(int v){if(v<16||v>30)throw new IllegalArgumentException("Temperature must be 16-30");temperature=v;}}
 static class Scene {List<Runnable> actions=new ArrayList<>();void add(Runnable r){actions.add(r);}void run(){for(Runnable r:actions)r.run();}}
 public static void main(String[] args){Light light=new Light();AirConditioner ac=new AirConditioner();Scene lecture=new Scene();lecture.add(()->light.setPower(true));lecture.add(()->light.setBrightness(40));lecture.add(()->ac.setPower(true));lecture.add(()->ac.setTemperature(24));lecture.run();System.out.println("Lecture Mode applied: light="+light.brightness+"%, AC="+ac.temperature+"C");}
}