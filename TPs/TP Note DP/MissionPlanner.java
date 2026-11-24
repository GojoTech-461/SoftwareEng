import java.util.List;

interface Mission {
    void display(String indent);
    int getDuration();
}

class SimpleMission implements Mission {
    private int duration;

    public SimpleMission(int duration){
        this.duration = duration;
    }

    @Override
    public void display(String indent){
        System.out.println(indent);
    }

    @Override
    public int getDuration(){
        return duration;
    }

}

class MissionList implements Mission{
    String indent;
    List<Mission> list;

    public void add(Mission mission) {
        list.add(mission);
    }

    public void remove(Mission mission){
        list.remove(mission);
    }

    @Override 
    public int getDuration() {
        int total = 0;
        for(Mission mission : list){
            total += mission.getDuration();
        }
        return total;
    }

    @Override 
    public void display(String indent){
        for(Mission mission : list){
            mission.display(indent);
        }
    }
}


public class MissionPlanner {

}
