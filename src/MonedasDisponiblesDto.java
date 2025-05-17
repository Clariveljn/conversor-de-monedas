import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonedasDisponiblesDto {
    private List<List<String>> supported_codes;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        if (supported_codes != null) {
            for (List<String> code : supported_codes) {
                if (code.size() >= 2) {
                    map.put(code.get(0), code.get(1));
                }
            }
        }
        return map;
    }
}
