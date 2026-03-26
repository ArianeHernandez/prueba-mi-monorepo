package com.osmosyscol.commons.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {

	public static <T> List<T> toList(T[] array) {

		if (array == null) {
			return null;
		}

		List<T> list = new ArrayList<T>();

		for (T t : array) {
			list.add(t);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(List<T> list, Class<T> c) {

		if (list == null || list.size() == 0) {
			return null;
		}

		if (c == null) {
			return null;
		}

		T[] t = (T[]) Array.newInstance(c, list.size());

		int i = 0;
		for (T e : list) {
			t[i] = e;
			i++;
		}

		return t;

	}

}
