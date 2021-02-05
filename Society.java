import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Society implements Component,Subject{

    Mediator mediator;

    public double getZValue() {
        return ZValue;
    }
    private int recoveringPatient=0;
    private  double ZValue=0.7;
    private int numberOfInfected;
    private ArrayList observers;
    private ArrayList society = new ArrayList();
    private int collisionTime;
    private int socialDistance;
    private int speed;
    private boolean mask;
    private boolean isInfected;
    private ArrayList peopleInHospital=new ArrayList();
    private double infectedProbFactor=0.10;//probability infected
    protected ArrayList Allx_coordinate=new ArrayList();
    protected ArrayList Ally_coordinate=new ArrayList();
    protected ArrayList InfectedOrHealtyBoolean = new ArrayList();
    private double RFactor=0.6;
    private boolean intersection = false;
    private Random random = new Random(1000);
    private ArrayList isDead=new ArrayList();
    public Society(int collisionTime,int socialDistance,int speed,boolean isMask,boolean isInfected){
        this.collisionTime=collisionTime;
        this.socialDistance=socialDistance;
        this.speed=speed;
        this.mask=isMask;
        this.isInfected=isInfected;
        observers=new ArrayList();
        numberOfInfected=0;
        this.mediator=new Mediator(this);

    }



    public ArrayList getInfectedOrHealtyBoolean() {
        return InfectedOrHealtyBoolean;
    }

    public void setInfectedOrHealtyBoolean(ArrayList infectedOrHealtyBoolean) {
        InfectedOrHealtyBoolean = infectedOrHealtyBoolean;
    }

    @Override
    public void addComponent(Component component) {
        this.society.add(component);
    }

    @Override
    public void removeComponent(Component component) {
        society.remove(component);
    }

    @Override
    public Component getChild(int child) {
        return (Component) society.get(child);
    }

    @Override
    public int getCollisionTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSocialDistance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSpeed() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isMask() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void move() {

        this.Allx_coordinate=new ArrayList();
        this.Ally_coordinate=new ArrayList();
        Iterator iterator =createIterator();

        while(iterator.hasNext()){
            Subject component = (Subject) iterator.next();
            try {
                    boolean flag=component.getInterSection();
                    if(flag==false){
                        component.move();
                    }
                this.Allx_coordinate.add(component.getXCoordinate());
                this.Ally_coordinate.add(component.getYCoordinate());
            }catch (UnsupportedOperationException e){

            }
        }
       this.mediator.menage();
        numberOfInfected=numberOfInfected();
        this.notifyObservers();
        //System.out.println("NUMBER OF INFECTED "+numberOfInfected());

    }

    @Override
    public boolean isInfected() {
        throw new UnsupportedOperationException();

    }

    @Override
    public double probInfected() {
       // System.out.println("prob infected");
        return 0;
    }

    @Override
    public void print() {
        Iterator iterator = this.society.iterator();
        while(iterator.hasNext()){
            Component society1 = (Component) iterator.next();
            society1.print();
        }
    }

    @Override
    public Iterator createIterator() {

        return new CompositeIterator(society.iterator());
    }

    @Override
    public int getXCoordinate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getYCoordinate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList getAllXCoordinate() {
        return this.Allx_coordinate;
    }

    @Override
    public ArrayList getAllYCoordinate() {
        return this.Ally_coordinate;
    }

    @Override
    public double getRFactor() {
        return this.RFactor;
    }

    @Override
    public boolean getInterSection() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setInterSection(boolean interSectionFlag) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getProbInfectedFactor() {
        return this.infectedProbFactor;
    }

    @Override
    public void setProbInfectedFactor(double prob) {
        this.infectedProbFactor=prob;
    }

    @Override
    public void setIsInfected(boolean flag) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList getPeopleInHospital() {
        return peopleInHospital;
    }

    @Override
    public int numberOfInfected() {

       int  res = 0;
        Iterator iterator = this.createIterator();

        while(iterator.hasNext()){
            Component component = (Component) iterator.next();
            try {
                if(component instanceof  healthySociety || component instanceof  infectedSociety){
                    if(component.isInfected()==true && ((Society) component).isDead()==false){

                        res++;
                        // System.out.println(res);
                    }
                }

            }catch (UnsupportedOperationException e){

            }
        }



        return res;
    }




    @Override
    public void setInHospital(boolean flag) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getInHospital() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getInfectedStartTime() {
       throw new UnsupportedOperationException();
    }

    @Override
    public void setInfectedStartTime(long infectedStartTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getHospitalTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setHospitalTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDead() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDead(boolean dead) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList getIsDead() {
        return isDead;
    }

    @Override
    public double getZ() {
        return this.ZValue;
    }

    @Override
    public boolean isIntersection() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setIntersection(boolean intersection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getIntersection() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getInterSectionTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setInterSectionTime(long interSectionTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void registerObserver(Observer o) {

        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {

        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer)observers.get(i);
            boolean death;
            if(probInfected()==1){
                death = true;
            }
            else{
                 death = false;
            }
            int deadCount=this.numberOfIDead();
            int infectedCount=this.numberOfInfected();


            int healtyCount = size() - numberOfInfected() - deadCount ;
            int recoveringPatient = this.getRecoveringPatient();
            //System.out.println("society  " + this.getPeopleInHospital());
            observer.update(this.InfectedOrHealtyBoolean,infectedCount,healtyCount,death,this.speed,this.Allx_coordinate,this.Ally_coordinate,this.getPeopleInHospital(),this.isDead,recoveringPatient);
        }
    }
    public int numberOfIDead(){
        int  res = 0;
        Iterator iterator = this.createIterator();

        while(iterator.hasNext()){
            Component component = (Component) iterator.next();
            try {
                if(component instanceof  healthySociety || component instanceof  infectedSociety){

                    if(((Society) component).isDead()==true){
                        res++;
                    }

                    // System.out.println(res);
                }
            }catch (UnsupportedOperationException e){

            }
        }



        return res;
    }


    public int size(){

        int  res = 0;
        Iterator iterator = this.createIterator();

        while(iterator.hasNext()){
            Component component = (Component) iterator.next();
            try {
                if(component.isInfected()==true || component.isInfected()==false ){

                    res++;

                   // System.out.println("res " + res);
                }
            }catch (UnsupportedOperationException e){

            }
        }



        return res;
    }


    public void takeMediator(Mediator mediator){
        this.mediator=mediator;
    }

    public int getRecoveringPatient() {
        return recoveringPatient;
    }

    public void setRecoveringPatient(int recoveringPatient) {
        this.recoveringPatient = recoveringPatient;
    }

    @Override
    public void setZFactor(double factor) {
        this.ZValue=factor;
    }

    @Override
    public void setRFactor(double factor) {
        this.RFactor=factor;
    }

    @Override
    public void setCoordinateAfterInterSection(int x, int y) {
            throw new UnsupportedOperationException();
    }


}
