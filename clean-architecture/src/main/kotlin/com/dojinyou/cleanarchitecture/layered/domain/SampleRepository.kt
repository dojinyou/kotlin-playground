package com.dojinyou.cleanarchitecture.layered.domain

import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<Sample, Long>
