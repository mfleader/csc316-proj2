cd C:\Users\Matthew\git\csc316-proj2\FamilyTrees\src
javac tree\proj2.java

java tree.proj2 < ..\test-files\small-input.txt > ..\test-files\small-OutActual.txt
::FC ..\test-files\small-OutActual.txt ..\test-files\small-output.txt

java tree.proj2 < ..\test-files\medium-input.txt > ..\test-files\medium-OutActual.txt
::FC ..\test-files\medium-OutActual.txt ..\test-files\meidum-output.txt