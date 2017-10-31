package xyz.android.amrro.recipes.ui.main;


import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import xyz.android.amrro.recipes.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppNavigationTest {

    @Rule
    public ActivityTestRule<HomeActivity> homeRule = new ActivityTestRule<>(HomeActivity.class);
    private IdlingResource idlingResource;

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Before
    public void init() {
        idlingResource = OkHttp3IdlingResource.create("okhttp", homeRule.getActivity().getClient());
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @After
    public void finish() {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }

    @Test
    public void appNavigationTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));


        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.nav_steps),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_bar),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recycler_view),
                        isDisplayed(),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        recyclerView2.perform(actionOnItemAtPosition(0, click()));


        // check if the next button is displayed.
        onView(allOf(withId(R.id.next), isDisplayed())).check(matches(isDisplayed()));
        // check if the prev button is not displayed cuz it's the first step.
        onView(withId(R.id.previous)).check(matches(not(isDisplayed())));
    }
}
