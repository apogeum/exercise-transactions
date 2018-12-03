### To build

`./gradlew build`

### To run mutation tests
`./gradlew pitest`

### To run
In first console window `export SPRING_CONFIG_NAME=alice`

In second console window `export SPRING_CONFIG_NAME=bob`

### To test operation

`
curl --header "Content-Type: application/json"
  --request POST
  --data '{"amount": 20,"from": "alice","to": "bob"}'
  localhost:8090/trustline/payment
  `
### Future development
* reconciliation functionality
  * merging conflicting transaction logs
  * model "source of truth"
* failure mode enhancements
  * more expressive error messages
  * automated failure mode testing of complex scenarios
  * align error communication with http protocol (codes)
* performance improvements
  * snapshot of current balance instead of reconstructing from begining of time
  * async IO, resource pooling, etc
* correctness
  * property based testing
  * mutation testing
  
