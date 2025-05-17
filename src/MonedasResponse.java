import java.util.List;

public class MonedasResponse {
    private String result;
    private String documentation;
    private String terms_of_use;
    private String time_last_update_utc;
    private String time_next_update_utc;
    private String base_code;
    private List<List<String>> supported_codes;

    public String getResult() {
        return result;
    }

    public List<List<String>> getSupportedCodes() {
        return supported_codes;
    }
}

