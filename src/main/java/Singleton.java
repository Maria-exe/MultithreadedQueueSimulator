import javax.swing.*;
import java.io.*;

public class Singleton {
    private static final Singleton inst= new Singleton();
    private PrintWriter myWriter;
    private File myFile;
    private JTextArea textArea;

    private Singleton()  {
        super();
        this.myFile = new File("filename.txt");
        try {
            this.myWriter = new PrintWriter(myFile, "UTF-8");
            BufferedWriter out = new BufferedWriter(myWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeToFile(Object o) {
        synchronized (inst) {
            myWriter.print(o);
        }
    }

    public void writeLineToFile(Object o) {
        synchronized (inst) {
            myWriter.println(o);
        }
    }

    public void writeToUI(Object o) {
        synchronized (inst) {
            this.textArea.append((String) o);
        }
    }

    public synchronized static Singleton getInstance() {
        return inst;
    }

    public PrintWriter getMyWriter() {
        return myWriter;
    }

    public void setMyWriter(PrintWriter myWriter) {
        this.myWriter = myWriter;
    }

    public File getMyFile() {
        return myFile;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
}