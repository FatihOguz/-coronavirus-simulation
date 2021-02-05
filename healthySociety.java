import java.util.Iterator;
import java.util.Random;

public class healthySociety extends Society{
    private int collisionTime;
    private int socialDistance;
    private int speed;
    private boolean mask;
    private boolean isInfected;
    private Random random = new Random();
    private int y_coordinate;
    private int x_coordinate;
    private boolean intersection = false;
    private boolean inHospital=false;
    private  long infectedStartTime;
    private long HospitalTime;
    private boolean isDead=false;
    private boolean isIntersection;
    private long interSectionTime;

    public healthySociety(int collisionTime,int socialDistance,int speed,boolean isMask,boolean isInfected) {
        super(collisionTime,socialDistance,speed,isMask,isInfected);
        this.collisionTime = collisionTime;
        this.socialDistance = socialDistance;
        this.speed = speed;
        this.mask = isMask;

        setIsInfected(isInfected);

        this.x_coordinate=random.nextInt(500);
        this.y_coordinate=random.nextInt(500);
    }

    @Override
    public void addComponent(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeComponent(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Component getChild(int child) {
        throw new UnsupportedOperationException();
    }


    @Override
    public int getCollisionTime() {
        return this.collisionTime;
    }

    @Override
    public int getSocialDistance() {
        return this.socialDistance;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public boolean isMask() {
        return this.mask;
    }

    @Override
    public void move() {
        if(isIntersection()==true){
            System.out.println("intersection");
        }
        else{
            int way = random.nextInt(4);

            if(way==0){ //right
                this.x_coordinate=this.x_coordinate + this.speed;
                if(x_coordinate>=1000){
                    this.x_coordinate=this.x_coordinate - this.speed;
                    this.x_coordinate=this.x_coordinate - this.speed;
                }
            }
            else if(way==1){//left
                this.x_coordinate=this.x_coordinate - this.speed;
                if(x_coordinate<=0){
                    this.x_coordinate=this.x_coordinate + this.speed;
                    this.x_coordinate=this.x_coordinate + this.speed;
                }
            }
            else if(way==2){//down
                this.y_coordinate=this.y_coordinate + this.speed;
                if(this.y_coordinate>=600){
                    this.y_coordinate=this.y_coordinate - this.speed;
                    this.y_coordinate=this.y_coordinate - this.speed;
                }
            }
            else if(way==3){//up
                this.y_coordinate=this.y_coordinate - this.speed;
                if(this.y_coordinate<=0){
                    this.y_coordinate=this.y_coordinate + this.speed;
                    this.y_coordinate=this.y_coordinate + this.speed;
                }
            }
        }



        this.Allx_coordinate.add(this.x_coordinate);
        this.Ally_coordinate.add(this.y_coordinate);

    }

    @Override
    public boolean isInfected() {
        return  this.isInfected;

    }
    public void setIsInfected(boolean flag){

        this.isInfected=flag;
        if(flag==true){
            this.infectedStartTime=System.currentTimeMillis();
        }
    }

    @Override
    public double probInfected() {
        System.out.println("prob infected");
        return 0;
    }

    @Override
    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "healthySociety{" +
                "collisionTime=" + collisionTime +
                ", socialDistance=" + socialDistance +
                ", speed=" + speed +
                ", mask=" + mask +
                ", isInfected=" + isInfected +
                '}';
    }


    public Iterator createIterator() {
        return new NullIterator();
    }

    @Override
    public int getXCoordinate() {
        return this.x_coordinate;
    }

    @Override
    public int getYCoordinate() {
        return this.y_coordinate;
    }

    @Override
    public int size() {
        return 0;
    }
    @Override
    public boolean getInterSection() {
        return this.intersection;
    }

    @Override
    public void setInterSection(boolean interSectionFlag) {
        this.intersection=interSectionFlag;
    }

    public void setInHospital(boolean flag){
        this.inHospital=flag;
    }
    public boolean getInHospital(){
        return this.inHospital;
    }

    public long getInfectedStartTime() {
        return infectedStartTime;
    }

    public void setInfectedStartTime(long infectedStartTime) {
        this.infectedStartTime = infectedStartTime;
    }

    @Override
    public long getHospitalTime() {
       return  this.HospitalTime;
    }

    @Override
    public void setHospitalTime() {
        this.HospitalTime=System.currentTimeMillis();
    }


    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public void setDead(boolean dead) {
        this.isDead=dead;
    }

    public void setIntersection(boolean intersection) {
        this.isIntersection = intersection;
        this.interSectionTime=System.currentTimeMillis();
    }
    public boolean getIntersection(){
        return this.isIntersection;
    }

    public long getInterSectionTime() {
        return interSectionTime;
    }

    public void setInterSectionTime(long interSectionTime) {
        this.interSectionTime = interSectionTime;
    }

    @Override
    public boolean isIntersection() {
       return isIntersection;
    }
    @Override
    public void setCoordinateAfterInterSection(int x, int y) {
       this.x_coordinate=x;
       this.y_coordinate=y;
    }

}
