
import java.util.ArrayList;
import java.util.List;

interface BattleUnit{
    void attack();
    int getPower();
}

class ModernRobot implements BattleUnit{
    String name;
    int power;

    ModernRobot(String name, int power){
        if(power < 0){
            System.out.println("The power must be positive");
            return ;
        }
        this.name = name;
        this.power = power;
    }

    @Override 
    public void attack(){
        System.out.println("The Modern Robot " + name + " attacks !");
    }

    @Override 
    public int getPower(){
        return power;
    }
}

class OldRobot{
    private final String model;
    private final int laserPower;

    public OldRobot(String model, int laserPower){
        this.model = model;
        this.laserPower = laserPower;
    }
    public void fireLaser(){
        System.out.println(model + " fires a laser !");
    }
    public int getLaserPower(){
        return laserPower;
    }

}

class OldRobotAdaptater implements BattleUnit{
    private OldRobot old;

    public OldRobotAdaptater(String model, int laserPower){
        this.old = new OldRobot(model, laserPower);
    }

    @Override 
    public void attack(){
        old.fireLaser();
    }

    @Override 
    public int getPower(){
        return old.getLaserPower();
    }
}

class RobotSquad implements BattleUnit{
    String name;
    List<BattleUnit> children;

    public RobotSquad( String name, List<BattleUnit> children) {
        this.name = name;
        this.children = children;
    }

    

    void add(BattleUnit b){
        children.add(b);
    }

    void remove(BattleUnit b){
        children.remove(b);
    }

    @Override 
    public void attack(){
        for(BattleUnit b: children){
            b.attack();
        }
    }

    @Override 
    public int getPower(){
        int total = 0;
        for(BattleUnit b : children){
            total += b.getPower();
        }
        return total;
    }
}

public class Battle {
    public static void main(String[] args) {
        BattleUnit RedRobot1 = new ModernRobot(" Red 1 ", 15);
        BattleUnit RedRobot2 = new OldRobotAdaptater(" Red 8 ", 0);
        BattleUnit RedRobot3 = new ModernRobot(" Red 4456 ", 65631);
        BattleUnit RedRobot4 = new ModernRobot(" Red 25 ", 10215);
        List<BattleUnit> RedSquad = new ArrayList<>();
        RedSquad.add(RedRobot1);
        RedSquad.add(RedRobot2);
        RedSquad.add(RedRobot3);
        RedSquad.add(RedRobot4);
        
        BattleUnit redArmy = new RobotSquad("Red Army", RedSquad);

        BattleUnit BlueRobot1 = new ModernRobot(" blue 1 ", 1512);
        BattleUnit BlueRobot2 = new OldRobotAdaptater(" blue 5 ", 75);
        BattleUnit BlueRobot3 = new ModernRobot(" blue 51 ", 6561);
        BattleUnit BlueRobot4 = new ModernRobot(" Blue 25 ", 1215);
        List<BattleUnit> BlueSquad = new ArrayList<>();
        BlueSquad.add(BlueRobot1);
        BlueSquad.add(BlueRobot2);
        BlueSquad.add(BlueRobot3);
        BlueSquad.add(BlueRobot4);
        
        BattleUnit BlueArmy = new RobotSquad("Blue Army", BlueSquad);

        redArmy.attack();
        BlueArmy.attack();

        int red = redArmy.getPower();
        int blue = BlueArmy.getPower();

        if(red > blue){
            System.out.println("Red gagne");
        } else {
            System.out.println("Blue gagne");
        }


    }

}
