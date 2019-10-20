# My implementation of Sales Taxes Problem
Il progetto è stato sviluppato in linguaggio Java, mediante l'IDE IntelliJ.

## Struttura
Il progetto è sviluppato in 4 classi.

### Good
Classe che rappresenta un bene. In essa vengono definiti 6 attributi: 
- **name**: il nome del bene;
- **quantity**: la quantità di oggetti di quel bene;
- **price**: il prezzo del singolo oggetto;
- **tax**: la tassa totale del bene (quindi già calcolata su quanti sono gli oggetti);
- **isTaxFreeGood**: un flag che identifica un bene esenteasse (quindi libri, cibo e medicine);
- **isImported**: un flag che identifica un bene importato.

Tramite il metodo *calculateTax()* calcolo la tassa totale del bene verificando i 4 casi possibili dati dalle combinazioni dei due flag.
Inoltre il metodo *customRound()* mi consente di approssimare il risultato del calcolo della tassa allo 0.05 più vicino in eccesso. 

### Input
Classe che rappresenta una lista di beni sulla quale calcolare l'importo totale. In Input troviamo:
- **goods**: l'ArrayList di beni che un input possiede;
- **totalTaxes**: la somma delle tasse totali dei beni dell'input;
- **inputTotal**: il costo totale dell'input.

Tutti gli attributi sono calcolati e assegnati nel costruttore, che prende in input l'ArrayList *goods*.

### Loader
La classe Loader mi consente di caricare un file di testo contenente i vari input, e generare l'ArrayList finale di Input sulla quale dovrò poi generare il mio output. 
L'oggetto *loader* viene instanziato nel *main()* della classe SalesTaxes, passando il path relativo del file da caricare. Successivamente, il metodo *loadInputs()* processerà tutto il file di testo generando i vari beni relativi ad un input (tramite la chiamata al metodo *createGood()*), e poi salverà tutti gli input in un ArrayList<Input> che verrà restituito al *main()*.
Ogni bene viene riconosciuto tramite degli array statici che contengono delle parole riguardanti cibo, libri e medicine. Inoltre viene anche riconosciuto se un bene è importato o meno, consentendo di settare i 2 flag relativi per il calcolo corretto delle tasse da applicare.
  
### SalesTaxes
Infine abbiamo la classe principale. Ho implementato in aggiunta un menù per poter selezionare più file di testo con il metodo *chooseInputFile()*, nel caso si hanno a disposizione più liste della spesa.
Nel costruttore è implementato tutto il resto: semplicemente si calcolano i vari input tramite il metodo della classe Loader, e poi si stampa l'output formattato come da consegna.

## Avvio del programma
Se si possiede l'IDE IntelliJ basta semplicemente, una volta nella schermata principale, selezionare "Check out from Version Control" -> "GIT" e clonare dal link github. Una volta caricato il progetto bisogna aprire la classe SalesTaxes nell'area a sinistra e premere la freccia verde accanto la sua dichiarazione.

Se si volesse lanciare il programma manualmente tramite Windows CMD, i passaggi sono i seguenti:
- Scaricare l'archivio ZIP da Github ed estrarlo;
- Copiare la cartella "input" dentro la cartella SalesTaxes, dove risiedono i file sorgente;
- Lanciare una finestra di prompt nel path dei file sorgente e compilare tramite comando "javac -d . Good.java Input.java Loader.java SalesTaxes.java";
- Eseguire tramite il comando "java com.xpeppers.salestaxes.SalesTaxes".

Tramite altri IDE potrebbero esserci procedure simili ad IntelliJ, o comunque tramite altri sistemi operativi possono esserci procedure simili all'apporccio manuale su Windows, ma non ho testato personalmente.
