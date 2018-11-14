package joana.test.gh_kanban.util;

public class Constants {
    public static final String GH_REPO_USER = "googlesamples";

    public enum KanbanState {
        BACKLOG {
            public String toString() {
                return "backlog";
            }
        },
        NEXT {
            public String toString() {
                return "next";
            }
        },
        DOING {
            public String toString() {
                return "doing";
            }
        },
        DONE {
            public String toString() {
                return "done";
            }
        }
    }
}
