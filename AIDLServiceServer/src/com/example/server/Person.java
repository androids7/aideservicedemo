package com.example.server;

import com.example.server.IPerson;
import android.os.RemoteException;

public class Person extends IPerson.Stub{
	private String name;
	
	@Override
	public void setValue(String name) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
	}
	
	@Override
	public String getValue() throws RemoteException {
		// TODO Auto-generated method stub
		return this.name;
	}
}
