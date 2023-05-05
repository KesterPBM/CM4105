package cm3110.gigachadapp.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This class provides details of the crypto parameters.
 */
@Entity(tableName = "StocksList")
public class StocksList {

    // the rank of the crypto

    // the supply of the crypto

    // the symbol of the crypto
    private String description;
    // the current price of the crypto
    private String symptomHeader;
    private String symptoms;
    private String name;



    //Getters and Setters//
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Default Constructor
     */
    public StocksList() {
        super();
    }



    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setSymptomHeader(String symptomHeader) { this.symptomHeader = symptomHeader;}

    public String getSymptomHeader() {return symptomHeader;}

    public void setSymptoms(String symptoms) {this.symptoms = symptoms;}

    public String getSymptoms() {return symptoms;}
    //To String Method//

    @Override
    public String toString() {
        return name + "\n " +
                description + "\n" +
                "Symptoms" +"\n" + symptoms + "\n"

                ;
    }
}
