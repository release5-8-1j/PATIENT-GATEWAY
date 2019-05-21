package com.bytatech.ayoos.repository.search;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bytatech.ayoos.client.domain.TestDate;
import com.bytatech.ayoos.client.doctor.model.*;
/**
 * Spring Data Elasticsearch repository for the TestDate entity.
 */
public interface DoctorSearchRepository extends ElasticsearchRepository<Doctor, Long> {
}
