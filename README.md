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

(in *nix systems you can just use: `./rock-paper-scissors.sh`)

You VS Computer (allowed values for `your_item`: 'rock', 'paper' or 'scissors')
* `java -jar target/scala-2.11/rock-paper-scissors-1.0.jar your_item`

(in *nix systems you can just use: `./rock-paper-scissors.sh your_item`)

#### Rock Paper Scissors Lizard Spock game

Computer VS Computer
* `java -Dgame=extended -jar target/scala-2.11/rock-paper-scissors-1.0.jar`

(in *nix systems you can just use: `./rock-paper-scissors-lizard-spock.sh`)

You VS Computer (allowed values for `your_item`: 'rock', 'paper', 'scissors', 'lizard' or 'spock')
* `java -Dgame=extended -jar target/scala-2.11/rock-paper-scissors-1.0.jar your_item`

(in *nix systems you can just use: `./rock-paper-scissors-lizard-spock.sh your_item`)

Note: `-Dgame=classic` is also supported to play the classic edition of the game. 
