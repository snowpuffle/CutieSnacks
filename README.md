[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
$OutputEncoding = [System.Text.Encoding]::UTF8
chcp 65001
javac -encoding UTF-8 Main.java game\*.java levels\*.java objects\*.java
