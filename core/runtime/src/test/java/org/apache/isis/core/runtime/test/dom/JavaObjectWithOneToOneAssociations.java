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

package org.apache.isis.core.runtime.test.dom;

public class JavaObjectWithOneToOneAssociations {
    boolean available = false;
    private JavaReferencedObject object;
    boolean valid = false;
    boolean visible = false;

    public String availableReferencedObject(final JavaReferencedObject object) {
        return available ? null : "not available";
    }

    public JavaReferencedObject getReferencedObject() {
        return object;
    }

    public void setReferencedObject(final JavaReferencedObject object) {
        this.object = object;
    }

    public String validReferencedObject(final JavaReferencedObject object) {
        return valid ? null : "not valid";
    }

    public boolean hideReferencedObject(final JavaReferencedObject object) {
        return !visible;
    }
}