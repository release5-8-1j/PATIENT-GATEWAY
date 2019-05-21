package com.bytatech.ayoos.client.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A TestDate.
 */

@Document(indexName = "testdate")
public class TestDate implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private Long id;

   
    private LocalDate date;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public TestDate date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestDate testDate = (TestDate) o;
        if (testDate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), testDate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TestDate{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
