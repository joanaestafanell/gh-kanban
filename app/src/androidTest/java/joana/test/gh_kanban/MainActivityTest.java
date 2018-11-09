package joana.test.gh_kanban;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import joana.test.gh_kanban.view.MainActivity;
import joana.test.gh_kanban.view.account.AccountFragment;
import joana.test.gh_kanban.view.explore.ExploreFragment;
import joana.test.gh_kanban.view.local.LocalFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private Context context = InstrumentationRegistry.getTargetContext();

    private MainActivity mMainActivity;

    @Before
    public void setUp(){
        mMainActivity = mMainActivityTestRule.getActivity();
    }

    @Test
    public void testMainActivityLaubchedAndExploreFragmentAttached(){
        assertNotNull(mMainActivity);
        assertNotNull(mMainActivity.findViewById(R.id.container));

        ExploreFragment exploreFragment = (ExploreFragment) mMainActivity.getSupportFragmentManager().findFragmentByTag(ExploreFragment.TAG);
        assertNotNull(exploreFragment);
        //        // Start statistics screen.
//        onView(withId(R.id.nav_view))
//                .perform(navigateTo(R.id.statistics_navigation_menu_item));
//
//        // Check that statistics Activity was opened.
//        String expectedNoStatisticsText = InstrumentationRegistry.getTargetContext()
//                .getString(R.string.no_statistics_available);
//        onView(withId(R.id.no_statistics)).check(matches(withText(expectedNoStatisticsText)));
    }

    @Test
    public void testBottomNavigationViewClick(){
        testBottomCliks(R.id.nav_explore);
        testBottomCliks(R.id.nav_local);
        testBottomCliks(R.id.nav_account);
    }

    //TODO Finish test
    @Test
    public void testRemoteRepoClickedAndLocalRepoIsCreatedAndDisplayed(){
        ExploreFragment exploreFragment = (ExploreFragment) mMainActivity.getSupportFragmentManager().findFragmentByTag(ExploreFragment.TAG);

    }

    private void testBottomCliks(int menuId){
        onView(withId(menuId)).perform(click());

        switch (menuId){
            case R.id.nav_explore:
                ExploreFragment exploreFragment = (ExploreFragment) mMainActivity.getSupportFragmentManager().findFragmentByTag(ExploreFragment.TAG);
                assertNotNull(exploreFragment);
                break;
            case R.id.nav_local:
                LocalFragment localFragment = (LocalFragment) mMainActivity.getSupportFragmentManager().findFragmentByTag(LocalFragment.TAG);
                assertNotNull(localFragment);
                break;
            case R.id.nav_account:
                AccountFragment accountFragment = (AccountFragment) mMainActivity.getSupportFragmentManager().findFragmentByTag(AccountFragment.TAG);
                assertNotNull(accountFragment);
                break;
        }
    }

}
