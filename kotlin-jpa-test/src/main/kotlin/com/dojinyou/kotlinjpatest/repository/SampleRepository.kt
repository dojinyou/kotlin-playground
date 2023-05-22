package com.dojinyou.kotlinjpatest.repository

import com.dojinyou.kotlinjpatest.domain.Sample
import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<Sample, String>
