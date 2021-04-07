import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JTextField simInterval;
    private JTextField minArrivalTime;
    private JTextField maxArrivalTime;
    private JTextField minServiceTime;
    private JTextField maxServiceTime;
    private JButton startSimBtn;
    private JButton endSimBtn;
    private JButton submitBtn;
    private JTextArea textArea;
    private JLabel lblPeak;
    private JLabel lblAvgServ;
    private JLabel lblAvgWait;
    private JTextField clientNum;
    private JTextField queueNum;
    public GUI() {
        initialize();
    }

    private void initialize() {
        this.getContentPane().setBackground(SystemColor.windowBorder);
        this.setBounds(100, 100, 1044, 408);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblClientNum = new JLabel("Number of clients:");
        lblClientNum.setBounds(28, 71, 117, 25);
        lblClientNum.setForeground(new Color(244, 164, 96));
        lblClientNum.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));

        JLabel lblQueueNum = new JLabel("Number of queues:");
        lblQueueNum.setBounds(28, 102, 117, 25);
        lblQueueNum.setForeground(new Color(244, 164, 96));
        lblQueueNum.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));

        JLabel lblSimulationInterval = new JLabel("Simulation interval: ");
        lblSimulationInterval.setBounds(28, 133, 144, 25);
        lblSimulationInterval.setForeground(new Color(244, 164, 96));
        lblSimulationInterval.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));

        JLabel lblMinArrivalTime = new JLabel("Min arrival time:");
        lblMinArrivalTime.setBounds(28, 164, 133, 25);
        lblMinArrivalTime.setForeground(new Color(244, 164, 96));
        lblMinArrivalTime.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));

        JLabel lblMaxArrivalTime = new JLabel("Max arrival time:");
        lblMaxArrivalTime.setBounds(28, 195, 117, 25);
        lblMaxArrivalTime.setForeground(new Color(244, 164, 96));
        lblMaxArrivalTime.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));

        JLabel lblMinServiceTime = new JLabel("Min service time:");
        lblMinServiceTime.setBounds(28, 226, 213, 25);
        lblMinServiceTime.setForeground(new Color(244, 164, 96));
        lblMinServiceTime.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));

        JLabel lblMaxServiceTime = new JLabel("Max service time: ");
        lblMaxServiceTime.setBounds(28, 258, 117, 25);
        lblMaxServiceTime.setForeground(new Color(244, 164, 96));
        lblMaxServiceTime.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));

        clientNum = new JTextField();
        clientNum.setBounds(247, 71, 112, 26);
        clientNum.setToolTipText("Insert positive integer value");
        clientNum.setFont(new Font("Tahoma", Font.BOLD, 11));
        clientNum.setForeground(new Color(105, 105, 105));
        clientNum.setBackground(new Color(245, 222, 179));
        clientNum.setText("4");

        queueNum = new JTextField();
        queueNum.setBounds(247, 103, 112, 26);
        queueNum.setToolTipText("Insert positive integer value");
        queueNum.setFont(new Font("Tahoma", Font.BOLD, 11));
        queueNum.setForeground(new Color(105, 105, 105));
        queueNum.setBackground(new Color(245, 222, 179));
        queueNum.setText("2");

        simInterval = new JTextField();
        simInterval.setBounds(247, 135, 112, 26);
        simInterval.setToolTipText("Insert positive integer value");
        simInterval.setBackground(new Color(245, 222, 179));
        simInterval.setForeground(new Color(105, 105, 105));
        simInterval.setFont(new Font("Tahoma", Font.BOLD, 11));
        simInterval.setColumns(10);
        simInterval.setText("60");

        minArrivalTime = new JTextField();
        minArrivalTime.setBounds(247, 167, 112, 26);
        minArrivalTime.setToolTipText("Insert positive integer value");
        minArrivalTime.setBackground(new Color(245, 222, 179));
        minArrivalTime.setForeground(new Color(105, 105, 105));
        minArrivalTime.setFont(new Font("Tahoma", Font.BOLD, 11));
        minArrivalTime.setColumns(10);
        minArrivalTime.setText("2");

        maxArrivalTime = new JTextField();
        maxArrivalTime.setBounds(247, 199, 112, 26);
        maxArrivalTime.setToolTipText("Insert positive integer value");
        maxArrivalTime.setBackground(new Color(245, 222, 179));
        maxArrivalTime.setForeground(new Color(105, 105, 105));
        maxArrivalTime.setFont(new Font("Tahoma", Font.BOLD, 11));
        maxArrivalTime.setColumns(10);
        maxArrivalTime.setText("30");

        minServiceTime = new JTextField();
        minServiceTime.setBounds(247, 231, 112, 26);
        minServiceTime.setToolTipText("Insert positive integer value");
        minServiceTime.setBackground(new Color(245, 222, 179));
        minServiceTime.setForeground(new Color(105, 105, 105));
        minServiceTime.setFont(new Font("Tahoma", Font.BOLD, 11));
        minServiceTime.setColumns(10);
        minServiceTime.setText("2");

        maxServiceTime = new JTextField();
        maxServiceTime.setBounds(247, 264, 112, 26);
        maxServiceTime.setToolTipText("Insert positive integer value");
        maxServiceTime.setBackground(new Color(245, 222, 179));
        maxServiceTime.setForeground(new Color(105, 105, 105));
        maxServiceTime.setFont(new Font("Tahoma", Font.BOLD, 11));
        maxServiceTime.setColumns(10);
        maxServiceTime.setText("4");

        submitBtn = new JButton("Submit input data");
        submitBtn.setBounds(442, 121, 135, 36);
        submitBtn.setForeground(new Color(244, 164, 96));
        submitBtn.setBackground(new Color(0, 0, 0));
        submitBtn.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));

        startSimBtn = new JButton("Start simulation");
        startSimBtn.setBounds(442, 163, 135, 34);
        startSimBtn.setForeground(new Color(244, 164, 96));
        startSimBtn.setBackground(new Color(0, 0, 0));
        startSimBtn.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));

        endSimBtn = new JButton("End simulation\r\n");
        endSimBtn.setBounds(442, 209, 135, 36);
        endSimBtn.setForeground(new Color(244, 164, 96));
        endSimBtn.setBackground(new Color(0, 0, 0));
        endSimBtn.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));

        JLabel lblTitle = new JLabel("QUEUES SIMULATOR");
        lblTitle.setBounds(422, 23, 184, 19);
        lblTitle.setForeground(new Color(244, 164, 96));
        lblTitle.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(653, 71, 360, 229);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);


        JLabel lblPeakHour = new JLabel("Peak hour");
        lblPeakHour.setForeground(new Color(255, 255, 255));
        lblPeakHour.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        lblPeakHour.setBounds(944, 316, 67, 13);

        JLabel lblAverageWaitingTime = new JLabel("Average waiting time ");
        lblAverageWaitingTime.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        lblAverageWaitingTime.setForeground(new Color(255, 255, 255));
        lblAverageWaitingTime.setBounds(655, 316, 133, 13);


        JLabel lblAverageServiceTime = new JLabel("Average service time");
        lblAverageServiceTime.setForeground(new Color(255, 255, 255));
        lblAverageServiceTime.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        lblAverageServiceTime.setBounds(800, 316, 144, 13);


        lblAvgWait = new JLabel("");
        lblAvgWait.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvgWait.setForeground(new Color(244, 164, 96));
        lblAvgWait.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        lblAvgWait.setBounds(677, 341, 92, 13);


        lblAvgServ = new JLabel("");
        lblAvgServ.setForeground(new Color(244, 164, 96));
        lblAvgServ.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        lblAvgServ.setBounds(810, 341, 112, 13);




        this.getContentPane().setLayout(null);
        this.getContentPane().add(lblMaxServiceTime);
        this.getContentPane().add(lblMaxArrivalTime);
        this.getContentPane().add(lblClientNum);
        this.getContentPane().add(lblQueueNum);
        this.getContentPane().add(lblSimulationInterval);
        this.getContentPane().add(lblMinArrivalTime);
        this.getContentPane().add(lblMinServiceTime);
        this.getContentPane().add(maxServiceTime);
        this.getContentPane().add(minServiceTime);
        this.getContentPane().add(simInterval);
        this.getContentPane().add(minArrivalTime);
        this.getContentPane().add(maxArrivalTime);
        this.getContentPane().add(clientNum);
        this.getContentPane().add(queueNum);
        this.getContentPane().add(endSimBtn);
        this.getContentPane().add(startSimBtn);
        this.getContentPane().add(submitBtn);
        this.getContentPane().add(lblTitle);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(lblAvgServ);
        this.getContentPane().add(lblAvgWait);
        this.getContentPane().add(lblAverageServiceTime);
        this.getContentPane().add(lblAverageWaitingTime);


    }

    public JTextField getClientNum() {
        return clientNum;
    }

    public JTextField getQueueNum() {
        return queueNum;
    }

    public JTextField getSimInterval() {
        return simInterval;
    }

    public JTextField getMinArrivalTime() {
        return minArrivalTime;
    }

    public JTextField getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public JTextField getMinServiceTime() {
        return minServiceTime;
    }

    public JTextField getMaxServiceTime() {
        return maxServiceTime;
    }


    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public JLabel getLblPeak() {
        return lblPeak;
    }

    public JLabel getLblAvgServ() {
        return lblAvgServ;
    }

    public JLabel getLblAvgWait() {
        return lblAvgWait;
    }

    public void submitActonListener (ActionListener s){
        this.submitBtn.addActionListener(s);
    }

    public void startActionListener (ActionListener a){
        this.startSimBtn.addActionListener(a);
    }

    public void stopActionListenr(ActionListener a){
        this.endSimBtn.addActionListener(a);
    }
    public JButton getStartSimBtn() {
        return startSimBtn;
    }

    public JButton getEndSimBtn() {
        return endSimBtn;
    }

    public JButton getSubmitBtn() {
        return submitBtn;
    }

}
