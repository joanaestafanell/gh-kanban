package joana.test.gh_kanban.util;

public class Utils {

    public static String getKanbanStateByIndex(int index){
        String state = Constants.KanbanState.BACKLOG.toString();
        switch (index){
            case 1:
                state = Constants.KanbanState.NEXT.toString();
                break;
            case 2:
                state = Constants.KanbanState.DOING.toString();
                break;
            case 3:
                state = Constants.KanbanState.DONE.toString();
                break;
        }
        return state;
    }

    public static int getStatePosition(String state) {

        for (Constants.KanbanState kanbanState : Constants.KanbanState.values()) {
            if (kanbanState.name().equalsIgnoreCase(state)) {
                return kanbanState.ordinal();
            }
        }

        return -1;
    }
}
