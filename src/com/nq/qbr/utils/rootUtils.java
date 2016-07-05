package com.nq.qbr.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import android.util.Log;

public class rootUtils {
	private static String LOG_TAG = rootUtils.class.getName();

	public static boolean isDeviceRooted() {
		if (checkRootMethod3()) {
			Log.d(LOG_TAG, "checkRootMethod3 true!");
			return true;
		}
		if (checkRootMethod2()) {
			Log.d(LOG_TAG, "checkRootMethod2 true!");
			return true;
		}
		if (checkRootMethod1()) {
			Log.d(LOG_TAG, "checkRootMethod1 true!");
			return true;
		}
		Log.d(LOG_TAG, "isDeviceRooted false!");
		return false;
	}

	public static boolean checkRootMethod1() {
		String buildTags = android.os.Build.TAGS;
		if (buildTags != null && buildTags.contains("test-keys")) {
			Log.d(LOG_TAG, "test-keys ok!");
			return true;
		}
		return false;
	}

	public static boolean checkRootMethod2() {
		String[] paths = { "/sbin/su", "/system/bin/su", "/system/xbin/su",
				"/data/local/xbin/su", "/data/local/bin/su",
				"/system/sd/xbin/su", "/system/bin/failsafe/su",
				"/data/local/su", "/system/app/Superuser.apk" };
		for (String path : paths) {
			if (new File(path).exists()) {
				Log.d(LOG_TAG, path);
				return true;
			}
		}
		return false;
	}

	public static boolean checkRootMethod3() {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(
					new String[] { "/system/xbin/which", "su" });
			BufferedReader in = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			if (in.readLine() != null) {
				Log.d(LOG_TAG, "checkRootMethod3 ok!");
				return true;
			}
			return false;
		} catch (Throwable t) {
			return false;
		} finally {
			if (process != null)
				process.destroy();
		}
	}
}
