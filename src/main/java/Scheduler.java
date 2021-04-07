import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler {
    private List<Server> servers;

    public Scheduler (Integer maxNumOfServers, Integer maxNumOfClientsPerServer){
        this.servers = Collections.synchronizedList(new ArrayList<Server>());
        for (int i = 0; i<maxNumOfServers;i++){
            this.servers.add(new Server(maxNumOfClientsPerServer));
            Thread t = new Thread(this.servers.get(i));
            t.start();
        }
    }

    public void dispatchClient(Client c){
        addTask(this.servers,c);
    }

    public void addTask(List<Server> servers, Client c) {
        Server minWaitTimeServer = servers.get(0);
        int minTime = Integer.MAX_VALUE;
        for(int i=0;i<servers.size();i++){ // send client to the queue with the minimum waiting time
            if(servers.get(i).getWaitingPeriod().intValue() < minTime) {
                minTime = servers.get(i).getWaitingPeriod().intValue();
                minWaitTimeServer = servers.get(i);
            }
        }
        try {
            minWaitTimeServer.addClient(c);
            c.setQueueID(servers.indexOf(minWaitTimeServer));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Server> getServers() {
        return servers;
    }
}
