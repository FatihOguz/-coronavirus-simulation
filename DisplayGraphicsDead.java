import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class DisplayGraphicsDead extends JPanel implements ActionListener,Observer {

    private long time =0;
    Subject society;
    final int PANEL_WIDTH=1000;
    final int PANEL_HEIGHT=600;
    Image person ;
    Image backGround;
    Timer timer;
    int xVelocity=1;
    int yVelocity=1;
    int x=150;
    int y=150;
    int countOfInfected=0;
    int countOfHealty=0;
    ArrayList xCoordinates=new ArrayList();
    ArrayList hospital=new ArrayList();
    ArrayList yCoordinates=new ArrayList();
    ArrayList isDead=new ArrayList();
    private ArrayList<Integer> yValues=new ArrayList();
    private ArrayList<Integer> xValues=new ArrayList();
    public DisplayGraphicsDead(Subject sociecty){
        this.society=sociecty;
        sociecty.registerObserver(this);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.white);
        person = new ImageIcon("enemy.jpeg").getImage();
        person=person.getScaledInstance(5,5, Image.SCALE_SMOOTH);
        Image image1=new ImageIcon("world.jpeg").getImage();
        backGround=image1.getScaledInstance(PANEL_WIDTH,PANEL_HEIGHT,Image.SCALE_SMOOTH);
        timer=new Timer(10,this);
        timer.start();
        time = System.currentTimeMillis();
        this.setBackground(Color.ORANGE);


    }
    public void  paint(Graphics g){
        super.paint(g); // paint background
        Graphics2D g2D = (Graphics2D) g;
       // g2D.drawImage(backGround,0,0,null);

        int recoveringPatient = 0;
        ArrayList infectedPeople=null;
        update(infectedPeople,countOfInfected,countOfHealty,true,1,xCoordinates,yCoordinates,hospital,isDead,recoveringPatient);
        int time1 =(int)( (System.currentTimeMillis() - time)/1000);
        int deadCount=0;
        for(int i=0;i<isDead.size();i++){
            if(isDead.get(i).equals(true)){
                deadCount++;
            }
        }
        System.out.println("count of dead" +deadCount);
        yValues.add(deadCount);
        xValues.add(time1);

        g2D.setColor(Color.red);
        for(int i=0;i<yValues.size();i++){
            //g2D.drawImage(person, xValues.get(i),600-  yValues.get(i),null);
            g2D.drawOval(xValues.get(i),600-  yValues.get(i),5,5);
        }


    }

    @Override
    public void update(ArrayList infectedPeople,int InfectedCount, int healtyCount, boolean death, int speed, ArrayList x, ArrayList y, ArrayList hospital, ArrayList isDead, int recoveringPatient) {
        this.countOfInfected=InfectedCount;
        this.isDead=isDead;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
