# Rock Paper Scissors

Rock Paper Scissor game implemented in Scala

### Run tests
`sbt clean coverage test`

### Tests coverage reports
Available at `target/scala-2.11/scoverage-report/`

### Build the executable jar
`sbt assembly`

### Command line usage
* `java -jar target/scala-2.11/rock-paper-scissors-1.0.jar` for a Computer VS Computer game;
* `java -jar target/scala-2.11/rock-paper-scissors-1.0.jar your_selection_arg` where `your_selection_arg` can be: 'rock', 'paper' or 'scissors'.
