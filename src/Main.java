import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CopyFile classForCopyFile = new CopyFile(new File("fileOne.txt"));
        File newFile = new File("DirectoryOne", classForCopyFile.getFile().getName());
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ThreadForRead threadForRead = new ThreadForRead(classForCopyFile, 1);
        ThreadWriteInNewFile threadWriteInNewFile = new ThreadWriteInNewFile(classForCopyFile, newFile, 2);
        ThreadForPintRezultCopy threadPrint = new ThreadForPintRezultCopy(classForCopyFile, 3);

        Thread threadPrintRezult = new Thread(threadPrint);
        Thread threadRead = new Thread(threadForRead);
        Thread write = new Thread(threadWriteInNewFile);
        threadPrintRezult.start();
        write.start();
        threadRead.start();
        try {
            threadRead.join();
            write.join();
            threadPrintRezult.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
