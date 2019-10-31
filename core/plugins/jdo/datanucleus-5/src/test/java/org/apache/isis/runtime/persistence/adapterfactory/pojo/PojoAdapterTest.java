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

package org.apache.isis.runtime.persistence.adapterfactory.pojo;

import java.util.Date;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.apache.isis.jdo.persistence.PersistenceSession5;
import org.apache.isis.metamodel.adapter.ObjectAdapter;
import org.apache.isis.metamodel.adapter.oid.Oid.Factory;
import org.apache.isis.metamodel.adapter.version.Version;
import org.apache.isis.metamodel.spec.ObjectSpecId;
import org.apache.isis.metamodel.specloader.SpecificationLoader;
import org.apache.isis.runtime.persistence.adapter.PojoAdapter;
import org.apache.isis.security.authentication.AuthenticationSession;
import org.apache.isis.unittestsupport.jmocking.JUnitRuleMockery2;
import org.apache.isis.unittestsupport.jmocking.JUnitRuleMockery2.Mode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class PojoAdapterTest {

    @Rule
    public JUnitRuleMockery2 context = JUnitRuleMockery2.createFor(Mode.INTERFACES_AND_CLASSES);

    private ObjectAdapter adapter;
    private RuntimeTestPojo domainObject;

    @Mock
    private Version mockVersion;
    @Mock
    private Version mockVersion2;
    @Mock
    private SpecificationLoader mockSpecificationLoader;
    @Mock
    private AuthenticationSession mockAuthenticationSession;
    @Mock
    private PersistenceSession5 mockPersistenceSession;

    @Before
    public void setUp() throws Exception {
        domainObject = new RuntimeTestPojo();

        adapter = PojoAdapter.of(
                domainObject, 
                Factory.persistentOf(ObjectSpecId.of("CUS"), "1"),
                mockSpecificationLoader, 
                mockPersistenceSession);

        adapter.setVersion(mockVersion);

        allowUnimportantMethodCallsOn(mockVersion);
        allowUnimportantMethodCallsOn(mockVersion2);
    }

    private void allowUnimportantMethodCallsOn(final Version version) {
        context.checking(new Expectations() {
            {
                allowing(version).getSequence();
                allowing(version).getUtcTimestamp();
                allowing(version).sequence();
                allowing(version).getUser();
                allowing(version).hasTimestamp();

                allowing(version).getTime();
                will(returnValue(new Date()));

                allowing(mockAuthenticationSession).getUserName();
                will(returnValue("fredbloggs"));
            }
        });
    }

    @Test
    public void getOid_initially() {
        assertEquals(Factory.persistentOf(ObjectSpecId.of("CUS"), "1"), adapter.getOid());
    }

    @Test
    public void getObject_initially() {
        assertEquals(domainObject, adapter.getPojo());
    }

    @Test
    public void getVersion_initially() throws Exception {
        assertSame(mockVersion, adapter.getVersion());
    }

    //TODO[2154] remove    
    //    @Test
    //    public void checkLock_whenVersionsSame() throws Exception {
    //
    //        context.checking(new Expectations() {
    //            {
    //                oneOf(mockVersion).different(mockVersion2);
    //                will(returnValue(false));
    //            }
    //        });
    //        
    //        adapter.checkLock(mockVersion2);
    //    }
    //
    //    @Test(expected=ConcurrencyException.class)
    //    public void checkLock_whenVersionsDifferent() throws Exception {
    //
    //        adapter = PojoAdapterBuilder.create()
    //                .with(mockSpecificationLoader)
    //                .withTitleString("some pojo")
    //                .with(mockVersion)
    //                .with(mockAuthenticationSession)
    //                .build();
    //        
    //        context.checking(new Expectations() {
    //            {
    //                oneOf(mockVersion).different(mockVersion2);
    //                will(returnValue(true));
    //            }
    //        });
    //        
    //        adapter.checkLock(mockVersion2);
    //    }

}