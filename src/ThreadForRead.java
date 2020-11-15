import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ThreadForRead implements Runnable {

    private ClassForCopyFile classForCopyFile;
    private int flag;
    private long startRead;

    public ThreadForRead(ClassForCopyFile classForCopyFile, int flag) {
        this.classForCopyFile = classForCopyFile;
        this.flag = flag;
        this.startRead = 0;
    }

    public long getStartRead() {
        return startRead;
    }

    public void setStartRead(long startRead) {
        this.startRead = startRead;
    }

    private byte[] read() {
        int byteread = 0;
        int lenghtNewForbyteread = 2;
        byte[] buffer = new byte[lenghtNewForbyteread];

        try (FileInputStream fileFrom = new FileInputStream(classForCopyFile.getFile())) {
            fileFrom.skip(getStartRead());
            if ((byteread = fileFrom.read(buffer)) < 0) {
                classForCopyFile.setStop(true);
            }
            this.setStartRead(this.startRead + (long) lenghtNewForbyteread);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    @Override
    public void run() {
        for (; classForCopyFile.isStop() != true; ) {
            classForCopyFile.setNumberOfBytesToCopy(read(), this.flag);
        }
        classForCopyFile.setStopWrite(true);
    }
}
