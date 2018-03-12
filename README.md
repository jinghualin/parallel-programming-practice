# parallel-programming-practice

Aufgabe 1:
Ein Supermarkt besitzt 3 Pfandautomaten.
Es kommen 25 Kunden mit zufällig 2 bis 5 Körben Pfandgut. Alle 8 Sekunden kommt ein Kunde herein. Das Erfassen eines Korbes Pfandgut dauert zufällig zwischen 3 und 6 Sekunden. Setze diese Situation mit Threads, synchronized, wait und notify um. 

Aufgabe 2:
Stelle deinen Supermarkt Source Code auf das Executor Framework und Lock/Condition um. 

Aufgabe 3:
Stelle deinen Supermarkt Source Code auf einen Scheduled Executor mit Lambda Ausdrücken um. Nimm hierbei nun an, dass Kunden in fixen Intervallen von 7 Sekunden den Salon betreten. Ersetze die Belegung der Pfandautomaten mit einer Semaphore. 

Aufgabe 4:
Stelle deinen Supermarkt Source Code auf eine BlockingQueue mit Producer und Consumer um. Wie verändert sich die Lösung, wenn alle 10 Sekunden zusätzlich ein “Goldkunde” den Supermarkt betritt, dessen Pfandkorb vorrangig verarbeitet wird ? 

Aufgabe 5:
Bearbeite 5.3 Hilzer’s Barbershop Problem in Java.
