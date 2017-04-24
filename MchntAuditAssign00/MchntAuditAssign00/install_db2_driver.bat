@echo off

set /p var=安装DB2驱动到本地Maven仓库，输入Y确认：
if /i %var% == Y (
    mvn install:install-file -Dfile=db2jcc.jar -DgroupId=com.ibm.db2 -DartifactId=db2jcc -Dversion=1.0.0 -Dpackaging=jar
    mvn install:install-file -Dfile=db2jcc_license_cisuz.jar -DgroupId=com.ibm.db2 -DartifactId=db2jcc_license_cisuz -Dversion=1.0.0 -Dpackaging=jar
    echo DB2驱动安装成功
) else (
    echo DB2驱动未安装
)

pause
