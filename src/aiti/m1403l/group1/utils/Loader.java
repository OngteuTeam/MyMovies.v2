package aiti.m1403l.group1.utils;

import aiti.m1403l.group1.data.DatabaseWrapper;
import aiti.m1403l.group1.network.APIProcess;
import aiti.m1403l.group1.network.Connectivity;
import android.content.Context;

public class Loader {

	private final Context context;
	private boolean isFirstRun = false;
	
	public Loader(Context context) {
		super();
		this.context = context;
		new DatabaseWrapper(context);

		MySPManager spManager = new MySPManager(context);
		this.isFirstRun = spManager.isFirstRun();
		if (Connectivity.isNetworkAvailable(context)) {
			if (spManager.isFirstRun()) {
				APIProcess.sendRequestFirstRun(context);
			} else {
				APIProcess.sendRequestUpdate(context);
			}
		} else {
			MyDialog.getDialogNoInternet(context);
		}

	}

	public boolean isFirstRun() {
		return isFirstRun;
	}
	

}
