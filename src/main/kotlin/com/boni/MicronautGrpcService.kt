package com.boni

import com.boni.grpc.MicronautGrpcReply
import com.boni.grpc.MicronautGrpcRequest
import com.boni.grpc.MicronautGrpcServiceGrpc
import io.grpc.stub.StreamObserver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class MicronautGrpcService : MicronautGrpcServiceGrpc.MicronautGrpcServiceImplBase() {

    override fun send(
        request: MicronautGrpcRequest,
        responseObserver: StreamObserver<MicronautGrpcReply>) {

        logger.info("Request: $request")
        val reply = MicronautGrpcReply.newBuilder()
            .setMessage("Olá ${request.name}")
            .build()
        logger.info("Reply: $reply")
        responseObserver.onNext(reply)
        responseObserver.onCompleted()
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(MicronautGrpcService::class.java)
    }
}