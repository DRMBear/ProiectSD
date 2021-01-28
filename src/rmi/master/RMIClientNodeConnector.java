

package rmi.master;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientNodeConnector  extends Remote{
    
  String forwardCommand(String command) throws RemoteException;
    
}
