#!/bin/sh
mainRoutine() {
	args=$1
	batchSize=$2
	for i in `seq 1 ${batchSize-5}`
	do
		gradle runEcMain"${1}"
	done
	sed -i '0,/^/s//{"ecBatch": [/' ecLog.json
	sed -i '$ s/.$/]}/' ecLog.json
	gradle runEcAnalytics	
	mv ecLog.json log/ecLog1_3"${args}".json
	mv ecAnalytics.json log/ecAnalytics1_3"${args}".json
}
mkdir -p log
rm -f ecLog.json
rm -f ecAnalytics.json
mainRoutine "" $1
mainRoutine f $1
mainRoutine p $1
mainRoutine fp $1
