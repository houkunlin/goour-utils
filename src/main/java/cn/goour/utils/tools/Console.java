package cn.goour.utils.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {
	public static void printAll(Object... objs) {
		StringBuilder sb = new StringBuilder();
		Thread currThread = Thread.currentThread();
		StackTraceElement[] stackElm = currThread.getStackTrace();
		StackTraceElement stack = null;
		String timeStr = getTimeString();
		String left = String.format("%" + timeStr.length() + "s", "");
		sb.append(timeStr);
		for (int i = 0; i < stackElm.length; i++) {
			stack = stackElm[i];
			sb.append(getStackTraceString(left, stack));
		}
		sb.append(getObjectString(left, objs));
		System.out.println(sb);
	}

	public static void printNow(Object... objs) {
		StringBuilder sb = new StringBuilder();
		Thread currThread = Thread.currentThread();
		StackTraceElement[] stackElm = currThread.getStackTrace();
		StackTraceElement stack = null;
		String timeStr = getTimeString();
		String left = String.format("%" + timeStr.length() + "s", "");
		sb.append(timeStr);
		for (int i = 0; i < stackElm.length; i++) {
			stack = stackElm[i];
			if (stack.getClassName().equals(Console.class.getName())) {
				stack = stackElm[i+1];
				break;
			}
		}
		sb.append(getStackTraceString(left, stack));
		sb.append(getObjectString(left, objs));
		System.out.println(sb);
	}
	public static void printInfo(Object objs) {
		StringBuilder sb = new StringBuilder();
		Thread currThread = Thread.currentThread();
		StackTraceElement[] stackElm = currThread.getStackTrace();
		StackTraceElement stack = null;
		String timeStr = getTimeString();
		sb.append(timeStr);
		for (int i = 0; i < stackElm.length; i++) {
			stack = stackElm[i];
			if (stack.getClassName().equals(Console.class.getName())) {
				stack = stackElm[i+1];
				break;
			}
		}
		sb.append(" ");
		sb.append(stack.getClassName());
		sb.append(".");
		sb.append(stack.getMethodName());
		sb.append(":");
		sb.append(stack.getLineNumber());
		sb.append("/-"+objs);
		System.out.println(sb);
	}
	private static String getTimeString(){
		StringBuilder sb = new StringBuilder();
		Date now = new Date();
		String dfStr = "yyyy-MM-dd HH:mm:ss.SSS";
		DateFormat df = new SimpleDateFormat(dfStr);
		sb.append("\n[");
		sb.append(df.format(now));
		sb.append("]");
		return sb.toString();
	}
	private static String getStackTraceString(String left,StackTraceElement stack){
		StringBuilder sb = new StringBuilder();
		sb.append(" For Class: ");
		sb.append(stack.getClassName());
		sb.append("\n" + left);
		sb.append(" At Mothod: ");
		sb.append(stack.getMethodName());
		sb.append("\n" + left);
		sb.append(" In Line: ");
		sb.append(stack.getLineNumber());
		sb.append("\n" + left);
		sb.append("\n" + left);
		return sb.toString();
	}
	private static String getObjectString(String left,Object...objs){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < objs.length; i++) {
			Object obj = objs[i];
			if (obj == null) {
				sb.append(" ObjClass : Null");
				sb.append("\n" + left);
				continue;
			}
			sb.append(" ObjClass : ");
			sb.append(obj.getClass().getName());
			sb.append("\n" + left);
			sb.append(" ObjString: ");
			sb.append(obj);
			sb.append("\n" + left);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		printInfo("w");
		printAll("w");
	}
}
