package Server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class PrimeNumberFactoryServer {
    public static void main(String[] args){
        System.out.println("Hello gRPC");
        Server server = ServerBuilder.forPort(50052).addService(new PrimeNumberFactoryServiceImpl()).build();
        try {
            server.start();
            System.out.println("server start");
        } catch (IOException e){
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("Recieved Shutdown Request");
            server.shutdown();
            System.out.println("Successfully Shutdown Server");
        }));
        try{
            server.awaitTermination();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
