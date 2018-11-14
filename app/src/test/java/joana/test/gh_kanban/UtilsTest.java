package joana.test.gh_kanban;

import android.arch.lifecycle.ViewModelProviders;

import org.junit.Before;
import org.junit.Test;

import joana.test.gh_kanban.model.local.BoardIssue;
import joana.test.gh_kanban.util.Constants;
import joana.test.gh_kanban.util.Utils;
import joana.test.gh_kanban.viewmodel.KanbanViewModel;

import static junit.framework.TestCase.assertEquals;

public class UtilsTest {

    @Test
    public void returnTrueIfGetKanbanStateByPositionSucceeds() throws Exception {
        int pos = 0;
        String kanbanState = Utils.getKanbanStateByIndex(pos);
        assertEquals(Constants.KanbanState.BACKLOG.name().toLowerCase(), kanbanState);
        pos = 1;
        kanbanState = Utils.getKanbanStateByIndex(pos);
        assertEquals(Constants.KanbanState.NEXT.name().toLowerCase(), kanbanState);
        pos = 2;
        kanbanState = Utils.getKanbanStateByIndex(pos);
        assertEquals(Constants.KanbanState.DOING.name().toLowerCase(), kanbanState);
        pos = 3;
        kanbanState = Utils.getKanbanStateByIndex(pos);
        assertEquals(Constants.KanbanState.DONE.name().toLowerCase(), kanbanState);
    }
}
