# Object Oriented Programming - Practice

Questi esercizi supportano l'apprendimento e la preparazione all'esame finale. Alcuni sono progettati per approfondire la comprensione oltre il materiale base.

## 📚 Indice dei Moduli

- [M1 - Esercizi base](#m1)
- [M2 & M3 - Classi, ereditarietà, interfacce, pattern](#m2--m3)
- [M4 - Eccezioni, logging, chain of responsibility](#m4)
- [M5 - Collections, Stream API, Lambda](#m5)
- [M6 - File I/O, serializzazione, Memento](#m6)
- [M7 - Threading, Executor, Deadlock, Observer](#m7)

## M1
1. Scrivi un programma che dichiari variabili di tipo diverso (`int`, `double`, `String`, `boolean`) e stampi i valori. Prova la conversione tra tipi.
2. Prendi due numeri `float` e stampa somma, differenza, prodotto, e quoziente (formattati a 2 decimali).
3. Scrivi un metodo che prende tre numeri e determina il più grande con `if-else`.
4. Scrivi un programma che prende i coefficienti `a`, `b`, `c` e calcola le radici reali di un’equazione quadratica. Se non esistono radici reali, stampa un messaggio.
5. Scrivi un programma che verifica se un numero intero è primo.
6. Metodo `perimeterRectangle(double length, double width)` che restituisce il perimetro di un rettangolo.
7. Metodo `isOdd(int num)` che restituisce `1` se il numero è dispari, `0` altrimenti.
8. Metodo `triangleArea(double a, double b, double c)` che calcola l’area usando la formula di Erone.
9. Data una tripletta di stringhe, stampa la concatenazione in ordine normale e inverso.
10. Metodo statico che conta e stampa quante parole ci sono in una frase.
11. Metodo che estrae e stampa una sottostringa da un indice dato.
12. Metodo che verifica se una stringa è palindroma (ignorando spazi e maiuscole).
13. Metodo che conta le occorrenze di un carattere in una stringa.
14. Metodo che conta quante vocali ci sono in una stringa (case-insensitive).
15. Metodo che trova l’indice della prima occorrenza di un carattere o stampa un messaggio se non presente.
16. Metodo che calcola e stampa il fattoriale di un numero.
17. Metodo che stampa i primi `n` numeri della serie di Fibonacci.
18. Metodo che calcola e stampa il MCD di due numeri.
19. Metodo che stampa la tabellina di `n`.
20. Metodo che stampa un pattern numerico con `n` righe.
21. Metodo che restituisce il `n`esimo numero primo.
22. Metodo che trova la somma massima di una sottosequenza contigua in un array.
23. Metodo che ruota un array verso destra di `k` posizioni.
24. Metodo che controlla se una stringa di parentesi `()[]{}` è bilanciata.
## M2 & M3
25. **Classe `Car`**
    - Attributi: `brand`, `model`, `year`
    - Due costruttori:
        - Uno che accetta solo `brand` e `model`, imposta `year` corrente
        - Uno che inizializza tutti gli attributi
    - Metodo `toString()`
    - Istanzia due oggetti e stampa i dettagli
26. **Classe `BankAccount`**
    - Attributo privato `balance`
    - Metodi: `deposit()`, `withdraw()`, `checkBalance()`
    - Impedisci prelievi superiori al saldo
27. **Classe `MathOperations`**
    - Due metodi `sum()` sovraccarichi: uno per `int`, uno per `double`
28. **Classe `Counter`**
    - Variabile statica `count`
    - Incrementata a ogni istanziazione
    - Metodo per recuperare `count`
29. **Classe `Circle`**
    - Attributo `radius`
    - Metodi: `calculateArea()`, `calculatePerimeter()`
30. **Classe `Student`**
    - Info studente e array di voti
    - Metodo per media e verifica superamento (`≥ 50`)
31. **Classe `Person` e sottoclasse `Student`**
    - `Person`: `name`, `age`
    - `Student`: `studentId`, metodo per stampare tutti i dettagli
32. **Classe astratta `Shape`**
    - Metodo astratto `calculateArea()`
    - Sottoclassi: `Rectangle`, `Circle` con implementazione area
33. **Interfaccia `Playable`**
    - Metodo `play()`
    - Classi `Guitar` e `VideoGame` che implementano `Playable`
34. **Interfaccia e gerarchia dispositivi**
    - `Chargeable` con `charge()`
    - `Device` astratta con `connectToWifi()`
    - `Smartphone` e `Laptop`: estendono `Device`, implementano `Chargeable`
    - `ElectricCar`: implementa solo `Chargeable`
    - Crea array di `Chargeable`, chiama `charge()`
35. **Struttura veicoli**
    - Interfaccia e classe astratta appropriate
    - Metodo comune `startEngine()`
    - Solo alcuni: `loadCargo()`, altri `move()`
    - Implementa `Car`, `Truck`, `Bicycle`
36. **Conservazione della fauna**
    - Classe astratta `Animal`: `eat()`, `sleep()`
    - Interfacce: `Flyable`, `Swimmable`
    - Classi: `Lion`, `Penguin`, `Fish`, `Eagle`, ecc.
37. **Classe `WildlifeConservationSystem`**
    - Array di `Animal`
    - Metodo statico `printAnimalDetails(Animal animal)`
    - Metodi sovraccaricati `performAction(Flyable)` e `performAction(Swimmable)`
38. **Sistema SIM Card**
    - Classe `Call`: durata e numero chiamato
    - Classe `SIM`: numero, credito, ultime 5 chiamate (array fisso)
    - Aggiungi almeno 6 chiamate, stampa i dettagli
39. **Sistema E-commerce**
    - `Product`: codice, descrizione, prezzo, quantità
    - `Customer`: ID, nome, email, data registrazione
    - `Cart`: cliente associato, lista prodotti, costo totale
    - Metodi: aggiunta/rimozione, controllo stock, stampa dettagli
40. **Builder Pattern per `Car`**
    - Classe `Car`: `brand`, `model`, `engine`, `features`
    - Classe `CarBuilder`: metodi concatenati
    - Usa builder per creare configurazioni diverse
41. **Factory Pattern per documenti**
    - Classe astratta `Document`: metodo `generate()`
    - Sottoclassi: `PDFDocument`, `WordDocument`, `TextDocument`
    - `DocumentFactory` restituisce istanza giusta in base al tipo
42. **Proxy Pattern per immagini**
    - Interfaccia `Image` con `display()`
    - `RealImage`: carica e mostra
    - `ProxyImage`: carica solo alla prima chiamata `display()`
43. **Facade per Home Theater**
    - Componenti: `DVDPlayer`, `Projector`, `SoundSystem`, `Lights`
    - Classe `HomeTheaterFacade`: metodi `startMovie()`, `endMovie()`
44. **Gerarchia dispositivi/remoti**
    - Dispositivi: `TV`, `Radio`
    - Remoti: `BasicRemote`, `AdvancedRemote`
    - Tightly coupled: es. `TVBasicRemote`, `TVAdvancedRemote`
45. **Bridge Pattern per remoti e dispositivi**
    - `RemoteControl` (astrazione): `powerOn()`, `powerOff()`
    - `Device` (interfaccia): `TV`, `Radio`
    - `BasicRemote`, `AdvancedRemote` indipendenti dal tipo dispositivo
## M4
46. **Gestione divisione e input**
    - Metodo che legge un intero dall’utente e divide 100 per quel numero
    - Gestisce `ArithmeticException` e `InputMismatchException` con messaggi amichevoli
47. **Radice quadrata e logging**
    - Calcola la radice quadrata di un numero
    - Usa `Logger` per loggare input, risultati, e input non validi (es. numeri negativi)
    - Configura il logger per scrivere su file
48. **Parsing sicuro di numeri**
    - Metodo che prende una lista di stringhe e prova a convertirle in numeri
    - Salta e logga quelle non parsabili
49. **Sistema di login con tentativi**
    - Simula un login
    - Se username/password errati, solleva un’eccezione personalizzata
    - Blocca dopo 3 tentativi falliti
50. **Propagazione delle eccezioni**
    - Tre metodi: `readFile()`, `processData()`, `main()`
    - `readFile()` lancia `IOException`
    - Non catturare fino a `main()` per mostrare la propagazione
51. **Custom Exception Wrapper**
    - Metodo in libreria che chiama codice di terze parti con potenziali eccezioni unchecked
    - Cattura internamente, rilancia una `LibraryException` personalizzata
52. **Chain of Responsibility Logger**
    - Diverse classi logger: `ConsoleLogger`, `FileLogger`, `EmailLogger`
    - Ogni logger gestisce un solo tipo di log (INFO, WARNING, ERROR)
    - Se il livello non è gestito, passa al logger successivo
53. **Enum e loggers**
    - Definisci un `enum LogLevel { INFO, WARNING, ERROR }`
    - Classe astratta `Logger` con:
        - `setNext(Logger)`
        - `log(LogLevel, String message)`
54. **Implementazioni concrete dei logger**
    - `ConsoleLogger`: stampa su console se `INFO`
    - `FileLogger`: simula scrittura su file per `WARNING`
    - `EmailLogger`: simula invio email per `ERROR`
55. **Catch generico e sicurezza**
    - Classe che cattura tutte le eccezioni con `catch(Exception e)`
    - Logga i messaggi
    - Spiega perché è cattiva pratica (es. Log4Shell) e i rischi di loggare input non fidati

## M5
56. **Liste di giocatori**
    - Memorizza nomi dei giocatori in una `List`
    - Dimostra la differenza tra `ArrayList` e `LinkedList`
    - Spiega impatto sulle performance di inserimenti/cancellazioni
57. **Classe generica `Box<T>`**
    - Metodi: `getContent()`, `setContent()`
    - Testa con `Integer`, `String`, e una classe personalizzata `Player`
58. **Lobby multiplayer**
    - Usa `List<Player>` per una stanza di gioco
    - Usa `Queue<Player>` per la coda matchmaking
    - Implementa: unione/uscita dalla stanza, generazione sessioni
    - Spiega perché `Queue` è migliore per il matchmaking
59. **Tracciamento achievements**
    - `Set<String>` per achievements unici
    - `Map<String, Integer>` per statistiche tipo "mostri sconfitti"
    - Aggiungi metodi per aggiornare statistiche e sbloccare achievements
    - Estendi a più giocatori usando `Map<Player, PlayerData>`
60. **GameEventLog e Iterator Pattern**
    - Collezione personalizzata con eventi in-game e timestamp
    - Classe interna `RecentEventIterator` che restituisce solo eventi ultimi 5 minuti
    - Spiega vantaggi dell'`Iterator Pattern` e come riusarlo per altri filtri
61. **Classe `Character` ordinabile**
    - Attributi: `name`, `level`, `XP`
    - Implementa `Comparable<Character>` per ordinamento naturale su `level`
    - Crea due `Comparator`: uno per `XP` decrescente, uno per `name` alfabetico
    - Confronta `Comparable` vs `Comparator` e quando usare ciascuno
62. **Lista di oggetti `Item`**
    - Ogni `Item` ha `name` e `price`
    - Stampa usando `forEach()` e lambda
    - Usa `removeIf()` per rimuovere item sotto una certa soglia
    - Calcola media dei prezzi usando `Stream`
    - Confronta con versione usando `for-loop` tradizionale
63. **Stream su `Transaction`**
    - Filtra transazioni sopra una certa soglia
    - Raggruppa per utente e calcola spesa totale
    - Trova i 3 utenti con maggiore spesa
    - Ordina per data
    - Spiega trasformazioni dei dati con Stream vs loop
64. **Stream su `Product`**
    - Raggruppa prodotti per `category`
    - Trova il più venduto per ogni categoria
    - Output: `Map<String, Product>`, chiave è `category`
65. **Even numbers trasformati (due versioni)**
    - Lista di interi
    - Filtra pari
    - Moltiplica ×3
    - Colleziona in nuova lista
    - Scrivi in forma sia `for-loop` sia `Stream`
    - Confronta chiarezza e leggibilità
66. **Libri in mappa filtrata**
    - Lista di `Book(title, author, yearPublished, price)`
    - Filtra libri dopo il 2010
    - Colleziona in `Map<String, Double>` con `title` come chiave
    - Gestione titoli duplicati: tieni il più costoso
67. **Optional e Stream**
    - Lista di `Order`, ogni `Order` ha `Optional<Customer>`, e `Customer` ha `Optional<String> email`
    - Estrai solo email presenti
    - Colleziona in `List<String>`

## M6
68. **Lettura file `.txt`**
    - Usa `BufferedReader` per leggere e stampare ogni riga
    - Gestisci le eccezioni e chiudi correttamente le risorse
69. **Scrittura file con messaggi**
    - Metodo che riceve una lista di `String`
    - Scrive ogni riga nel file con `BufferedWriter` in modalità append
70. **Conteggio parole da file**
    - Leggi un `.txt`
    - Conta quante volte appare ogni parola (ignorando case e punteggiatura)
    - Salva in `Map<String, Integer>`
71. **Copia file binari**
    - Copia file `.jpg` (o simili) con `FileInputStream` e `FileOutputStream`
    - Usa un buffer per migliorare le performance
72. **Scrittura e lettura dati binari**
    - Usa `DataOutputStream` per scrivere `int`, `double`, `String`
    - Leggi con `DataInputStream` e stampa i valori
73. **Serializzazione oggetti**
    - Classe `User(name, email, age)` implementa `Serializable`
    - Scrivi un oggetto su file e leggilo per verificarne l’integrità
74. **Serializzazione lista di oggetti**
    - Lista di `Book` serializzata su file
    - Deserializzala e stampa il contenuto
75. **Gestione `serialVersionUID`**
    - Aggiungi un nuovo campo a `User`
    - Definisci `serialVersionUID`
    - Verifica la compatibilità con una versione precedente
76. **Stampa file `.txt` in directory**
    - Usa `Files.list()` e `Stream` per stampare tutti i `.txt` in una directory
    - Ordina alfabeticamente
77. **Lettura file con `FileChannel`**
    - Usa `FileChannel` e `ByteBuffer` per leggere file in memoria e stamparlo
    - Evita stream legacy
78. **Filtraggio righe con Stream**
    - Usa `Files.lines()`:
        - Filtra righe con parola chiave
        - Trasforma in maiuscolo
        - Salva in un nuovo file
79. **Input utente con controlli**
    - Prende input dell’utente e lo scrive su file
    - Verifica che:
        - Non contenga parole proibite
        - Sia pulito da spazi superflui
        - Sia valido, altrimenti stampa errore
80. **Text editor con undo (Memento Pattern)**
    - `EditorState`: rappresenta il contenuto corrente (memento)
    - `TextEditor`: modifica e salva/ripristina stato (originator)
    - `History`: gestisce versioni precedenti con uno stack (caretaker)
    - Implementa `undo()` per tornare allo stato precedente
81. **CLI per log filtering**
    - App carica un grande file di log
    - Permette filtri per:
        - Intervallo di date
        - Livello di log (`INFO`, `WARN`, `ERROR`)
        - Sottostringa
    - Usa `Stream<String>` da `Files.lines()` per elaborazione efficiente

## M7
82. **Thread con `NumberPrinter`**
    - Classe `NumberPrinter` estende `Thread`
    - Ogni thread stampa da 1 a 10 con ritardo di 500ms
    - Avvia due istanze e osserva l’esecuzione parallela
    - Spiega cosa cambia se si chiama `run()` invece di `start()`
83. **Download file simulato**
    - Classe `FileDownloader` implementa `Runnable`
    - Simula download stampando avanzamento e dormendo
    - Avvia più thread con nomi file diversi
84. **Esecuzione con `ExecutorService`**
    - Crea 5 `Callable` o `Runnable`
    - Usali con un thread pool fisso da 2 thread
    - Stampa quale thread esegue ogni task
    - Chiudi l’executor in modo ordinato
85. **Contatore sincronizzato**
    - Classe `Counter` con metodo `increment()`
    - 100 thread che lo invocano 1000 volte
    - Usa `synchronized` per garantire correttezza
    - Confronta il risultato rimuovendo `synchronized`
86. **Produttore-Consumatore**
    - Produttore aggiunge oggetti ogni secondo
    - Consumatore li rimuove se presenti
    - Usa `wait()` e `notify()` per la sincronizzazione
    - Logga le interazioni
87. **Simulazione deadlock**
    - Due risorse: `Printer` e `Scanner`
    - Due thread che bloccano le risorse in ordine inverso
    - Mostra come si crea un deadlock
    - Previeni con timeout o ordine di acquisizione dei lock
88. **Observer pattern con azioni su stock**
    - `Stock` è il soggetto osservato
    - Osservatori: `EmailAlert`, `MobileApp`
    - Notifica aggiornamenti prezzo a tutti gli osservatori
    - Dimostra registrazione, deregistrazione e notifica
89. **Singleton `AppConfig`**
    - Classe Singleton thread-safe che carica impostazioni
    - Usa lazy initialization con: `synchronized`, static holder o `volatile`
    - Verifica comportamento da thread multipli
90. **Chatroom con Observer**
    - `ChatRoom`: soggetto che gestisce utenti iscritti
    - Metodo `postMessage(String)` notifica tutti
    - Utenti (`User`) implementano `receive(String message)`
    - Dimostrazione:
        - Crea 3 utenti: `UserA`, `UserB`, `UserC`
        - Posta messaggio "Welcome"
        - Disiscrivi `UserB`
        - Posta "UserB has left" e mostra che solo `UserA` e `UserC` ricevono il messaggio