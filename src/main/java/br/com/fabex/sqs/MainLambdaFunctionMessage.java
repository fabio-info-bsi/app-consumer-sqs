package br.com.fabex.sqs;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSBatchResponse;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

import java.util.ArrayList;
import java.util.List;

public class MainLambdaFunctionMessage implements RequestHandler<SQSEvent, SQSBatchResponse> {
    @Override
    public SQSBatchResponse handleRequest(SQSEvent sqsEvent, Context context) {
        List<SQSBatchResponse.BatchItemFailure> batchItemFailures = new ArrayList<>();
        for (SQSEvent.SQSMessage message : sqsEvent.getRecords()) {
            try {
                // TODO: Regra de negócio(Processar mensagem)
            } catch (Exception e) {
                // TODO: Tratativa do processamento (add mensagem nao processada para ser devolvida para SQS)
                batchItemFailures.add(new SQSBatchResponse.BatchItemFailure(message.getMessageId()));
            }
        }
        return new SQSBatchResponse(batchItemFailures);
    }
}