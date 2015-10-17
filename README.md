# Rock Paper Scissors

Rock Paper Scissor game implemented in Scala

### Run tests
`sbt clean coverage test`

### Tests coverage reports
Available at `target/scala-2.11/scoverage-report/`

### Build the executable jar
`sbt assembly`

### Usage (command line)

#### Rock Paper Scissor game

Computer VS Computer
* `java -jar target/scala-2.11/rock-paper-scissors-1.0.jar`

You VS Computer (allowed values for `your_selection`: 'rock', 'paper' or 'scissors')
* `java -jar target/scala-2.11/rock-paper-scissors-1.0.jar your_selection`

#### Rock Paper Scissors Lizard Spock game

Computer VS Computer
* `java -Dgame=extended -jar target/scala-2.11/rock-paper-scissors-1.0.jar`

You VS Computer (allowed values for `your_selection`: 'rock', 'paper', 'scissors', 'lizard' or 'spock')
* `java -Dgame=extended -jar target/scala-2.11/rock-paper-scissors-1.0.jar your_selection`

Note: `-Dgame=classic` is also supported to play the classic edition of the game. 
