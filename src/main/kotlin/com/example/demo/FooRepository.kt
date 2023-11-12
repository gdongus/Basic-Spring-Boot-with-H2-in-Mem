package com.example.demo

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FooRepository : CrudRepository<Foo, Long> {

    @Query("SELECT f FROM Foo f WHERE f.randomData = :randomData")
    fun findByCustom(@Param("randomData") randomData: String): Foo

}