
package rmi.master;

import commandexecutor.RMICommandExecutor;
import fileexplorer.FileExplorer;
import static java.awt.SystemColor.text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientNodeConnector extends UnicastRemoteObject implements RMIClientNodeConnector {


    public ClientNodeConnector() throws RemoteException {
    }

    @Override
    public String forwardCommand(String command) throws RemoteException {
        String rmiName = "rmi://localhost:9000/fileExplorer";

        System.out.println("Connecting to: " + rmiName);
        RMICommandExecutor c = null;
        try {
            c = (RMICommandExecutor) Naming.lookup(rmiName);
           //System.out.println(c.execute(command));
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientNodeConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientNodeConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

         

        return c.execute(command);
    }

}
