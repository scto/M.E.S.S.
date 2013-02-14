package jeu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class ProfilManager {
	
	// the location of the profile data file
    private static final String PROFIL_FILE = "data/profil-v7.json";
 // the loaded profile (may be null)
    private Profil profil;
    
    /**
     * Creates the profile manager.
     */
    public ProfilManager() {
    }

    /**
     * Retrieves the player's profile, creating one if needed.
     */
    public Profil retrieveProfile()
    {
        // create the handle for the profile data file
        FileHandle profileDataFile = Gdx.files.local( PROFIL_FILE );

        // if the profile is already loaded, just return it
        if( profil != null ) return profil;

        // create the JSON utility object
        Json json = new Json();

        // check if the profile data file exists
        if( profileDataFile.exists() ) {
            // load the profile from the data file
            try {
                // read the file as text
                String profileAsText = profileDataFile.readString().trim();
                // decode the contents (if it's base64 encoded)
                if( profileAsText.matches( "^[A-Za-z0-9/+=]+$" ) ) {
                    Gdx.app.log( "Log", "Persisted profile is base64 encoded" );
                    profileAsText = Base64Coder.decodeString( profileAsText );
                }
                // restore the state
                profil = json.fromJson( Profil.class, profileAsText );
            } catch( Exception e ) {
                // log the exception
                Gdx.app.error( "PROFIL", "Unable to parse existing profile data file", e );
                // recover by creating a fresh new profile data file;
                profil = new Profil();
                persist( profil );
            }

        } else {
            // create a new profile data file
            profil = new Profil();
            persist( profil );
        }
        // return the result
        return profil;
    }

    /**
     * Persists the given profile.
     */
    protected void persist(Profil profil)
    {
        // create the handle for the profile data file
        FileHandle profileDataFile = Gdx.files.local( PROFIL_FILE );
        // create the JSON utility object
        Json json = new Json();
        // convert the given profile to text
        String profileAsText;
        profileAsText = json.toJson( profil );

        // encode the text
       // if( ! Tyrian.DEV_MODE ) {
        profileAsText = Base64Coder.encodeString( profileAsText );
      //  }
        // write the profile data file
        profileDataFile.writeString( profileAsText, false );
    }

    /**
     * Persists the player's profile.
     * If no profile is available, this method does nothing.
     */
    public void persist()
    {
        if( profil != null ) {
            persist( profil );
        }
    }

	public String getXp() {
		return profil.champXp;
	}

}
