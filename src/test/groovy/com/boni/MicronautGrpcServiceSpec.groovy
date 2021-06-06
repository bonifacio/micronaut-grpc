package com.boni

import com.boni.grpc.MicronautGrpcRequest
import com.boni.grpc.MicronautGrpcServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import spock.lang.Unroll

import javax.inject.Inject

@MicronautTest
class MicronautGrpcServiceSpec extends Specification {

    @Inject
    MicronautGrpcServiceGrpc.MicronautGrpcServiceBlockingStub stub

    @Unroll
    void 'It should return [#messageText] when name is [#request.name]'() {

        when:
        def response = stub.send(request)

        then:
        response.message == messageText

        where:
        request                                                     | messageText
        MicronautGrpcRequest.newBuilder().setName("João").build() | "Olá João"
        MicronautGrpcRequest.newBuilder().setName("Maria").build()  | "Olá Maria"

    }

    @Bean
    MicronautGrpcServiceGrpc.MicronautGrpcServiceBlockingStub blockingStub(
            @GrpcChannel(GrpcServerChannel.NAME) ManagedChannel channel) {
        return MicronautGrpcServiceGrpc.newBlockingStub(channel);
    }
}
