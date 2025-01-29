package com.splunk;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;

import java.util.function.Consumer;

public class TestApp {

    public static void main(String[] args) {
        AwsCredentialsProvider xxx = DefaultCredentialsProvider.create();
        S3Client s3 = S3Client.builder().credentialsProvider(xxx).build();
        System.out.println("s3 client created");
        try {
            s3.listObjectsV2(new Consumer<ListObjectsV2Request.Builder>() {
                @Override
                public void accept(ListObjectsV2Request.Builder builder) {
                    builder.bucket(null);
                }
            });
        } catch (Exception e) {
            System.out.println("Ignoring exception");
        }
        SpanContext context = Span.current().getSpanContext();
        System.out.println("ROOT: " + context.getTraceId() + ":" + context.getSpanId());
        while(true){
            myMethod();
        }

    }

    @WithSpan
    static void myMethod(){
        Span current = Span.current();
        System.out.println("Doing nothing for a bit: " + current.getSpanContext().getTraceId() + ":" + current.getSpanContext().getSpanId());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
