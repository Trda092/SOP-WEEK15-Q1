package Server;

import com.proto.prime.PrimeFactoryGrpc;
import com.proto.prime.PrimeFactoryRequest;
import com.proto.prime.PrimeFactoryResponse;
import io.grpc.stub.StreamObserver;

public class PrimeNumberFactoryServiceImpl extends PrimeFactoryGrpc.PrimeFactoryImplBase {
@Override
    public void primeDecomposition(PrimeFactoryRequest request, StreamObserver<PrimeFactoryResponse> responseObserver){
        int numberInput = request.getNumber();
        int prime = 2;
//        PrimeFactoryResponse response = PrimeFactoryResponse.newBuilder().setPrime((int) result).build();
        while (numberInput >= prime*prime) {
            PrimeFactoryResponse response = PrimeFactoryResponse.newBuilder().setPrime(prime).build();
            if (numberInput % prime == 0){
            responseObserver.onNext(response);
            numberInput = numberInput/prime;
        }   else{
                prime = prime+1;
            }
        }
        responseObserver.onNext(PrimeFactoryResponse.newBuilder().setPrime(numberInput).build());
//    for (int i =1;i<=numberInput;i+=2){
//        PrimeFactoryResponse response = PrimeFactoryResponse.newBuilder().setPrime((int) numberInput/i).build();
//        responseObserver.onNext(response);
//    }
        responseObserver.onCompleted();
    }
}
