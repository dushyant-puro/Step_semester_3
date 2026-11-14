package week_04.constructors.class_problems;

public class BusRouteRankingEngine {
    static class BusRoute {
        private final String routeCode, routeName;
        private final int priority;
        BusRoute(String routeCode, String routeName, int priority) {
            if (routeCode == null || routeName == null || routeCode.trim().isEmpty() || routeName.trim().isEmpty()) throw new IllegalArgumentException("Route fields required");
            this.routeCode = routeCode; this.routeName = routeName; this.priority = priority;
        }
        BusRoute(String routeCode, String routeName) { this(routeCode, routeName, 3); }
        int compareTo(BusRoute other) {
            int result = Integer.compare(other.priority, priority);
            if (result == 0) result = routeCode.compareToIgnoreCase(other.routeCode);
            if (result == 0) result = Integer.compare(routeName.length(), other.routeName.length());
            return result;
        }
        String getRouteCode() { return routeCode; }
    }
    static BusRoute[] rankRoutes(BusRoute[] routes) {
        BusRoute[] sorted = routes.clone();
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = 0; j < sorted.length - 1 - i; j++) {
                if (sorted[j].compareTo(sorted[j + 1]) > 0) {
                    BusRoute temp = sorted[j]; sorted[j] = sorted[j + 1]; sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }
    public static void main(String[] args) {
        BusRoute[] result = rankRoutes(new BusRoute[]{new BusRoute("RT205L", "Airport Express", 3), new BusRoute("rt201j", "City Central", 4), new BusRoute("RT299T", "Night Service")});
        for (BusRoute route : result) System.out.println(route.getRouteCode());
    }
}