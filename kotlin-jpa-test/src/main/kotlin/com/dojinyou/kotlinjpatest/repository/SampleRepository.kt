package com.dojinyou.kotlinjpatest.repository

import com.dojinyou.kotlinjpatest.entity.Sample
import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<Sample, String>
