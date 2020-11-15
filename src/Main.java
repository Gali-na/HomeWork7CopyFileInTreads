import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ClassForCopyFile classForCopyFile = new ClassForCopyFile(new File("fileOne.txt"));
        File newFile = new File("DirectoryOne", classForCopyFile.getFile().getName());
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ThreadForRead threadForRead = new ThreadForRead(classForCopyFile, 1);
        TreadWriteInNewFile treadForWreareInNewFile = new TreadWriteInNewFile(classForCopyFile, newFile, 2);
        TreadForPintRezultCopy threadPrint = new TreadForPintRezultCopy(classForCopyFile, 3);

        Thread threadPrintRezult = new Thread(threadPrint);
        Thread threadRead = new Thread(threadForRead);
        Thread wreate = new Thread(treadForWreareInNewFile);
        threadPrintRezult.start();
        wreate.start();
        threadRead.start();
        try {
            threadRead.join();
            wreate.join();
            threadPrintRezult.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
