package xyz.android.amrro.recipes.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import xyz.android.amrro.recipes.data.api.RecipesService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * Created by amrro <amr.elghobary@gmail.com> on 8/9/17..
 * <p>
 * Tests for {@link RecipeRepo}.
 */


@RunWith(JUnit4.class)
public class RecipeRepoTest {

    private RecipesService service;
    private RecipeRepo repo;


    @Before
    public void setUp() {
        service = mock(RecipesService.class);
        repo = new RecipeRepo(service);
    }


    @Test
    public void loadRecipes() {
        repo.recipes();
        verify(service, times(1)).recipes();
    }

}