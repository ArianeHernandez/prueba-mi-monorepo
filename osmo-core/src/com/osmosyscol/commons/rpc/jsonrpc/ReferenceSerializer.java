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

import java.util.logging.Logger;

public class ReferenceSerializer extends AbstractSerializer {
	private final static Logger log = Logger.getLogger(ReferenceSerializer.class.getName());

	private JSONRPCBridge bridge;

	@SuppressWarnings("unchecked")
	private static Class[] _serializableClasses = new Class[] {};
	@SuppressWarnings("unchecked")
	private static Class[] _JSONClasses = new Class[] {};

	@SuppressWarnings("unchecked")
	public Class[] getSerializableClasses() {
		return _serializableClasses;
	}

	@SuppressWarnings("unchecked")
	public Class[] getJSONClasses() {
		return _JSONClasses;
	}

	public ReferenceSerializer(JSONRPCBridge bridge) {
		this.bridge = bridge;
	}

	@SuppressWarnings("unchecked")
	public boolean canSerialize(Class clazz, Class jsonClazz) {
		return (!clazz.isArray() && !clazz.isPrimitive() && !clazz.isInterface() && (bridge.isReference(clazz) || bridge.isCallableReference(clazz)) && (jsonClazz == null || jsonClazz == JSONObject.class));
	}

	@SuppressWarnings("unchecked")
	public ObjectMatch tryUnmarshall(SerializerState state, Class clazz, Object o) throws UnmarshallException {
		return ObjectMatch.OKAY;
	}

	@SuppressWarnings("unchecked")
	public Object unmarshall(SerializerState state, Class clazz, Object o) throws UnmarshallException {
		JSONObject jso = (JSONObject) o;
		Object ref = null;
		String json_type = jso.getString("JSONRPCType");
		int object_id = jso.getInt("objectID");
		if (json_type != null && json_type.equals("Reference")) {
			synchronized (bridge.referenceMap) {
				ref = bridge.referenceMap.get(new Integer(object_id));
			}
		}
		return ref;
	}

	@SuppressWarnings("unchecked")
	public Object marshall(SerializerState state, Object o) throws MarshallException {
		Class clazz = o.getClass();
		Integer identity = new Integer(System.identityHashCode(o));
		if (bridge.isReference(clazz)) {
			if (ser.isDebug())
				log.fine("marshalling reference to object " + identity + " of class " + clazz.getName());
			synchronized (bridge.referenceMap) {
				bridge.referenceMap.put(identity, o);
			}
			JSONObject jso = new JSONObject();
			jso.put("JSONRPCType", "Reference");
			jso.put("javaClass", clazz.getName());
			jso.put("objectID", identity);
			return jso;
		} else if (bridge.isCallableReference(clazz)) {
			if (ser.isDebug())
				log.fine("marshalling callable reference to object " + identity + " of class " + clazz.getName());
			bridge.registerObject(identity, o);
			JSONObject jso = new JSONObject();
			jso.put("JSONRPCType", "CallableReference");
			jso.put("javaClass", clazz.getName());
			jso.put("objectID", identity);
			return jso;
		}
		return null;
	}

}
