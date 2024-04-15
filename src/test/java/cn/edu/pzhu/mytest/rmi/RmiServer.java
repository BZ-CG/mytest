package cn.edu.pzhu.mytest.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * rmi test
 *
 * @author zhangcz
 * @since 20220228
 */
public class RmiServer {

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, NotBoundException {

        int port = 1098;
        String url = "rmi://127.0.0.1:1098/DogService";
        LocateRegistry.createRegistry(port);
        Naming.bind(url, new DogServiceImpl());
    }
}
