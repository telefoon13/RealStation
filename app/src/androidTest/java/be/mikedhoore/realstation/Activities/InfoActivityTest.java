//Used resource : https://www.vogella.com/tutorials/AndroidTesting/article.html
package be.mikedhoore.realstation.Activities;

import android.view.View;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import be.mikedhoore.realstation.R;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

public class InfoActivityTest {

    @Rule
    public ActivityTestRule<InfoActivity> rule  = new  ActivityTestRule<>(InfoActivity.class);

    @Test
    public void onCreate() {
        InfoActivity infoActivity = rule.getActivity();
        View viewById = infoActivity.findViewById(R.id.textViewInfo);
        assertThat(viewById,notNullValue());
    }
}