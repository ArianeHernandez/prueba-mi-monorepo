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

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class DictionarySerializer extends AbstractSerializer {
	@SuppressWarnings("unchecked")
	private static Class[] _serializableClasses = new Class[] { Hashtable.class };

	@SuppressWarnings("unchecked")
	private static Class[] _JSONClasses = new Class[] { JSONObject.class };

	@SuppressWarnings("unchecked")
	public Class[] getSerializableClasses() {
		return _serializableClasses;
	}

	@SuppressWarnings("unchecked")
	public Class[] getJSONClasses() {
		return _JSONClasses;
	}

	@SuppressWarnings("unchecked")
	public boolean canSerialize(Class clazz, Class jsonClazz) {
		return (super.canSerialize(clazz, jsonClazz) || ((jsonClazz == null || jsonClazz == JSONObject.class) && Dictionary.class.isAssignableFrom(clazz)));
	}

	@SuppressWarnings("unchecked")
	public ObjectMatch tryUnmarshall(SerializerState state, Class clazz, Object o) throws UnmarshallException {
		JSONObject jso = (JSONObject) o;
		String java_class = jso.getString("javaClass");
		if (java_class == null)
			throw new UnmarshallException("no type hint");
		if (!(java_class.equals("java.util.Dictionary") || java_class.equals("java.util.Hashtable")))
			throw new UnmarshallException("not a Dictionary");
		JSONObject jsonmap = jso.getJSONObject("map");
		if (jsonmap == null)
			throw new UnmarshallException("map missing");
		ObjectMatch m = new ObjectMatch(-1);
		Iterator i = jsonmap.keys();
		String key = null;
		try {
			while (i.hasNext()) {
				key = (String) i.next();
				m = ser.tryUnmarshall(state, null, jsonmap.get(key)).max(m);
			}
		} catch (UnmarshallException e) {
			throw new UnmarshallException("key " + key + " " + e.getMessage());
		}
		return m;
	}

	@SuppressWarnings("unchecked")
	public Object unmarshall(SerializerState state, Class clazz, Object o) throws UnmarshallException {
		JSONObject jso = (JSONObject) o;
		String java_class = jso.getString("javaClass");
		if (java_class == null)
			throw new UnmarshallException("no type hint");
		Hashtable ht = null;
		if (java_class.equals("java.util.Dictionary") || java_class.equals("java.util.Hashtable")) {
			ht = new Hashtable();
		} else {
			throw new UnmarshallException("not a Dictionary");
		}
		JSONObject jsonmap = jso.getJSONObject("map");
		if (jsonmap == null)
			throw new UnmarshallException("map missing");
		Iterator i = jsonmap.keys();
		String key = null;
		try {
			while (i.hasNext()) {
				key = (String) i.next();
				ht.put(key, ser.unmarshall(state, null, jsonmap.get(key)));
			}
		} catch (UnmarshallException e) {
			throw new UnmarshallException("key " + key + " " + e.getMessage());
		}
		return ht;
	}

	@SuppressWarnings("unchecked")
	public Object marshall(SerializerState state, Object o) throws MarshallException {
		Dictionary ht = (Dictionary) o;
		JSONObject obj = new JSONObject();
		JSONObject mapdata = new JSONObject();
		if (ser.getMarshallClassHints())
			obj.put("javaClass", o.getClass().getName());
		obj.put("map", mapdata);
		Object key = null;
		Object val = null;
		try {
			Enumeration en = ht.keys();
			while (en.hasMoreElements()) {
				key = en.nextElement();
				val = ht.get(key);
				// only support String keys
				mapdata.put(key.toString(), ser.marshall(state, val));
			}
		} catch (MarshallException e) {
			throw new MarshallException("map key " + key + " " + e.getMessage());
		}
		return obj;
	}

}
