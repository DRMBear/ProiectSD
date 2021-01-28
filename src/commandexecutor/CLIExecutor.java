
package commandexecutor;

import fileexplorer.FileExplorer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class CLIExecutor extends UnicastRemoteObject implements RMICommandExecutor {

    private static final Pattern REGEX = Pattern.compile("^(.+?)([ ](.*))?$");

    private final FileExplorer fileExplorer;

    public CLIExecutor(FileExplorer fileExplorer) throws RemoteException {
        this.fileExplorer = fileExplorer;
    }

    @Override
    public String execute(String command) throws RemoteException {

        Matcher matcher = REGEX.matcher(command);

        if (matcher.matches()) {

            String cmd = matcher.group(1);
            System.err.println(cmd);
            switch (cmd) {
                case "view":
                    System.err.println("Selected cat");
                    return fileExplorer.readFileContent(matcher.group(3));
                case "showl":
                    System.err.println("Selected ls");
                    return fileExplorer.listAllFiles();
                case "del":
                    System.err.println("Selected rm");
                    return fileExplorer.deleteFile(matcher.group(3));
                case "create":
                    System.err.println("Selected touch");
                    return fileExplorer.createFile(matcher.group(3));
                case "write":
                    System.err.println("Selected sh");
                    return processAppend(matcher.group(3));
                case "createwrite":
                    System.err.println("Selected toucha");
                    return processAppendAndC(matcher.group(3));    
                default:
                    return "Comanda incorecte.";
            }
        }

        return "Date de intrare incorecte.";

    }

    private String processAppend(String appendParams) {
        String command;
        String text;
        Matcher matcher = REGEX.matcher(appendParams);

        if (matcher.matches()) {
            
            
            return fileExplorer.fileWrite(matcher.group(1), matcher.group(3));
            
        }

        return "Comanda nu poate fi executata.";
    }
    
    
    private String processAppendAndC(String appendParams) {
        String command;
        String text;
        Matcher matcher = REGEX.matcher(appendParams);

        if (matcher.matches()) {
            
            
            return fileExplorer.fileCreateWrite(matcher.group(1), matcher.group(3));
            
        }

        return "Comanda nu poate fi executata.";
    }

}
