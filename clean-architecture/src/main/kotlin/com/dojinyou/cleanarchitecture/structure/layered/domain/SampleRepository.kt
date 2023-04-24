package com.dojinyou.cleanarchitecture.structure.layered.domain

import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<Sample, Long>
