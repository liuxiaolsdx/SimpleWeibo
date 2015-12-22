package com.weibo.util;

import java.io.StringWriter;
import java.io.PrintWriter;

public class ExceptionUtil {
	public static String toString(Exception e) {

		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer, true));

		return writer.toString();
	}

	public static void main(String[] args) {
		try {
			throw new Exception("自定义异常");
		} catch (Exception ex) {
			System.out.println(toString(ex));
		}

	}

}
