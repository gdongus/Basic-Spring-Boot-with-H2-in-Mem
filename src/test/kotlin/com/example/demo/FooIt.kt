package com.example.demo

import io.kotest.matchers.equality.shouldBeEqualToUsingFields
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class FooRepositoryIntegrationTest {

	@Autowired
	private lateinit var repository: FooRepository

	@Test
	fun `should save and retrieve foo from repository`() {
		val data = UUID.randomUUID().toString()
		val foo = Foo(firstName = "John", lastName = "Doe", randomData = data)

		val savedFoo = repository.save(foo)

		val retrievedFoo: Foo = repository.findByCustom(data)

		retrievedFoo.shouldNotBeNull()
		retrievedFoo.randomData.shouldBe(data)
		retrievedFoo.shouldBeEqualToUsingFields(savedFoo)
	}
}
