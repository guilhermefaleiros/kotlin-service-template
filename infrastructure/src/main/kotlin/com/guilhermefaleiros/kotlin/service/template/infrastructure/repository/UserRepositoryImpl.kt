package com.guilhermefaleiros.kotlin.service.template.infrastructure.repository

import com.guilhermefaleiros.kotlin.service.template.application.repository.UserRepository
import com.guilhermefaleiros.kotlin.service.template.domain.entity.User
import com.guilhermefaleiros.kotlin.service.template.domain.valueobject.EntityId
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserRepositoryImpl(
    private val template: NamedParameterJdbcTemplate,
) : UserRepository {
    override fun save(user: User) {
        val sql =
            """
            INSERT INTO users (id, name, email)
            VALUES (:id, :name, :email)
            """.trimIndent()
        val params =
            mapOf(
                "id" to user.id.value,
                "name" to user.name,
                "email" to user.email,
            )
        template.update(sql, params)
    }

    override fun findById(id: EntityId): User? {
        val sql =
            """
            SELECT id, name, email
            FROM users
            WHERE id = :id
            """.trimIndent()
        val params = mapOf("id" to id.value)
        return template.query(sql, params) { rs, _ ->
            User.restore(
                id = rs.getObject("id", UUID::class.java),
                name = rs.getString("name"),
                email = rs.getString("email"),
            )
        }.firstOrNull()
    }

    override fun deleteById(id: EntityId) {
        val sql =
            """
            DELETE FROM users
            WHERE id = :id
            """.trimIndent()
        val params = mapOf("id" to id.value)
        template.update(sql, params)
    }

    override fun findAll(): List<User> {
        val sql =
            """
            SELECT id, name, email
            FROM users
            """.trimIndent()
        return template.query(sql) { rs, _ ->
            User.restore(
                id = rs.getObject("id", UUID::class.java),
                name = rs.getString("name"),
                email = rs.getString("email"),
            )
        }
    }
}
