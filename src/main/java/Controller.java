import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private GUI simFrame;
    private SimulationManager main;
    private boolean initialized;
    private boolean startInvoked;

    public Controller(GUI simFrame, SimulationManager main) {
        this.simFrame = simFrame;
        this.main = main;
        this.initialized = false;
        this.startInvoked = false;
        simFrame.getStartSimBtn().addActionListener(new StartListener());
        simFrame.getEndSimBtn().addActionListener(new StopListener());
        simFrame.getSubmitBtn().addActionListener(new SubmitListener());
    }

    /**
     * starts the main thread that executes the simulation manager
     */
    class StartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(Controller.this.isInitialized()) // the simulation can only start after the input data has been submitted to the SimulationManager
                Controller.this.startInvoked = true;
        }
    }

    /**
     * stop the simulation and set resulted calculations
     */
    class StopListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(main.timeLimit);
            Singleton.getInstance().getMyWriter().close();
            System.out.println(Singleton.getInstance().getMyFile().canWrite());
            Controller.this.main.setRunning(false);
        }
    }
    /**
    * fetch data from UI and send it to the SimulationManager
    * */
    class SubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Integer numOfClients = Integer.valueOf(simFrame.getClientNum().getText());
            Integer numOfServers = Integer.valueOf(simFrame.getQueueNum().getText());
            Integer timeLimit = Integer.valueOf(simFrame.getSimInterval().getText());
            Integer minArrivalTime = Integer.valueOf(simFrame.getMinArrivalTime().getText());
            Integer maxArrivalTime = Integer.valueOf(simFrame.getMaxArrivalTime().getText());
            Integer minServiceTime = Integer.valueOf(simFrame.getMinServiceTime().getText());
            Integer maxServiceTime = Integer.valueOf(simFrame.getMaxServiceTime().getText());
            Controller.this.main.timeLimit = timeLimit;
            Controller.this.main.numOfServers = numOfServers;
            Controller.this.main.numOfClients = numOfClients;
            Controller.this.main.minArrivalTime = minArrivalTime;
            Controller.this.main.maxArrivalTime = maxArrivalTime;
            Controller.this.main.minServiceTime = minServiceTime;
            Controller.this.main.maxServiceTime = maxServiceTime;
            Controller.this.initialized = true;

        }
    }

    public GUI getSimFrame() {
        return simFrame;
    }

    public SimulationManager getMain() {
        return main;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public boolean isStartInvoked() {
        return startInvoked;
    }
}

