### To build

`./gradlew build`

### To run mutation tests
`./gradlew pitest`

### To run
In first console window `export SPRING_CONFIG_NAME=alice`

In second console window `export SPRING_CONFIG_NAME=bob`

### To perform a transaction
`
curl --header "Content-Type: application/json"
  --request POST
  --data '{"id": "has-to-be-unique", "amount": 20,"from": "alice","to": "bob"}'
  localhost:8090/trustline/payment
  `
### To reconcile from bob's log
`
curl -X POST \
  http://localhost:8090/trustline/reconcile \
  -H 'Content-Type: application/json' \
  -d bob
`

### Future development
* failure mode enhancements
  * automated failure mode testing of complex scenarios
* advanced reconciliation strategies
  * merge logs, automated conflict resolution
* performance improvements
  * snapshot of current balance instead of reconstructing from begining of time
  * async IO, resource pooling, etc
* correctness
  * property based testing
  
