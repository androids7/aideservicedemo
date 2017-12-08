package com.example.server;

import com.example.server.IPerson;
import com.example.server.R;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private Button bindButton;
	private Button unbindButton;
	private IPerson iPerson;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bindButton = (Button)findViewById(R.id.button1);
		unbindButton = (Button)findViewById(R.id.button2);
		bindButton.setOnClickListener(this);
		unbindButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			Intent intent = new Intent(MainActivity.this,MyService.class);
			bindService(intent, conn, Service.BIND_AUTO_CREATE);
			break;
		case R.id.button2:
			unbindService(conn);
			break;

		default:
			break;
		}
	}
	
	private ServiceConnection conn = new ServiceConnection() {
		//连接对象
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			iPerson = IPerson.Stub.asInterface(service);
			if(iPerson!=null){
				try {
					iPerson.setValue("AIDL TEST");
					Toast.makeText(MainActivity.this, "赋值成功", Toast.LENGTH_LONG).show();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(MainActivity.this, "赋值失败", Toast.LENGTH_LONG).show();
				}
			}
		}
	};

}
