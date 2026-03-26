package com.osmosyscol.datasuite.near.utils;

import java.lang.reflect.*;

public class ReflectionUtils {
	
	public static <T> void setSingleton(Class<T> clazz, String attributeName, Object newValue) throws Exception {
		Field instance = clazz.getDeclaredField(attributeName);
		instance.setAccessible(true);
		instance.set(instance, newValue );
	}
	
	
	public static void setFinalStatic(Field field, Object newValue) throws Exception {
	      field.setAccessible(true);

	      Field modifiersField = Field.class.getDeclaredField("modifiers");
	      modifiersField.setAccessible(true);
	      modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

	      field.set(null, newValue);
	}
	
}
