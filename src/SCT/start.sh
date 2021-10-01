curr_time=`date '+%m%d%y%H%M%S'`
PgName=`basename $0`
AP_PATH=/BATCH_JOB
EXE_PATH=/BATCH_JOB/EXE
SCT_PATH=/BATCH_JOB/SCT
LOG_PATH=/BATCH_JOB/log/op/monitor
LOGFILE=$LOG_PATH/$PgName.log.$curr_time


if [ -z $EXE_PATH ]
then
    echo "**---------------------------------------------------------**"
    echo "**                   Please Check AP Path                  **"
    echo "**---------------------------------------------------------**"
    sleep 10
    exit
fi

cd $SCT_PATH
echo " $LOGFILE starting `date` in `uname -n` " >> $LOGFILE

keywd=`ps -ef |grep $LOGNAME`

cnt=0

curr_date=`date '+%Y%m%d%H%M%S'`


### clprocCPG
    check_file=clprocCPG

    RsFlag=`echo "$keywd"|grep $check_file`
    if test -z "$RsFlag"
    then
        nohup $check_file > /dev/null 2>&1 &                             
        echo " $0, start $check_file , `date`" >> $LOGFILE
        cnt=`expr $cnt + 1`
    else
        echo " $0, $check_file exist `date`" >> $LOGFILE
    fi  	
    
### clprocCPG2FTP
    check_file=clprocCPG2FTP

    RsFlag=`echo "$keywd"|grep $check_file`
    if test -z "$RsFlag"
    then
        nohup $check_file > /dev/null 2>&1 &                             
        echo " $0, start $check_file , `date`" >> $LOGFILE
        cnt=`expr $cnt + 1`
    else
        echo " $0, $check_file exist `date`" >> $LOGFILE
    fi  	
    

### PermanentBatchJobManager
    check_file=PermanentBatchJobManager

    RsFlag=`echo "$keywd"|grep $check_file`
    if test -z "$RsFlag"
    then
        nohup $check_file > /dev/null 2>&1 &                             
        echo " $0, start $check_file , `date`" >> $LOGFILE
        cnt=`expr $cnt + 1`
    else
        echo " $0, $check_file exist `date`" >> $LOGFILE
    fi  	


### clprocECPG
    check_file=clprocECPG

    RsFlag=`echo "$keywd"|grep $check_file`
    if test -z "$RsFlag"
    then
        nohup $check_file > /dev/null 2>&1 &                             
        echo " $0, start $check_file , `date`" >> $LOGFILE
        cnt=`expr $cnt + 1`
    else
        echo " $0, $check_file exist `date`" >> $LOGFILE
    fi  	