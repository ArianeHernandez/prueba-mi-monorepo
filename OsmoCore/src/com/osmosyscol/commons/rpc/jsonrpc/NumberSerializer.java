/*
 * JSON-RPC-Java - a JSON-RPC to Java Bridge with dynamic invocation
 *
 * $Id$
 *
 * Copyright Metaparadigm Pte. Ltd. 2004.
 * Michael Clark <michael@metaparadigm.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public (LGPL)
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details: http://www.gnu.org/
 *
 */

package com.osmosyscol.commons.rpc.jsonrpc;

import java.math.BigDecimal;

public class NumberSerializer extends AbstractSerializer {
	@SuppressWarnings("unchecked")
	private static Class[] _serializableClasses = new Class[] { Integer.class, Byte.class, Short.class, Long.class, Float.class, Double.class , BigDecimal.class};

	@SuppressWarnings("unchecked")
	private static Class[] _JSONClasses = new Class[] { Integer.class, Byte.class, Short.class, Long.class, Float.class, Double.class, String.class  , BigDecimal.class};

	@SuppressWarnings("unchmecked")
	public Class[] getSerializableClasses() {
		return _serializableClasses;
	}

	@SuppressWarnings("unchecked")
	public Class[] getJSONClasses() {
		return _JSONClasses;
	}

	@SuppressWarnings("unchecked")
	public ObjectMatch tryUnmarshall(SerializerState state, Class clazz, Object jso) throws UnmarshallException {
		try {
			toNumber(clazz, jso);
		} catch (NumberFormatException e) {
			throw new UnmarshallException("not a number");
		}
		return ObjectMatch.OKAY;
	}

	@SuppressWarnings("unchecked")
	public Object toNumber(Class clazz, Object jso) throws NumberFormatException {
		if (clazz == Integer.class) {
			if (jso instanceof String)
				return new Integer((String) jso);
			else
				return new Integer(((Number) jso).intValue());
		} else if (clazz == Long.class) {
			if (jso instanceof String)
				return new Long((String) jso);
			else
				return new Long(((Number) jso).longValue());
		} else if (clazz == Short.class) {
			if (jso instanceof String)
				return new Short((String) jso);
			else
				return new Short(((Number) jso).shortValue());
		} else if (clazz == Byte.class) {
			if (jso instanceof String)
				return new Byte((String) jso);
			else
				return new Byte(((Number) jso).byteValue());
		} else if (clazz == Float.class) {
			if (jso instanceof String)
				return new Float((String) jso);
			else
				return new Float(((Number) jso).floatValue());
		} else if (clazz == Double.class) {
			if (jso instanceof String)
				return new Double((String) jso);
			else
				return new Double(((Number) jso).doubleValue());
		} else if (clazz == BigDecimal.class) {
			if (jso instanceof String)
				return new BigDecimal((String) jso);
			else
				return new BigDecimal(jso.toString());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object unmarshall(SerializerState state, Class clazz, Object jso) throws UnmarshallException {
		try {
			if (jso == null || "".equals(jso))
				return null;
			return toNumber(clazz, jso);
		} catch (NumberFormatException nfe) {
			throw new UnmarshallException("cannot convert object " + jso + " to type " + clazz.getName());
		}
	}

	public Object marshall(SerializerState state, Object o) throws MarshallException {
		return o;
	}

}
