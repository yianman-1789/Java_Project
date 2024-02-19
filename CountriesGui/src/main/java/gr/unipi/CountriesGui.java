package gr.unipi;

import gr.unipi.CountriesApi;
import gr.unipi.CountryStructure.Country;
import gr.unipi.CountryStructure.Currency;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CountriesGui extends Application implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    private Scene mainScene;
    private Scene resultScene;
    private TextField searchField;
    private Label errorLabel;
    private Button backButton;
    private Button searchByNameButton;
    private Button searchByCurrencyButton;
    private Button searchByLanguageButton;
    private Button getAllCountriesButton;

    public static void main(String[] args) {
        launch(args);
    }

    // Αρχικοποιεί την αρχική οθόνη της εφαρμογής και βάζει την κύρια οθόνη στο stage
    @Override
    public void start(Stage primaryStage) {
        // Αποθηκεύει το stage σε μεταβλητή για να μπορούμε να αλλάξουμε την οθόνη αργότερα
        this.primaryStage = primaryStage;
        // Ορίζει τον τίτλο της εφαρμογής
        primaryStage.setTitle("Countries Search App");

        // Δημιουργεί το layout της αρχικής οθόνης και ορίζει αποστάσεις
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(100));
        mainLayout.setAlignment(Pos.CENTER);

        // Προσθέτει έναν τίτλο στην αρχική οθόνη
        Label titleLabel = new Label("Countries Search App");
        titleLabel.setStyle("-fx-font-size: 22");
        mainLayout.getChildren().add(titleLabel);

        createElements(mainLayout);

        // Ορίζει την αρχική οθόνη της εφαρμογής μαζί με το μέγεθος της και το εμφανίζει
        mainScene = new Scene(mainLayout, 650, 500);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    // Δημιουργεί τα στοιχεία της αρχικής οθόνης
    // Περιέχει ένα πεδίο κειμένου, 4 κουμπιά για αναζήτηση
    // Επίσης περιέχει ένα label που εμφανίζει τυχόν σφάλματα
    private void createElements(VBox mainLayout) {
        // Ορισμός του text field
        searchField = new TextField();
        searchField.setStyle("-fx-font-size: 14");
        searchField.setPromptText("Enter search...");

        // Ορισμός του label για τα σφάλματα
        // Ξεκινάει ως κενό και αν υπάρξει σφάλμα, θα αλλάξει το περιεχόμενο του
        errorLabel = new Label("");
        errorLabel.setStyle("-fx-font-size: 14; -fx-text-fill: red");

        // Δημιουργία των κουμπιών
        searchByNameButton = new Button("Get country by name");
        searchByCurrencyButton = new Button("Get country by currency");
        searchByLanguageButton = new Button("Get country by language");
        getAllCountriesButton = new Button("Get all countries");
        searchByNameButton.setStyle("-fx-font-size: 14");
        searchByCurrencyButton.setStyle("-fx-font-size: 14");
        searchByLanguageButton.setStyle("-fx-font-size: 14");
        getAllCountriesButton.setStyle("-fx-font-size: 14");

        // Προσθέτει τον event handler σε κάθε κουμπί
        // Όταν πατηθεί ένα κουμπί, θα καλείται η μέθοδος handle που έχει υλοποιηθεί παρακάτω
        // Με το this, λέμε ότι η κλάση αυτή (CountriesGui) θα είναι υπεύθυνη για την επεξεργασία του event εφόσον κάνει implement το EventHandler
        searchByNameButton.setOnAction(this);
        searchByCurrencyButton.setOnAction(this);
        searchByLanguageButton.setOnAction(this);
        getAllCountriesButton.setOnAction(this);

        // Προσθέτει τα στοιχεία που φτιάχτηκαν στο layout
        mainLayout.getChildren().add(searchField);
        mainLayout.getChildren().add(searchByNameButton);
        mainLayout.getChildren().add(searchByCurrencyButton);
        mainLayout.getChildren().add(searchByLanguageButton);
        mainLayout.getChildren().add(getAllCountriesButton);
        mainLayout.getChildren().add(errorLabel);
    }

    // Εφόσον η κλάση μας κάνει implement την EventHandler, πρέπει να υλοποιήσουμε την μέθοδο handle
    // Δέχεται ένα event και ανάλογα με το κουμπί που πατήθηκε, αλλάζει την οθόνη της εφαρμογής
    // Όταν γίνεται αναζήτηση και υπάρξει σφάλμα, εμφανίζει το σφάλμα στην αρχική οθόνη μέσω ενός Label
    @Override
	public void handle(ActionEvent event) {
        // Ανάλογα με την τιμή του event, καταλαβαίνουμε ποιο κουμπί πατήθηκε και τρέχουμε τον ανάλογο κώδικα

        // Αν πατηθεί το κουμπί back, επιστρέφει στην αρχική οθόνη
		if (event.getSource() == backButton) {
			primaryStage.setScene(mainScene);

		}

        // Τα 4 κουμπιά αναζήτησης είναι παρόμοια μεταξύ τους
        // Καλούν την αντίστοιχη μέθοδο του CountriesApi και μέσω της showSearchResult εμφανίζουν μια νέα οθόνη με τα αποτελέσματα
        // Αν υπάρξει σφάλμα, ανανεώνεται η τιμή του errorLabel και δείχνει το σφάλμα
        else if (event.getSource() == searchByNameButton) {
            try{
                Country[] countries = CountriesApi.getCountryByName(searchField.getText());
                showSearchResult(countries);

                errorLabel.setText("");
            } catch (Exception e){
                errorLabel.setText("Error: Country with name " + searchField.getText() + " not found! Please try again.");
            }

        } else if (event.getSource() == searchByCurrencyButton) {
            try{
                Country[] countries = CountriesApi.getCountryByCurrency(searchField.getText());
                showSearchResult(countries);

                errorLabel.setText("");
            } catch (Exception e){
                errorLabel.setText("Error: Countries with currency " + searchField.getText() + " not found! Please try again.");
            }

        } else if (event.getSource() == searchByLanguageButton) {
            try{
                Country[] countries = CountriesApi.getCountryByLanguage(searchField.getText());
                showSearchResult(countries);
                
                errorLabel.setText("");
            } catch (Exception e){
                errorLabel.setText("Error: Countries with language " + searchField.getText() + " not found! Please try again.");
            }

        } else if (event.getSource() == getAllCountriesButton) {
            try{
                Country[] countries = CountriesApi.getAllCountries();
                showSearchResult(countries);
                
                errorLabel.setText("");
            } catch (Exception e){
                Label errorLabel = new Label("Error: Countries not found! Please try again.");
            }

        }
	}

    // Υπεύθυνο για την εμφάνιση νέας οθόνης που θα περιέχει τα αποτελέσματα της αναζήτησης
    // Εμφανίζει τα αποτελέσματα σε πεδίο που μπορεί να κάνει scroll
    // Περιέχει κουμπί για επιστροφή στην αρχική οθόνη
    private void showSearchResult(Country[] countries) {
        // Δημιουργία ενός νέου layout για την νέα οθόνη
        VBox resultLayout = new VBox(10);
        resultLayout.setPadding(new Insets(50));
        resultLayout.setAlignment(Pos.CENTER);

        // Δημιουργία του κουμπιού back
        // Σαν event handler έχει και αυτό την κλάση CountriesGui
        backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14");
        backButton.setOnAction(this);

        // Δημιουργία του scroll pane που θα περιέχει τα αποτελέσματα με την δυνατότητα scroll
        // Το layout που περιέχει τα αποτελέσματα είναι ένα VBox
        ScrollPane scrollPane = new ScrollPane();
        VBox countriesLayout = new VBox(25);
        countriesLayout.setAlignment(Pos.CENTER_LEFT);

        // Για κάθε χώρα που βρέθηκε, δημιουργείται ένα label από την μέθοδο createCountryLabel και προστίθεται στο layout
        for (Country country : countries) {
            Label countryLabel = createCountryLabel(country);

            countriesLayout.getChildren().add(countryLabel);
        }

        // Προσθέτει το layout με τα αποτελέσματα στο scroll pane και το scroll pane με το κουμπί back στο layout της νέας οθόνης
        scrollPane.setContent(countriesLayout);
        resultLayout.getChildren().add(backButton);
        resultLayout.getChildren().add(scrollPane);

        // Τέλος, δημιουργεί την νέα οθόνη με το layout που φτιάχτηκε και γίνεται αλλαγή της οθόνης
        resultScene = new Scene(resultLayout, 650, 500);
        primaryStage.setScene(resultScene);
    }

    // Δημιουργεί ένα Label για την χώρα που περιέχει όλα της τα στοιχεία
    // Στοιχεία στην μορφή πινάκων εμφανίζονται με κόμματα ανάμεσα
    private Label createCountryLabel(Country country) {
        String countryString = "";
        String lineSpacing = "        ";

        countryString += country.getName().getCommon() + ":\n";
        countryString += lineSpacing + "Official Name: " + country.getName().getOfficial() + "\n";
        countryString += lineSpacing + "Region: " + country.getRegion() + "\n";
        countryString += lineSpacing + "Population: " + country.getPopulation() + "\n";
        
        countryString += lineSpacing + "Capitals: ";
        for (String capital : country.getCapital()) {
            countryString += capital + ", ";
        }
        countryString += "\n";

        countryString += lineSpacing + "Languages: ";
        for (String language : country.getLanguages().values()) {
            countryString += language + ", ";
        }
        countryString += "\n";

        countryString += lineSpacing + "Currencies: ";
        for (Currency currency : country.getCurrencies().values()) {
            countryString += currency.getName() + " (" + currency.getSymbol() + "), ";
        }
        countryString += "\n";

        Label countryLabel = new Label(countryString);
        countryLabel.setStyle("-fx-font-size: 14");

        return countryLabel;
    }
}