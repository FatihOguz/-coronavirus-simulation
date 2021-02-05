import java.util.ArrayList;

public interface Observer {
    public void update(ArrayList infectedPeople,int InfectedCount,int healtyCount, boolean death, int speed, ArrayList x, ArrayList y,ArrayList hospital,ArrayList isDead,int recoveringPatient);
}