package gepro.mobile.android;

import android.content.SearchRecentSuggestionsProvider;

public class GeproSearchSuggestionProvider extends SearchRecentSuggestionsProvider {
    
    final static String AUTHORITY = "com.example.android.apis.SuggestionProvider";

    final static int MODE = DATABASE_MODE_QUERIES;

    public GeproSearchSuggestionProvider() {
        super();
        setupSuggestions(AUTHORITY, MODE);
    }
}
