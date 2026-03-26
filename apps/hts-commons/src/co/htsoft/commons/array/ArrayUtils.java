package co.htsoft.commons.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {

	public static <T> Set<T> toSet(T[] array) {

		if (array == null) {
			return null;
		}

		Set<T> list = new HashSet<T>();

		for (T t : array) {
			list.add(t);
		}

		return list;
	}

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
	public static <T> T[] toArray(List<T> list) {

		if (list == null || list.size() == 0) {
			return null;
		}

		T[] t = (T[]) Array.newInstance(list.get(0).getClass(), list.size());

		int i = 0;
		for (T e : list) {
			t[i] = e;
			i++;
		}

		return t;

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

	@SuppressWarnings("unchecked")
	public static <T> T[] join(T[]... elements) {

		if (elements == null || elements.length == 0) {
			return null;
		}

		List<T> e = new ArrayList<>();

		for (T[] ts : elements) {
			if (elements != null) {
				e.addAll(toList(ts));
			}
		}

		return toArray(e);
	}

}
