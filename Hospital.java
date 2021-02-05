public class Hospital {
    private volatile static Hospital uniqueInstance;
    private int numberOfVentilators;
    private int numberOfFullVentilator;
    private int patientRecovering =0;
    private Hospital(Subject society) {
        int size=society.size();
        System.out.println("size " + size);
        this.numberOfVentilators=size/100;
    }
    public static Hospital getInstance(Subject society) {

        if (uniqueInstance == null) {

            synchronized (Hospital.class) {
                if (uniqueInstance == null) {

                    uniqueInstance = new Hospital(society);

                }
            }
        }
        return uniqueInstance;
    }
    public int getNumberOfVentilators() {
        return numberOfVentilators;
    }
    public void setNumberOfVentilators(int numberOfVentilators) {
        this.numberOfVentilators = numberOfVentilators;
    }
    public int getNumberOfFullVentilator() {
        return numberOfFullVentilator;
    }
    public void setNumberOfFullVentilator(int numberOfFullVentilator) {
        this.numberOfFullVentilator = numberOfFullVentilator;
    }
    public int getPatientRecovering() {
        return patientRecovering;
    }
    public void setPatientRecovering(int patientRecovering) {
        this.patientRecovering = patientRecovering;
    }
}