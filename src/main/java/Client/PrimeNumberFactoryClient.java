package Client;

import com.proto.prime.PrimeFactoryGrpc;
import com.proto.prime.PrimeFactoryRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class PrimeNumberFactoryClient {
    public static void main(String[] args) {
        System.out.println("Hello Client");
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",50052).usePlaintext().build();
        PrimeFactoryGrpc.PrimeFactoryBlockingStub steam = PrimeFactoryGrpc.newBlockingStub(channel);
        Scanner Obj = new Scanner(System.in);
//        int inputNumber = 55;
        System.out.print("Client to Server: ");
        int inputNumber = Obj.nextShort();

        PrimeFactoryRequest primeFactoryRequest = PrimeFactoryRequest.newBuilder().setNumber(inputNumber).build();

        steam.primeDecomposition(primeFactoryRequest).forEachRemaining(primeFactoryResponse ->{
            System.out.println("Server to Client:"+ primeFactoryResponse.getPrime());
        });
//        PrimeFactoryResponse primeFactoryResponse = primeClient.primeDecomposition(primeFactoryRequest);
//
//        System.out.println("Server to Client:"+ primeFactoryResponse.getPrime());
//        PrimeFactoryGrpc.PrimeFactoryFutureStub asyncClient = PrimeFactoryGrpc.newFutureStub(channel);
        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
