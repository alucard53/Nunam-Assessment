$projPath = Split-Path $myInvocation.MyCommand.Path -Parent
cd $projPath

mvn spring-boot:run
