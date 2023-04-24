package com.dojinyou.cleanarchitecture.feature.sample

import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<Sample, Long>
