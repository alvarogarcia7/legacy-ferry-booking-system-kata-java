package ferry.booking.port;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static ferry.booking.Util.readFileToString;

public class Ports {

    private final List<Port> ports = new ArrayList<>();

    public Ports() {
        try {
            String json = readFileToString("/ports.txt");
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Port p = new Port();
                p.id = obj.getInt("Id");
                p.name = obj.getString("Name");
                ports.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Port> all() {
        return ports;
    }
}
