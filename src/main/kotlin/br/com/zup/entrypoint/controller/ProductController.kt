package br.com.zup.entrypoint.controller

import br.com.zup.core.mapper.ProductConverter
import br.com.zup.core.port.ProductServicePort
import br.com.zup.entrypoint.dto.ProductDto
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.validation.Validated
import java.util.*
import javax.validation.Valid

@Validated
@Controller("/v1/iupp/products")
class ProductController(private val productServicePort: ProductServicePort) {

    @Post
    fun create(@Valid @Body dto: ProductDto) =
        HttpResponse.created(productServicePort.create(ProductConverter.toProduct(dto)))

    @Get
    fun findAll() = HttpResponse.ok(ProductConverter.toListDto(productServicePort.findAll()))

    @Get("/{id}")
    fun findById(@PathVariable id: UUID) = HttpResponse.ok(ProductConverter.toDto(productServicePort.findById(id)))

    @Put("/{id}")
    fun update(@PathVariable id: UUID, @Valid @Body dto: ProductDto): HttpResponse<Any> {
        productServicePort.update(ProductConverter.toProductWithId(id, dto))
        return HttpResponse.noContent()
    }

    @Delete("/{id}")
    fun delete(@PathVariable id: UUID): HttpResponse<Any> {
        productServicePort.delete(id)
        return HttpResponse.noContent()
    }
}
