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
import org.hamcrest.core.IsInstanceOf;
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
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppNavigationTest {

    private static final String RECIPE_INTRODUCTION = "Recipe Introduction";
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
    public void navigationTest() {

        ViewInteraction recipesList_HomeActivity = onView(
                allOf(withId(R.id.recycler),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                1)));

        recipesList_HomeActivity.check(matches(isDisplayed()));

        // checking the toolbar's title in HomeActivity.
        onView(allOf(
                withText("Recipes"),
                childAtPosition(allOf(
                        withId(R.id.toolbar),
                        childAtPosition(IsInstanceOf.instanceOf(android.widget.LinearLayout.class), 0)),
                        0),
                isDisplayed())
        ).check(matches(withText("Recipes")));

        recipesList_HomeActivity.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction recipeStepsList = onView(allOf(
                withId(R.id.recycler_view),
                isDisplayed(),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))
        );
        recipeStepsList.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.largeLabel), withText("Ingredients"),
                        isDisplayed()));

        textView3.check(matches(withText("Ingredients")));

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.nav_steps),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_bar),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        // step index starts with 0.
        onView(allOf(
                withId(R.id.step_number), withText("0"),
                childAtPosition(
                        childAtPosition(
                                IsInstanceOf.instanceOf(android.widget.FrameLayout.class),
                                0),
                        0),
                isDisplayed())
        ).check(matches(withText("0")));

        // step name displayed.
        onView(allOf(
                withId(R.id.step_name),
                withText(RECIPE_INTRODUCTION),
                childAtPosition(
                        childAtPosition(
                                IsInstanceOf.instanceOf(android.widget.FrameLayout.class),
                                0),
                        1),
                isDisplayed())
        ).check(matches(isDisplayed()));


        recipeStepsList.perform(actionOnItemAtPosition(0, click()));

        // check step name
        onView(allOf(withId(R.id.step_name), isDisplayed()))
                .check(matches(withText(RECIPE_INTRODUCTION)));


        onView(allOf(withId(R.id.previous), not(isDisplayed())))
                .check(matches(not(isDisplayed())));

        onView(allOf(withId(R.id.next), isDisplayed()))
                .check(matches(isDisplayed()))
                .perform(click());


        onView(allOf(
                withId(R.id.previous),
                childAtPosition(
                        allOf(withId(R.id.linearLayout),
                                childAtPosition(
                                        withId(R.id.step_detail),
                                        1)),
                        0),
                isDisplayed())
        ).check(matches(isDisplayed()));

    }
}
