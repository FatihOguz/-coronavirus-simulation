import java.util.Iterator;

public interface Component {

    void addComponent(Component component);
    void removeComponent(Component component);
    Component getChild(int child);
    int getCollisionTime();
    int getSocialDistance();
    int getSpeed();
    boolean isMask();
    void move();
    boolean isInfected();
    double probInfected();
    void print();
    Iterator createIterator();
    int getXCoordinate();
    int getYCoordinate();
    int size();





}
