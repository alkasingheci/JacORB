#!/bin/sh
@JAVA_CMD@  -classpath @JACORB_HOME@/lib/idl.jar:@JACORB_HOME@/lib/logkit-1.2.jar:${CLASSPATH} org.jacorb.idl.parser  "$@"
