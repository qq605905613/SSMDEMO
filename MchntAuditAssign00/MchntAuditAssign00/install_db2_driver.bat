@echo off

set /p var=��װDB2����������Maven�ֿ⣬����Yȷ�ϣ�
if /i %var% == Y (
    mvn install:install-file -Dfile=db2jcc.jar -DgroupId=com.ibm.db2 -DartifactId=db2jcc -Dversion=1.0.0 -Dpackaging=jar
    mvn install:install-file -Dfile=db2jcc_license_cisuz.jar -DgroupId=com.ibm.db2 -DartifactId=db2jcc_license_cisuz -Dversion=1.0.0 -Dpackaging=jar
    echo DB2������װ�ɹ�
) else (
    echo DB2����δ��װ
)

pause
