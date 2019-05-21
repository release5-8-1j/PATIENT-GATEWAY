package com.bytatech.ayoos.repository.search;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bytatech.ayoos.client.domain.TestDate;

/**
 * Spring Data Elasticsearch repository for the TestDate entity.
 */
public interface TestDateSearchRepository extends ElasticsearchRepository<TestDate, Long> {
}
