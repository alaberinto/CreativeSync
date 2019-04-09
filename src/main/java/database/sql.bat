@echo off
cd C:\Program Files\MySQL\MySQL Server 5.7\bin
mysqldump.exe  -u root -ppassword --complete-insert netflixdb > C:\Users\731866\backupFile.sql