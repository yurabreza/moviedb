package com.moviedb.presentation;

import android.test.AndroidTestCase;

import com.moviedb.R;
import com.moviedb.data.exception.MovieNotFoundException;
import com.moviedb.data.exception.NetworkConnectionException;
import com.moviedb.presentation.exception.ErrorMessageFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ErrorMessageFactoryTest extends AndroidTestCase{

    @Override protected void setUp() throws Exception {
        super.setUp();
    }

    public void testNetworkConnectionErrorMessage() {
        String expectedMessage = getContext().getString(R.string.exception_message_no_connection);
        String actualMessage = ErrorMessageFactory.create(getContext(),
                new NetworkConnectionException());

        assertThat(actualMessage, is(equalTo(expectedMessage)));
    }

    public void testUserNotFoundErrorMessage() {
        String expectedMessage = getContext().getString(R.string.exception_message_movie_not_found);
        String actualMessage = ErrorMessageFactory.create(getContext(), new MovieNotFoundException());

        assertThat(actualMessage, is(equalTo(expectedMessage)));
    }
}
