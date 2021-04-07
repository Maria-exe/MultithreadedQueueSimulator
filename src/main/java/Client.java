public class Client implements Comparable<Client> {
    private Integer ID;
    private Integer arrivalTime; // timpul la care intra in coada
    private Integer finishTime; // timpul la care iese din coada = timpul de sosire + timpul de asteptare la coada + timpul de procesare
    private Integer processingPeriod; // timpul de procesare in fata cozii
    private Integer waitingPeriodOnChosenServer;
    private Integer queueID;

    public Client(Integer ID, Integer arrivalTime, Integer processingPeriod) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.finishTime = 0;
        this.processingPeriod = processingPeriod;
        this.waitingPeriodOnChosenServer = 0;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getFinishTime() {
        return finishTime;
    }

    public void setFinishTime() {
        this.finishTime = /*this.arrivalTime + */ processingPeriod + this.waitingPeriodOnChosenServer;
    }

    public Integer getProcessingPeriod() {
        return this.processingPeriod;
    }

    public void decProcessingPeriod(Integer processingPeriod) {
        this.processingPeriod -= processingPeriod;
    }

    public Integer getWaitingPeriodOnChosenServer() {
        return waitingPeriodOnChosenServer;
    }

    public void setWaitingPeriodOnChosenServer(Integer waitingPeriodOnChosenServer) {
        this.waitingPeriodOnChosenServer = waitingPeriodOnChosenServer;
    }

    public Integer getQueueID() {
        return queueID;
    }

    public void setQueueID(Integer queueID) {
        this.queueID = queueID;
    }

    @Override
    public int compareTo(Client c) {  // se ordoneaza clientii crescator, in functie de timpul de sosire
        if(this.arrivalTime > c.arrivalTime) return 1;
        else if (this.arrivalTime.equals(c.arrivalTime)) return 0;
        return -1;
    }

    public void represent() {
        Singleton.getInstance().writeToFile("("+getID()+", "+ getArrivalTime() + ", " + getProcessingPeriod()+") ");
    }
    public void representt() {
        System.out.print("("+getID()+", "+ getArrivalTime() + ", " + getProcessingPeriod()+") ");
    }
    public void printClientToGUI() {
        Singleton.getInstance().writeToUI("("+getID()+", "+ getArrivalTime() + ", " + getProcessingPeriod()+") ");
    }
}

