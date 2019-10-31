/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.isis.metamodel.facets.object.parented;

import java.util.List;
import java.util.Set;

import org.apache.isis.metamodel.facetapi.Facet;

/**
 * Indicates that this class is parented, that is, wholly contained within a
 * larger object.
 *
 * <p>
 * There is (now) only one class of object that is parented, namely internal collections.
 *
 * <p>
 * Internal collections are the {@link List}s, {@link Set}s etc that hold references to
 * other root entities.
 */
public interface ParentedCollectionFacet extends Facet {

}