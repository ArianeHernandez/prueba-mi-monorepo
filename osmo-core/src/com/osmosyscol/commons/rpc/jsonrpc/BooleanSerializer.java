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

public class BooleanSerializer extends AbstractSerializer {
	@SuppressWarnings("unchecked")
	private static Class[] _serializableClasses = new Class[] { boolean.class, Boolean.class };

	@SuppressWarnings("unchecked")
	private static Class[] _JSONClasses = new Class[] { Boolean.class };

	@SuppressWarnings("unchecked")
	public Class[] getSerializableClasses() {
		return _serializableClasses;
	}

	@SuppressWarnings("unchecked")
	public Class[] getJSONClasses() {
		return _JSONClasses;
	}

	@SuppressWarnings("unchecked")
	public ObjectMatch tryUnmarshall(SerializerState state, Class clazz, Object jso) throws UnmarshallException {
		return ObjectMatch.OKAY;
	}

	@SuppressWarnings("unchecked")
	public Object unmarshall(SerializerState state, Class clazz, Object jso) throws UnmarshallException {
		if (clazz == boolean.class) {
			return new Boolean(((Boolean) jso).booleanValue());
		} else {
			return jso;
		}
	}

	public Object marshall(SerializerState state, Object o) throws MarshallException {
		return o;
	}

}
