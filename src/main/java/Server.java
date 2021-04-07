import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Server implements Runnable {
    private BlockingQueue<Client> queue;
    private Integer waitingPeriod; //  = nr de clienti aflati deja in coada coada
    private AtomicReference<Client> clientFinished = new AtomicReference<>();
    private AtomicBoolean clientWasProcessed = new AtomicBoolean();
    private Integer sumOfWaitingTimeInQueue = 0;
    private Integer sumOfServiceTimeInQueue = 0;
    private Integer clientsInQueue = 0;
    private Integer maxInQueue = Integer.MIN_VALUE;
    public AtomicReference<Client> getClientFinished() {
        return clientFinished;
    }

    public Boolean getClientWasProcessed() {
        return clientWasProcessed.get();
    }

    public Integer getWaitingPeriod() {
        return waitingPeriod;
    }

    public BlockingQueue<Client> getClients() {
        return queue;
    }

    public Server(int numberOfClients) {
        this.queue = new ArrayBlockingQueue<Client>(numberOfClients);
        this.waitingPeriod = 0;
    }

    public void addClient(Client client) throws InterruptedException { // adauga client in coada
        client.setWaitingPeriodOnChosenServer(this.getWaitingPeriod());
        this.queue.put(client);
        this.clientsInQueue += 1;
        this.waitingPeriod++;
    }

    public void takeClient() { //process client
        try {
            if (!this.queue.isEmpty()) {
                Client taken = this.queue.peek();
                this.clientFinished.set(taken);
                taken.setFinishTime();
                this.sumOfWaitingTimeInQueue += taken.getFinishTime();
                this.sumOfServiceTimeInQueue += taken.getProcessingPeriod();
                Thread.sleep(1000);  // the client arrives at front of the queue
                while (taken.getProcessingPeriod() >1) {
               // for (int i=0;i<taken.getProcessingPeriod();i++) { // the client is being processed for a time equal to it's processingPeriod
                    taken.decProcessingPeriod(1);
                    Thread.sleep(1000);
                }
                clientWasProcessed.set(true);
                this.waitingPeriod--;
                this.queue.take(); // the client is removed from the queue at finish time
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            takeClient();
        }
    }

    public Integer getSumOfServiceTime(){
        return 0;
    }

    public Integer getSumOfWaitingTimeInQueue() {
        return this.sumOfWaitingTimeInQueue;
    }
    public Integer getSumOfServiceTimeInQueue() {
        return this.sumOfServiceTimeInQueue;
    }

    public void printQueue(int index){
        System.out.print("Queue "+ index +":");
        for (Client c: this.queue) {
            System.out.print(" ("+c.getID()+", "+c.getArrivalTime()+", "+c.getProcessingPeriod()+")");
        }
        System.out.println();
    }

    public void printQueueToFile(int index){
        Singleton.getInstance().writeToFile("Queue "+ index +": ");
        if(this.queue.isEmpty()) {
            Singleton.getInstance().writeLineToFile("closed");
        }
        else{
            for (Client c: this.queue) {
                Singleton.getInstance().writeToFile(" ("+c.getID()+", "+c.getArrivalTime()+", "+c.getProcessingPeriod()+")");
            }
            Singleton.getInstance().writeLineToFile("");
        }
    }

    public void printQueueToGUI(int index){
        Singleton.getInstance().writeToUI("Queue "+ index +": ");
        if(this.queue.isEmpty()) {
            Singleton.getInstance().writeToUI("closed\n");
        }
        else{
            for (Client c: this.queue) {
                c.printClientToGUI();
            }
            Singleton.getInstance().writeToUI("\n");
        }
    }
}
