package com.example.common

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*

suspend inline fun <reified T : Any> ApplicationCall.ok(body: T) {
    this.respond(body)
}

suspend fun ApplicationCall.sendBadRequest() {
    this.respondText(Messages.BadRequest, status = HttpStatusCode.BadRequest)
}

suspend fun ApplicationCall.created() {
    this.respondText(Messages.Created, status = HttpStatusCode.Created)
}

suspend fun ApplicationCall.internalServerError() {
    this.respondText(Messages.InternalServerError, status = HttpStatusCode.InternalServerError)
}

suspend fun ApplicationCall.notFound() {
    this.respondText(Messages.NotFound, status = HttpStatusCode.NotFound)
}

suspend fun request(call: ApplicationCall, function: suspend (ApplicationCall) -> Unit) {
    try {
        function(call)
    } catch (_: BadRequestException) {
        call.sendBadRequest()
    } catch (_: NotFoundException) {
        call.notFound()
    } catch (_: Exception) {
        call.internalServerError();
    }
}
