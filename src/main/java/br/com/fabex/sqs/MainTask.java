package br.com.fabex.sqs;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

public class MainTask {

    public static void main(String[] args) {
        new MainTask().readMessageSimpleQueueService();
    }

    public void readMessageSimpleQueueService() {
        final String queue = "SQS_ASYNC_REQUEST";
        try (SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build()) {
            GetQueueUrlResponse queueUrl = sqsClient.getQueueUrl(GetQueueUrlRequest.builder().queueName(queue).build());
            ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                    .queueUrl(queueUrl.queueUrl())
                    .maxNumberOfMessages(5)
                    .build();
            sqsClient.receiveMessage(receiveMessageRequest).messages().forEach(message -> {
                try {
                    // TODO: Regra de negócio(Processar mensagem)
                    /* Removendo mensagem da fila */
                    DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                            .queueUrl(queueUrl.queueUrl())
                            .receiptHandle(message.receiptHandle())
                            .build();
                    sqsClient.deleteMessage(deleteMessageRequest);
                } catch (Exception e) {
                    // TODO: Tratativa da Regra de negócio (Apenas 'solta' a mensagem)
                }
            });
        }
    }
}
