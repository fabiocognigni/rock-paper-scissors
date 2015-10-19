jarFile=target/scala-2.11/rock-paper-scissors-1.0.jar

if ! [ -a $jarFile ]  ; then
	echo "Executable JAR not found: building from sources ..."
	sbt assembly
fi

java -Dgame=classic -jar $jarFile $@
