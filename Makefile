
.PHONY: create-golden-master
create-golden-master:
	./gradlew test --tests '*GoldenMaster.creation'

#--stacktrace --debug


.PHONY: check-golden-master
check-golden-master:
	./gradlew test --tests '*GoldenMasterTest'


