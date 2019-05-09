package chat.chat_vorlagen.tests;

import java.util.HashMap;

public class MapTest
{
    public MapTest()
    {
        HashMap<String, String> map = new HashMap<String, String>();
        
        map.put("Fritz", "01234567");
        map.put("Frieda", "98765433");
        
        map.put("Fritz", "---------");
        
        for (String name: map.keySet()) {
            System.out.println(name + ", " + map.get(name));
        }
        
        if (map.containsKey("Frieda")) {
            System.out.println("Frieda ist in der Map enthalten");
        } else {
            System.out.println("Frieda ist NICHT in der Map enthalten");
        }
        
        map.remove("Fritz");
        
        for (String name: map.keySet()) {
            System.out.println(name + ", " + map.get(name));
        }
    }
}
