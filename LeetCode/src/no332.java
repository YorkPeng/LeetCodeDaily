import java.util.*;

/**
 * @author ZJPang
 * @date 2020/8/27 0027
 */

public class no332 {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        List<String> str1 = new ArrayList<>();
        str1.add("MUC");
        str1.add("LHR");
        List<String> str2 = new ArrayList<>();
        str2.add("JFK");
        str2.add("MUC");
        List<String> str3 = new ArrayList<>();
        str3.add("SFO");
        str3.add("SJC");
        List<String> str4 = new ArrayList<>();
        str4.add("LHR");
        str4.add("SFO");
        tickets.add(str1);
        tickets.add(str2);
        tickets.add(str3);
        tickets.add(str4);
        System.out.println(findItinerary(tickets));
    }
    public static List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (List<String> temp : tickets) {
            String key = temp.get(0);
            if(!map.containsKey(key)) {
                List<String> target = new LinkedList<String>();
                map.put(key, target);
            }
            map.get(key).add(temp.get(1));
        }
        for(String key: map.keySet()){
            Collections.sort(map.get(key));
        }
        List<String> res = new ArrayList<>();
        res.add("JFK");
        backTracking(map, res, tickets.size() + 1);
        return res;
    }

    private static boolean backTracking(HashMap<String, List<String>> map, List<String> res, int targetSize){
        if(res.size() == targetSize){
            return true;
        }
        String last = res.get(res.size() - 1);
        List<String> target = map.get(last);
        if(target == null){
            return false;
        }
        for(int i = 0; i < target.size(); i++){
            String to = target.remove(i);
            res.add(to);
            if(backTracking(map, res, targetSize)){
                return true;
            }
            target.add(to);
            Collections.sort(target);
        }
        return false;
    }
}
