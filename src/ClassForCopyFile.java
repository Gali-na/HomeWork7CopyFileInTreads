import java.io.File;

public class ClassForCopyFile {
    private File file;
    private int flag =1;
    private byte[] numberOfBytesToCopy;
    private boolean stop;
    private boolean stopWrite;
    private boolean stopPrint;

    public boolean isStopPrint() {
        return stopPrint;
    }

    public void setStopPrint(boolean stopPrint) {
        this.stopPrint = stopPrint;
    }

    public boolean isStopWrite() {
        return stopWrite;
    }

    public void setStopWrite(boolean stopWrite) {
        this.stopWrite = stopWrite;
    }

    public ClassForCopyFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public synchronized byte[] getNumberOfBytesToCopy(int flag) {
        for(;this.flag!=flag;) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        if(flag==2){
            this.flag=3;
        }
        if(flag==3){
            this.flag=1;
        }
        notifyAll();
        return numberOfBytesToCopy;
    }

    public synchronized void setNumberOfBytesToCopy(byte [] bytes,int flag) {
        for(;this.flag!=flag;) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        this.numberOfBytesToCopy=bytes;
        this.flag=2;
        notifyAll();

    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
