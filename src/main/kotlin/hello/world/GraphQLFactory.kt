package hello.world

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import graphql.GraphQL
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class GraphQLFactory {

    @Bean
    @Singleton
    fun graphQL(): GraphQL {
        val topLevelObject = TopLevelObject(Query())
        val schema = toSchema(config = SchemaGeneratorConfig(
            supportedPackages = listOf("hello.world")
        ), queries = listOf(topLevelObject))

        // Return the GraphQL bean.
        return GraphQL.newGraphQL(schema).build()
    }
}