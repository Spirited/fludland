package org.fludland.repository;

import org.fludland.AbstractIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public abstract class AbstractDataIntegrationTest extends AbstractIntegrationTest {
}
