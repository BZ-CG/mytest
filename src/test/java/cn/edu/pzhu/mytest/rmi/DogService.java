package cn.edu.pzhu.mytest.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * dog
 *
 * @author zhangcz
 * @since 20220228
 */
public interface DogService extends Remote {

    String eat(String name) throws RemoteException;
}
