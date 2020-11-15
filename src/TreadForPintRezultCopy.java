public class TreadForPintRezultCopy implements Runnable {

    private ClassForCopyFile classForCopyFile;
    private int flag;
    private int rezult;

    public TreadForPintRezultCopy(ClassForCopyFile classForCopyFile, int flag) {
        this.classForCopyFile = classForCopyFile;
        this.flag = flag;
        this.rezult = 0;
    }
    public TreadForPintRezultCopy() {

    }
    private void printRezult() {
        byte[] bytes = new byte[2];
        long sizeFile = classForCopyFile.getFile().length();
        int sizeByffer = classForCopyFile.getNumberOfBytesToCopy(this.flag).length;
        rezult = rezult + (int) ((sizeByffer * 100) / sizeFile);
        System.out.println((rezult) + "%");

    }

    @Override
    public void run() {
        for (; classForCopyFile.isStopPrint() != true; ) {
            printRezult();
        }
    }
}
