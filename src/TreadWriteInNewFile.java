import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TreadWriteInNewFile implements Runnable {
    private ClassForCopyFile classForCopyFile;
    private File newFile;
    private int flag;

    public TreadWriteInNewFile(ClassForCopyFile classForCopyFile, File newFile, int flag) {
        this.classForCopyFile = classForCopyFile;
        this.newFile = newFile;
        this.flag = flag;
    }

    public TreadWriteInNewFile() {

    }

    private void writeInNewFile(byte[] bytes) {
        try (FileOutputStream whereСopy = new FileOutputStream(this.newFile, true)) {
            whereСopy.write(bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (; classForCopyFile.isStopWrite() != true; ) {
            writeInNewFile(classForCopyFile.getNumberOfBytesToCopy(this.flag));
        }
        classForCopyFile.setStopPrint(true);
    }
}
