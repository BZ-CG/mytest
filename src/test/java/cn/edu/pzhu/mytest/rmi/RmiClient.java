package cn.edu.pzhu.mytest.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * rmi test
 *
 * @author zhangcz
 * @since 20220228
 */
public class RmiClient {

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, NotBoundException {
        String url = "rmi://127.0.0.1:1098/DogService";
        Remote lookup = Naming.lookup(url);
        DogService dogService = (DogService) lookup;
        System.out.println(dogService.eat("rice"));
    }
}
