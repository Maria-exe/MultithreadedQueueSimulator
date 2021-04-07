import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulationManager implements Runnable {
    // data from UI
    public Integer timeLimit;
    public Integer numOfClients;
    public Integer numOfServers;
    public Integer minArrivalTime;
    public Integer maxArrivalTime;
    public Integer minServiceTime;
    public Integer maxServiceTime;
    private Scheduler scheduler;
    private List<Client> clients;
    private GUI simulationFrame;
    private Controller controller;
    private AtomicBoolean running;



    public SimulationManager() throws IOException {
        simulationFrame = new GUI();
        controller = new Controller(simulationFrame,this);
        simulationFrame.setVisible(true);
        while(controller.isStartInvoked() == false){ // make main thread wait until input data is submitted to the SimulationManager
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {
            }
        }
        this.generateNRandomClients();
        this.scheduler = new Scheduler(numOfServers,10);
        this.running = new AtomicBoolean(false);
    }

    private void generateNRandomClients() {
        this.clients = Collections.synchronizedList(new ArrayList<Client>());
        for(int i = 0; i < this.numOfClients; i++){
            Random r = new Random(); // generate random client
            Client c = new Client(i, r.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime,
                    r.nextInt(maxServiceTime - minServiceTime) + minServiceTime);
            this.clients.add(c); // add the randomly generated client to the list of clients
        }
        Collections.sort(clients); // sort clients by arrivalTime
    }

    private void sendStatusToFile(int currentTime) {
        Singleton.getInstance().writeLineToFile("Time " + currentTime+": ");
        Singleton.getInstance().writeToFile("Waiting clients: ");
        for(Iterator<Client> it = this.clients.iterator();it.hasNext();){
            it.next().represent();
        }
        Singleton.getInstance().writeLineToFile("");
        for(Iterator<Server> it = this.scheduler.getServers().iterator(); it.hasNext();){
            Server currentServer = it.next();
            currentServer.printQueueToFile(this.scheduler.getServers().indexOf(currentServer));
        }
    }



    public double computeAvgWaitingTimeInQueue(){
        Integer total = 0;
        for(Iterator<Server> it = this.scheduler.getServers().iterator(); it.hasNext();){
            Server currentServer = it.next();
            total += currentServer.getSumOfWaitingTimeInQueue();
        }
        double avg = (double) total / (double) (this.numOfClients - this.clients.size());
        Singleton.getInstance().writeToUI("\nAverage   waiting   time: " + avg + "\n");
        Singleton.getInstance().writeToFile("\nAverage   waiting   time: " + avg + "\n");
        this.simulationFrame.getLblAvgWait().setText(String.valueOf(avg));
        return avg;
    }

    public double computeAvgServiceTimeInQueue(){
        Integer totalsrv = 0;
        for(Iterator<Server> it = this.scheduler.getServers().iterator(); it.hasNext();){
            Server currentServer = it.next();
            totalsrv += currentServer.getSumOfServiceTimeInQueue();
        }
        double avg = (double) totalsrv / (double) (this.numOfClients - this.clients.size());
        Singleton.getInstance().writeToUI("\nAverage  service  time: " + avg + "\n");
        Singleton.getInstance().writeToFile("\nAverage  service   time: " + avg + "\n");
        this.simulationFrame.getLblAvgServ().setText(String.valueOf(avg));
        return avg;
    }


    private void sendStatusToUI(int currentTime) {
        Singleton.getInstance().setTextArea(this.simulationFrame.getTextArea());
        Singleton.getInstance().writeToUI("Time " + currentTime+": \n");
        Singleton.getInstance().writeToUI("Waiting clients: ");
        for(Iterator<Client> it = this.clients.iterator();it.hasNext();){
            it.next().printClientToGUI();
        }
        Singleton.getInstance().writeToUI("\n");
        for(Iterator<Server> it = this.scheduler.getServers().iterator(); it.hasNext();){
            Server currentServer = it.next();
            currentServer.printQueueToGUI(this.scheduler.getServers().indexOf(currentServer));
        }
    }
    private void sendStatus(int currentTime) {

        System.out.println("Waiting clients: ");
        for(Iterator<Client> it = this.clients.iterator();it.hasNext();){
            it.next().representt();
        }
        System.out.println();
        for(Iterator<Server> it = this.scheduler.getServers().iterator(); it.hasNext();){
            Server currentServer = it.next();
            currentServer.printQueue(this.scheduler.getServers().indexOf(currentServer));
        }
    }

    public void setRunning(Boolean b) {
        this.running.set(b);
    }

    @Override
    public void run() {
        int currentTime = 0; // tSimulation
        while(currentTime < timeLimit && running.get()) {
            System.out.println(currentTime);
            for (Iterator<Client> it = clients.iterator(); it.hasNext();) {
                Client c = it.next();
                if(c.getArrivalTime() == currentTime){
                     scheduler.dispatchClient(c);
                     it.remove();
                    }
            }
            sendStatusToUI(currentTime);
            sendStatusToFile(currentTime);
            sendStatus(currentTime);
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SimulationManager sm = new SimulationManager();
        Thread main = new Thread(sm);
        sm.setRunning(true);
        main.start();
        main.join();
        sm.computeAvgWaitingTimeInQueue();
        sm.computeAvgServiceTimeInQueue();
    }
}

