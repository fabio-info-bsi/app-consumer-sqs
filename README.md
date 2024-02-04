# app-consumer-sqs
Um exemplo de consumers de sqs com @EnableScheduling e Lambda handler em Java.

## Usou/Criou:
- Aws Sqs (dependencias)
- @EnableScheduling & @Scheduled(fixedRate = 60_000, initialDelay = 10_000)
- RequestHandler (Lambda)
- SQSEvent & SQSBatchResponse
- Tratativas de erros de mensage em lote
