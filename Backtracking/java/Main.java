import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.numTilePossibilities("AAB"));
    }

    public int numTilePossibilities(String tiles) {
        char[] str = tiles.toCharArray();
        Set<String> res = new HashSet<>();
        helper1(res, new boolean[tiles.length()], new StringBuilder(), str);
        return res.size();
    }

    public void helper1(Set<String> res, boolean[] visited, StringBuilder stringBuilder, char[] tiles){
        if(stringBuilder.length() != 0) {
            res.add(stringBuilder.toString());
        }
        for(int i = 0; i < tiles.length; i++){
            if(visited[i]){
                continue;
            }
            visited[i] = true;
            stringBuilder.append(tiles[i]);
            helper1(res, visited, stringBuilder, tiles);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            visited[i] = false;
        }
    }
}
