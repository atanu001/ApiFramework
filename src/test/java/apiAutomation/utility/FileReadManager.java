package apiAutomation.utility;

public class FileReadManager {

    private static final FileReadManager fileReadManager = new FileReadManager();
    private static final FileHelper fileHelper = new FileHelper();

    public static FileReadManager getInstance(){
        return fileReadManager;
    }

    public FileHelper getFileHelper(){
        return(fileHelper == null) ? new FileHelper():fileHelper;
    }
}
