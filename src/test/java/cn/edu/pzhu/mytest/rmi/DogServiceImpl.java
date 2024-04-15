package cn.edu.pzhu.mytest.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * dog
 *
 * @author zhangcz
 * @since 20220228
 */
public class DogServiceImpl extends UnicastRemoteObject implements DogService {

    public DogServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String eat(String name) {
        return "dog eat " + name;
    }
}
