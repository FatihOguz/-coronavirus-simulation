import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
public class MyPanel extends JPanel implements ActionListener ,Observer {
    final int PANEL_WIDTH=1000;
    final int PANEL_HEIGHT=600;
    Image[] enemy;
    Image person ;
    Image InfectedPerson ;
    Image backGround;
    Timer timer;
    int xVelocity=1;
    int yVelocity=1;
    int x=150;
    int y=150;
    int countOfInfected=0;
    int countOfHealty=0;
    Subject society;
    ArrayList xCoordinates=new ArrayList();
    ArrayList hospital=new ArrayList();
    ArrayList yCoordinates=new ArrayList();
    ArrayList isDead=new ArrayList();
    public MyPanel(Subject society){
        this.society=society;
        society.registerObserver(this);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));

        this.setBackground(Color.RED);

        person = new ImageIcon("enemy.jpeg").getImage();
        InfectedPerson = new ImageIcon("green.png").getImage();
        person=person.getScaledInstance(5,5, Image.SCALE_SMOOTH);
        InfectedPerson=InfectedPerson.getScaledInstance(5,5,Image.SCALE_SMOOTH);
        Image image1=new ImageIcon("world.jpeg").getImage();
        backGround=image1.getScaledInstance(PANEL_WIDTH,PANEL_HEIGHT,Image.SCALE_SMOOTH);
        timer=new Timer(10, this);
        timer.start();
    }
    public void paint(Graphics g){
        super.paint(g); // paint background
        Graphics2D g2D = (Graphics2D) g;
       // g2D.drawImage(backGround,0,0,null);
        society.move();
        int recoveringPatient = 0;
        ArrayList infectedPeople = null;
        update(infectedPeople,countOfInfected,countOfHealty,true,1,xCoordinates,yCoordinates,hospital,isDead,recoveringPatient);

        g2D.setColor(Color.black);

        for(int i=0;i<xCoordinates.size();i++){

            if(hospital.get(i).equals(false) && isDead.get(i).equals(false)){
               // g2D.drawImage(person,(int)xCoordinates.get(i),(int)yCoordinates.get(i),null);
                g2D.fillOval(5,5,2,2);
                g2D.drawOval((int)xCoordinates.get(i),(int)yCoordinates.get(i),5,5);

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator iterator = this.society.createIterator();


        while(iterator.hasNext()){
            Component component = (Component) iterator.next();
            try {
                if(component.isMask()==true){
                    component.move();
                    this.x=component.getXCoordinate();
                    this.y=component.getYCoordinate();
                }
            }catch (UnsupportedOperationException ex){

            }
        }
        repaint();
        repaint();
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public void update(ArrayList infectedPeople,int InfectedCount,int healtyCount, boolean death, int speed, ArrayList x, ArrayList y,ArrayList hospital,ArrayList isDead,int recoveringPatient) {
        this.hospital=hospital;
        this.countOfInfected=InfectedCount ;
        this.xCoordinates=x;
        this.yCoordinates=y;
        this.isDead=isDead;
    }

}
