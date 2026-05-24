import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit 6 assignment homework: Baseball elimination
 */
public class BaseballElimination {
    private static class NetworkData {
        private final FlowNetwork network;
        private final int source;
        private final int target;
        private final int totalTeamCapacity;
        private final int[] teamVertex;

        public NetworkData(FlowNetwork network, int source, int target, int totalTeamCapacity, int[] teamVertex) {
            this.network = network;
            this.source = source;
            this.target = target;
            this.totalTeamCapacity = totalTeamCapacity;
            this.teamVertex = teamVertex;
        }

    }


    private final int n;
    private final String[] teamNames;
    private final Map<String, Integer> teamToIndex;
    private final int[] wins;
    private final int[] losses;
    private final int[] remainings;
    private final int[][] games;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        if (filename == null) throw new IllegalArgumentException("filename is null");
        In in = new In(filename);

        n = in.readInt();
        teamNames = new String[n];
        teamToIndex = new HashMap<>();
        wins = new int[n];
        losses = new int[n];
        remainings = new int[n];
        games = new int[n][n];

        for (int i = 0; i < n; i++) {
            String name = in.readString();

            teamNames[i] = name;
            teamToIndex.put(name, i);

            wins[i] = in.readInt();
            losses[i] = in.readInt();
            remainings[i] = in.readInt();

            for (int j = 0; j < n; j++) {
                games[i][j] = in.readInt();
            }
        }


    }

    public int numberOfTeams() {
        // number of teams
        return n;
    }

    public Iterable<String> teams() {
        // all teams
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(teamNames[i]);
        }
        return result;
    }

    public int wins(String team) {
        // number of wins for given team
        validateTeam(team);
        int index = teamToIndex.get(team);
        return wins[index];
    }

    public int losses(String team) {
        // number of losses for given team
        validateTeam(team);
        int index = teamToIndex.get(team);
        return losses[index];
    }

    public int remaining(String team) {
        // number of remaining games for given team
        validateTeam(team);
        int index = teamToIndex.get(team);
        return remainings[index];
    }

    public int against(String team1, String team2) {
        // number of remaining games between team1 and team2
        validateTeam(team1);
        validateTeam(team2);
        int i = teamToIndex.get(team1);
        int j = teamToIndex.get(team2);

        return games[i][j];
    }

    public boolean isEliminated(String team) {
        // is given team eliminated?
        validateTeam(team);
        int xIndex = teamToIndex.get(team);
        int maxWin = wins[xIndex] + remainings[xIndex];

        // trivial case: check if other teams wins already more than team x's maxWin
        for (int i = 0; i < n; i++) {
            if (i != xIndex && wins[i] > maxWin) {
                return true; // team x eliminated
            }
        }

        // non-trivial case
    }

    public Iterable<String> certificateOfElimination(String team) {
        // subset R of teams that eliminates given team; null if not eliminated
        validateTeam(team);
    }

    private void validateTeam(String team) {
        if (team == null || !teamToIndex.containsKey(team)) {
            throw new IllegalArgumentException("Invalid team");
        }
    }

    private NetworkData buildFlowNetwork(int teamX){
        int maxWin = wins[teamX]+remainings[teamX];

        // count game vertices, one vertex for every pair except team x
        int gameVertexCount = 0;
        int totalGameCapacity = 0;

        for(int i=0;i<n;i++){
            if(i==teamX) continue;


        }
    }


}
