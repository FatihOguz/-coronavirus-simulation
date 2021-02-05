import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Mediator {
    private Subject society;
    long startTime;
    private int time =0;
    private boolean initFlag=true;
    private Hospital hospital;
    public Mediator(Subject Society)
    {
        this.society=Society;

        startTime=System.currentTimeMillis();


    }


    public void menage(){
        hospital=Hospital.getInstance(this.society);
        hospital.setNumberOfVentilators(society.size()/100);
       // System.out.println("SİZE OF VANTİLATOR " +hospital.getNumberOfVentilators());

        if(initFlag==false){

            fixRemoveHospitalArray();
            removeInfectedArrayList();
            removeArrayListDead();
        }
        initFlag=false;
        Iterator iterator = society.createIterator();


        while(iterator.hasNext()){
            Subject component = (Subject) iterator.next();

            if(component instanceof  healthySociety || component instanceof  infectedSociety){

                int componentX=component.getXCoordinate();
                int componentY=component.getYCoordinate();
                int componentSocialDistance = component.getSocialDistance();


                Iterator otherIterator = society.createIterator();
                while (otherIterator.hasNext()){
                    Subject otherComponent = (Subject) otherIterator.next();
                    if(otherComponent instanceof healthySociety || otherComponent instanceof infectedSociety){
                        int otherComponentX=otherComponent.getXCoordinate();
                        int otherComponentY=otherComponent.getXCoordinate();
                        int otherComponentSocialDistance=otherComponent.getSocialDistance();

                        int socialDistance=Math.min(componentSocialDistance,otherComponentSocialDistance);
                        double componentMask;
                        double otherComponentMask;
                        double R = society.getRFactor();
                        double probInfected;
                        int componentCollisionTime=component.getCollisionTime();
                        int otherComponentCollisionTime = otherComponent.getCollisionTime();
                        int collisionTime = Math.max(componentCollisionTime,otherComponentCollisionTime);

                        if(component.isMask()){
                            componentMask=0.2;
                        }
                        else{
                            componentMask=1;
                        }
                        if(otherComponent.isMask()){
                            otherComponentMask=0.2;
                        }
                        else{
                            otherComponentMask=1;
                        }
                        boolean cInfected = component.isInfected();
                        boolean ocInfected=otherComponent.isInfected();

                        if(cInfected || ocInfected){

                            if(componentX == otherComponentX + socialDistance ){
                                if(componentY==otherComponentY){
                                    //System.out.println("ıntersection");
                                    int time =  (int)( ((System.currentTimeMillis()  ) -  (component.getInterSectionTime() )) / 1000);
                                    if(time>5){
                                        component.setInterSection(false);
                                        Random random = new Random();
                                        int x = random.nextInt(500);
                                        int y=random.nextInt(500);
                                        component.setCoordinateAfterInterSection(x,y);
                                    }
                                    component.setInterSection(true);
                                    probInfected = R * (1 + (collisionTime / 10 )) *
                                            componentMask * otherComponentMask * (1-(socialDistance/10));

                                    double societyProbInfected = society.getProbInfectedFactor();

                                    if(probInfected>=societyProbInfected){
                                       // System.out.println(probInfected);
                                        component.setIsInfected(true);
                                        otherComponent.setIsInfected(true);
                                    }

                                }
                            }
                            else if(componentX==otherComponentX - socialDistance){
                                if(componentY==otherComponentY){
                                    //System.out.println("intersection2 ");
                                    int time =  (int)( ((System.currentTimeMillis()  ) -  (component.getInterSectionTime() )) / 1000);
                                    if(time>5){
                                        component.setInterSection(false);
                                        Random random = new Random();
                                        int x = random.nextInt(500);
                                        int y=random.nextInt(500);
                                        component.setCoordinateAfterInterSection(x,y);
                                    }
                                    component.setInterSection(true);
                                    probInfected = R * (1 + (collisionTime / 10 )) *
                                            componentMask * otherComponentMask * (1-(socialDistance/10));

                                    double societyProbInfected = society.getProbInfectedFactor();

                                    if(probInfected>=societyProbInfected){
                                       // System.out.println(probInfected);
                                        component.setIsInfected(true);
                                        otherComponent.setIsInfected(true);
                                    }
                                }
                            }
                            else if(componentY==otherComponentY + socialDistance){
                                if(componentX==otherComponentX){
                                    //System.out.println("intersection 3");
                                    int time =  (int)( ((System.currentTimeMillis()  ) -  (component.getInterSectionTime() )) / 1000);
                                    if(time>5){
                                        component.setInterSection(false);
                                        Random random = new Random();
                                        int x = random.nextInt(500);
                                        int y=random.nextInt(500);
                                        component.setCoordinateAfterInterSection(x,y);
                                    }
                                    component.setInterSection(true);
                                    probInfected = R * (1 + (collisionTime / 10 )) *
                                            componentMask * otherComponentMask * (1-(socialDistance/10));

                                    double societyProbInfected = society.getProbInfectedFactor();

                                    if(probInfected>=societyProbInfected){
                                       // System.out.println(probInfected);
                                        component.setIsInfected(true);
                                        otherComponent.setIsInfected(true);
                                    }
                                }
                            }
                            else if(componentY==otherComponentY - socialDistance){
                                if (componentX==otherComponentX){
                                    //System.out.println("intersection 4");
                                    int time =  (int)( ((System.currentTimeMillis()  ) -  (component.getInterSectionTime() )) / 1000);
                                    if(time>5){
                                        component.setInterSection(false);
                                        Random random = new Random();
                                        int x = random.nextInt(500);
                                        int y=random.nextInt(500);
                                        component.setCoordinateAfterInterSection(x,y);
                                    }
                                    component.setInterSection(true);
                                    probInfected = R * (1 + (collisionTime / 10 )) *
                                            componentMask * otherComponentMask * (1-(socialDistance/10));

                                    double societyProbInfected = society.getProbInfectedFactor();

                                    if(probInfected>=societyProbInfected){
                                       // System.out.println(probInfected);
                                        component.setIsInfected(true);
                                        otherComponent.setIsInfected(true);
                                    }
                                }
                            }


                        }

                    }

                }

            }

        }
        time = (int)( ((System.currentTimeMillis()  ) -  (startTime )) / 1000);


        //System.out.println("timer " +time);



       infectedArrayList();
        goHospital();
        fixAddHospitalArray();
        recoveringPatient();
        fixArrayListDead();
       // System.out.println("size " + society.getPeopleInHospital().size() + " " + society.getInfectedOrHealtyBoolean().size() +" " + society.size());
       // removeInfectedArrayList();
    }


    public void infectedArrayList() {

        Iterator iterator = society.createIterator();


        while (iterator.hasNext()) {
            Subject component = (Subject) iterator.next();

            if (component instanceof healthySociety || component instanceof infectedSociety) {

                society.getInfectedOrHealtyBoolean().add(component.isInfected());


            }

        }
    }

    public void removeInfectedArrayList(){
        Iterator iterator = society.createIterator();

        int i = 0 ;
        while (iterator.hasNext()) {
            Subject component = (Subject) iterator.next();

            if (component instanceof healthySociety || component instanceof infectedSociety) {
                if(society.getInfectedOrHealtyBoolean().size()>0)
                society.getInfectedOrHealtyBoolean().remove(0);
                i++;


            }

        }

    }


    public void goHospital(){
        Iterator iterator = society.createIterator();

        int i = 0 ;
        while (iterator.hasNext()) {
            Subject component = (Subject) iterator.next();
            if (component instanceof healthySociety || component instanceof infectedSociety) {
                boolean flag = component.isInfected();

                if(flag == true){

                    long time1 = (int)( ((System.currentTimeMillis()  ) -  (component.getInfectedStartTime() )) / 1000);
                    if(time1 >=25){
                        int numberOfFullVentilators = this.hospital.getNumberOfFullVentilator();
                        int numberOfVentialtors = this.hospital.getNumberOfVentilators();
                        //System.out.println("number of  " + numberOfVentialtors + " " + numberOfFullVentilators);
                        if(numberOfFullVentilators<numberOfVentialtors && component.getInHospital()==false){
                            component.setInHospital(true);
                            component.setHospitalTime();
                            this.hospital.setNumberOfFullVentilator(numberOfFullVentilators+1);
                        }

                    }
                }
            }
            i++;
        }
    }

    public void fixAddHospitalArray(){
        Iterator iterator = society.createIterator();

        int i = 0 ;
        while (iterator.hasNext()) {
            Subject component = (Subject) iterator.next();
            if (component instanceof healthySociety || component instanceof infectedSociety) {

                society.getPeopleInHospital().add(component.getInHospital());
                //System.out.println("mediator fiz " + society.getPeopleInHospital());
            }
            i++;
        }


    }


    public void fixRemoveHospitalArray(){
        Iterator iterator = society.createIterator();

        int i = 0 ;
        while (iterator.hasNext()) {
            Subject component = (Subject) iterator.next();
            if (component instanceof healthySociety || component instanceof infectedSociety) {
                if(society.getPeopleInHospital().size()>0)
                society.getPeopleInHospital().remove(0);
            }
            i++;
        }


    }

    public void recoveringPatient(){
        Iterator iterator = society.createIterator();

        int i = 0 ;
        while (iterator.hasNext()) {
            Subject component = (Subject) iterator.next();
            if (component instanceof healthySociety || component instanceof infectedSociety) {

                if(component.getInHospital()){

                    long time1 = (int)( ((System.currentTimeMillis()  ) -  (component.getHospitalTime() )) / 1000);
                    if(time1 >=10){
                      //  System.out.println("recoverring patient");
                        component.setInHospital(false);
                        component.setIsInfected(false);
                        hospital.setNumberOfFullVentilator(hospital.getNumberOfFullVentilator()-1);
                        hospital.setPatientRecovering(hospital.getPatientRecovering() + 1);
                        society.setRecoveringPatient(hospital.getPatientRecovering());
                    }
                }
            }
            i++;
        }


    }

    public void fixArrayListDead(){
        Iterator iterator = society.createIterator();

        int i = 0 ;
        while (iterator.hasNext()) {
            Subject component = (Subject) iterator.next();

            if (component instanceof healthySociety || component instanceof infectedSociety) {
                double probDead = 100 * ( 1 - society.getZ());
                long time1 = (int)( ((System.currentTimeMillis()  ) -  (component.getInfectedStartTime() )) / 1000);
               // System.out.println("prob dead " + probDead);
               // System.out.println("time1 " + time1);
                if(time1>=probDead && component.isInfected() ){
                    component.setDead(true);
                }
                this.society.getIsDead().add(component.isDead());
            }
            i++;
        }


    }
    public void removeArrayListDead(){
        Iterator iterator = society.createIterator();

        int i = 0 ;
        while (iterator.hasNext()) {
            Subject component = (Subject) iterator.next();
            if (component instanceof healthySociety || component instanceof infectedSociety) {
                if(society.getIsDead().size()>0)
                this.society.getIsDead().remove(0);
            }
            i++;
        }


    }



}

