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

/**
 * Interface to be implemented by custom serializer objects that convert to and
 * from Java objects and JSON objects.
 */

public interface Serializer {
	public void setOwner(JSONSerializer ser);

	@SuppressWarnings("unchecked")
	public Class[] getSerializableClasses();

	@SuppressWarnings("unchecked")
	public Class[] getJSONClasses();

	@SuppressWarnings("unchecked")
	public boolean canSerialize(Class clazz, Class jsonClazz);

	@SuppressWarnings("unchecked")
	public ObjectMatch tryUnmarshall(SerializerState state, Class clazz, Object json) throws UnmarshallException;

	@SuppressWarnings("unchecked")
	public Object unmarshall(SerializerState state, Class clazz, Object json) throws UnmarshallException;

	public Object marshall(SerializerState state, Object o) throws MarshallException;
}
