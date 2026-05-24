import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;

/**
 * Unit 6 assignment homework: Baseball elimination
 */
public class BaseballElimination {
    private static class NetworkData {
        private final FlowNetwork network;
        private final int source;
        private final int sink;
        private final int totalTeamCapacity;
        private final int[] teamVertex;

        public NetworkData(FlowNetwork network, int source, int sink, int totalTeamCapacity, int[] teamVertex) {
            this.network = network;
            this.source = source;
            this.sink = sink;
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
        NetworkData data = buildFlowNetwork(xIndex);
        FordFulkerson ff = new FordFulkerson(data.network, data.source, data.sink);
        return ff.value() < data.totalTeamCapacity;
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

    private NetworkData buildFlowNetwork(int teamX) {
        int maxWin = wins[teamX] + remainings[teamX];

        // count game vertices, one vertex for every pair except team x
        int gameVertexCount = 0;
        int totalGameCapacity = 0;

        for (int i = 0; i < n; i++) {
            if (i == teamX) continue;

            for (int j = 0; j < n; j++) {
                if (j == teamX) continue;
                gameVertexCount++;
                totalGameCapacity += games[i][j];
            }
        }

        int teamVertexCount = n - 1;
        int source = 0;
        int firstGameVertex = 1;
        int firstTeamVertex = firstGameVertex + gameVertexCount;
        int sink = firstTeamVertex + teamVertexCount;

        int vertexCount = sink + 1;
        FlowNetwork network = new FlowNetwork(vertexCount);

        int[] teamVertex = new int[n];
        // init tam vertex
        for (int i = 0; i < n; i++) {
            teamVertex[i] = -1;
        }

        // assign real vertex number
        int currentTeamVertex = firstTeamVertex;
        for (int i = 0; i < n; i++) {
            if (i == teamX) continue;
            teamVertex[i] = currentTeamVertex;
            currentTeamVertex++;
        }

        // edge for game to team should be inf
        double infinity = totalGameCapacity + 1.0;
        int currentGameVertex = firstGameVertex;

        for (int i = 0; i < n; i++) {
            if (i == teamX) continue;
            for (int j = 0; j < n; j++) {
                if (j == teamX) continue;

                int gameVertex = currentGameVertex++;

                //source -> game
                network.addEdge(new FlowEdge(source, gameVertex, games[i][j]));

                //game -> team i
                network.addEdge(new FlowEdge(gameVertex, teamVertex[i], infinity));

                // game -> team j
                network.addEdge(new FlowEdge(gameVertex, teamVertex[j], infinity));

            }
        }

        // add team -> sink
        for (int i = 0; i < n; i++) {
            if (i == teamX) continue;

            int capacity = maxWin - wins[i];
            // capacity should be positive but use math.max fot safety
            network.addEdge(new FlowEdge(teamVertex[i], sink, Math.max(0, capacity)));
        }
        return new NetworkData(network, source, sink, totalGameCapacity, teamVertex);


    }


}
