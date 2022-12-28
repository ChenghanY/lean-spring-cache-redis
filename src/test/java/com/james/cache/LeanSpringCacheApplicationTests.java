package com.james.cache;

import com.james.cache.search.ISearchService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LeanSpringCacheApplicationTests {

	Logger LOGGER = LoggerFactory.getLogger(LeanSpringCacheApplicationTests.class);

	@Autowired
	private ISearchService searchService;

	@Test
	void hello() {
		LOGGER.info(searchService.search("h"));
		LOGGER.info(searchService.search("h"));
	}

}
