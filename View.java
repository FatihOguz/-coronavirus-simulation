import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class View extends JFrame implements Observer{

    private  Subject society;
    private MyPanel panel;
    private JFrame window=new JFrame("INFECTED AND DEAD GRAPHICS");
    int startStop=0;
    private JButton startOrStop = new JButton("START/STOP");
    private TextField countOfInfected=new TextField("infected " , 20);
    private TextField countOfHealty=new TextField("healthy " , 20);
    private TextField countOfHospital=new TextField("hospital " , 20);
    private TextField countOfDead=new TextField("DEAD " , 20);
    private TextField countOfRecoveringPatient=new TextField("Recovering Patient " , 20);
    private TextField addHealthy=new TextField("Add Healthy " , 20);
    private JButton addButton = new JButton("ADD BUTTON HEALTHY");
    private TextField addInfected=new TextField("Add Infected " , 20);
    private JButton addButtonInfected = new JButton("ADD BUTTON INFECTED");
    private JButton zFactorButton = new JButton("z Factor button");
    private TextField ZFactor=new TextField("Z FACTOR " , 20);
    private JButton rFactorButton = new JButton("R Factor button");
    private TextField RFactor=new TextField("R FACTOR " , 20);
    private JPanel Jpanel1 = new JPanel();
   private DisplayGraphicsInfected displayGraphics ;
   private DisplayGraphicsDead displayGraphicsDead;
    public View(Subject society){
        System.out.println("WIEV CONSTROCTUR");
        this.society = society;
        Society healthySociety=new Society(2,6,3,true,false);
        Society infectedSociety=new Society(4,8,4,true,true);
        society.addComponent(healthySociety);
        society.addComponent(infectedSociety);
        displayGraphics = new DisplayGraphicsInfected(society);
        displayGraphicsDead = new DisplayGraphicsDead(society);
        society.registerObserver(this);
        panel=new MyPanel( society);
        super.setLayout(new GridLayout(1,2));
       this.setSize(2000,1200);
       window.setSize(2000,1200);

       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jpanel1.setLayout(new GridLayout(7,2));
        Jpanel1.setBounds(0,0,1000,600);
        countOfDead.setBounds(100,100,80,30);
        countOfHealty.setBounds(100,100,80,30);
        countOfHospital.setBounds(100,100,80,30);
        countOfInfected.setBounds(100,100,80,30);
        countOfRecoveringPatient.setBounds(100,100,80,30);



        Jpanel1.add(addButton);
        Jpanel1.add(addHealthy);
        Jpanel1.add(addButtonInfected);
        Jpanel1.add(addInfected);

        Jpanel1.add(zFactorButton);
        Jpanel1.add(ZFactor);

        Jpanel1.add(rFactorButton);
        Jpanel1.add(RFactor);


        Jpanel1.add(countOfRecoveringPatient);
        Jpanel1.add(countOfDead);
        Jpanel1.add(countOfHealty);
        Jpanel1.add(countOfHospital);
        Jpanel1.add(countOfInfected);
        Jpanel1.add(startOrStop);


        startOrStop.setVisible(true);
        this.getContentPane().add(panel);
        this.getContentPane().add(Jpanel1);

        this.pack();
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        window.setLayout(new GridLayout(1,2));

        displayGraphics.setVisible(true);
        displayGraphicsDead.setVisible(true);

        window.getContentPane().add(displayGraphics);

        window.pack();

        window.setLocationRelativeTo(null);

        window.setVisible(true);







        Jpanel1.setVisible(true);
        countOfRecoveringPatient.setVisible(true);
        countOfHealty.setVisible(true);
        countOfHospital.setVisible(true);
        countOfDead.setVisible(true);
        countOfInfected.setVisible(true);
        addHealthy.setVisible(true);
        addInfected.setVisible(true);
        zFactorButton.setVisible(true);
        ZFactor.setVisible(true);
        rFactorButton.setVisible(true);
        RFactor.setVisible(true);


        window.add(displayGraphicsDead);

        startOrStop.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                if(startStop%2==0){
                    panel.getTimer().stop();;
                }
                else
                {
                    panel.getTimer().restart();
                }
                startStop++;



            }
        });

        addInfected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(addInfected.getText());
            }
        });
        addButtonInfected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int collisionTime = random.nextInt(5)+1;
                int socialDistance = random.nextInt(9)+1;
                int speed=random.nextInt(5)+1;
                boolean isMask = random.nextBoolean();
                System.out.println("-----------> " + parseInt(addInfected.getText()));
                for(int i=0;i<parseInt(addInfected.getText());i++){
                    infectedSociety.addComponent(new infectedSociety(collisionTime,socialDistance,speed,isMask,true));
                }
            }
        });
        ZFactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(ZFactor.getText());
            }
        });
        zFactorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double factor = Double.parseDouble(ZFactor.getText());
                society.setZFactor(factor);
            }
        });

        RFactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(RFactor.getText());
            }
        });
        rFactorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double factor = Double.parseDouble(RFactor.getText());
                society.setRFactor(factor);
            }
        });
        addHealthy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(addHealthy.getText());
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int collisionTime = random.nextInt(5)+1;
                int socialDistance = random.nextInt(9)+1;
                int speed=random.nextInt(5)+1;
                boolean isMask = random.nextBoolean();
                System.out.println("-----------> " + parseInt(addHealthy.getText()));
                for(int i=0;i<parseInt(addHealthy.getText());i++){
                    healthySociety.addComponent(new healthySociety(collisionTime,socialDistance,speed,isMask,false));
                }
            }
        });
        addButton.setVisible(true);
        addButtonInfected.setVisible(true);
    }




    @Override
    public void update(ArrayList infectedPeople,int InfectedCount, int healtyCount, boolean death, int speed, ArrayList x, ArrayList y,ArrayList hospital,ArrayList isDead,int recoveringPatient) {
        countOfInfected.setText("INFECTED : " + Integer.toString(InfectedCount));
        countOfHealty.setText("HEALTHY : " + Integer.toString(healtyCount));

        int countHospital=0;
        int deadCount=0;
        for(int i=0;i<hospital.size();i++){
            if(hospital.get(i).equals(true)){
                countHospital++;
            }
            if(isDead.get(i).equals(true)){
                deadCount++;
            }
        }
        countOfHospital.setText("HOSPITAL : " + Integer.toString(countHospital));
        countOfDead.setText("DEAD : " + Integer.toString(deadCount) ) ;
        countOfRecoveringPatient.setText("RECOVERING PATIENT : " + Integer.toString(recoveringPatient));
    }






}
