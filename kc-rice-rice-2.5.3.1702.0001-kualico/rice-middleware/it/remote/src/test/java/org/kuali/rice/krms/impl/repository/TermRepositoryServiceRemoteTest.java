/**
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.krms.impl.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.kuali.rice.krms.api.repository.RuleRepositoryService;
import org.kuali.rice.krms.api.repository.term.TermRepositoryService;
import org.kuali.rice.test.remote.RemoteTestHarness;

public class TermRepositoryServiceRemoteTest extends TermRepositoryServiceTest {

        RemoteTestHarness harness = new RemoteTestHarness();

    @Before
    @Override
    public void setupServiceUnderTest() {
        try {
            super.setupServiceUnderTest();
            TermRepositoryService remoteProxy =
                    harness.publishEndpointAndReturnProxy(TermRepositoryService.class, this.getTermRepositoryServiceImpl());
            super.setTermRepositoryService(remoteProxy);
        } catch (Throwable t) {
            Assert.fail("https://jira.kuali.org/browse/KULRICE-9848 " + t.getMessage());
        }
    }

    @After
    public void unPublishEndpoint() {
        harness.stopEndpoint();
    }
}
